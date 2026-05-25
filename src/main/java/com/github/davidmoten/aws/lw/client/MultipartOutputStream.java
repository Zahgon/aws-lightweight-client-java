package com.github.davidmoten.aws.lw.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import com.github.davidmoten.aws.lw.client.internal.Retries;
import com.github.davidmoten.aws.lw.client.internal.util.Preconditions;
import com.github.davidmoten.aws.lw.client.xml.builder.Xml;

// NotThreadSafe
public final class MultipartOutputStream extends OutputStream {

    private final Client s3;

    private final String bucket;

    private final String key;

    private final String uploadId;

    private final ExecutorService executor;

    private final ByteArrayOutputStream bytes;

    // for reuse in write(int) method
    private final byte[] singleByte = new byte[1];

    private final long partTimeoutMs;

    private final Retries<Void> retries;

    private final int partSize;

    private final List<Future<String>> futures = new CopyOnWriteArrayList<>();

    private int nextPart = 1;

    MultipartOutputStream(Client s3, String bucket, String key, Function<? super Request, ? extends Request> transformCreate, ExecutorService executor, long partTimeoutMs, Retries<Void> retries, int partSize) {
        Preconditions.checkNotNull(s3);
        Preconditions.checkNotNull(bucket);
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(transformCreate);
        Preconditions.checkNotNull(executor);
        Preconditions.checkArgument(partTimeoutMs > 0);
        Preconditions.checkNotNull(retries);
        Preconditions.checkArgument(partSize >= 5 * 1024 * 1024);
        this.s3 = s3;
        this.bucket = bucket;
        this.key = key;
        this.executor = executor;
        this.partTimeoutMs = partTimeoutMs;
        this.retries = retries;
        this.partSize = partSize;
        this.bytes = new ByteArrayOutputStream();
        this.uploadId = transformCreate.apply(//
        s3.path(bucket, //
        key).query(//
        "uploads").method(//
        HttpMethod.POST)).//
        responseAsXml().content("UploadId");
    }

    public void abort() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void write(byte[] b) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void submitPart() {
        int part = nextPart;
        nextPart++;
        byte[] body = bytes.toByteArray();
        bytes.reset();
        Future<String> future = executor.submit(() -> retry(() -> //
        s3.path(bucket, //
        key).method(//
        HttpMethod.PUT).query("partNumber", //
        "" + part).query("uploadId", //
        uploadId).requestBody(//
        body).readTimeout(partTimeoutMs, //
        TimeUnit.MILLISECONDS).responseExpectStatusCode(//
        200).firstHeader(//
        "ETag").//
        get().replace("\"", //
        ""), "on part " + part));
        futures.add(future);
    }

    private <T> T retry(Callable<T> callable, String description) {
        //TODO use description
        return retries.call(callable, x -> false);
    }

    @Override
    public void close() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private String getResult(Future<String> future) {
        try {
            return future.get(partTimeoutMs, TimeUnit.MILLISECONDS);
        } catch (Throwable e) {
            abort();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(int b) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
