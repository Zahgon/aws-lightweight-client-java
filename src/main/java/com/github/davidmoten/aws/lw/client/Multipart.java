package com.github.davidmoten.aws.lw.client;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import com.github.davidmoten.aws.lw.client.internal.Retries;
import com.github.davidmoten.aws.lw.client.internal.util.Preconditions;

public final class Multipart {

    private Multipart() {
        // prevent instantiation
    }

    public static Builder s3(Client s3) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static final class Builder {

        private final Client s3;

        private String bucket;

        public String key;

        public ExecutorService executor;

        public long timeoutMs = TimeUnit.HOURS.toMillis(1);

        public Function<? super Request, ? extends Request> transform = x -> x;

        public int partSize = 5 * 1024 * 1024;

        public Retries<Void> retries;

        Builder(Client s3) {
            this.s3 = s3;
            this.retries = s3.retries().withValueShouldRetry(values -> false);
        }

        public Builder2 bucket(String bucket) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    public static final class Builder2 {

        private final Builder b;

        Builder2(Builder b) {
            this.b = b;
        }

        public Builder3 key(String key) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    public static final class Builder3 {

        private final Builder b;

        Builder3(Builder b) {
            this.b = b;
        }

        public Builder3 executor(ExecutorService executor) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder3 partTimeout(long duration, TimeUnit unit) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder3 partSize(int partSize) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder3 partSizeMb(int partSizeMb) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder3 maxAttemptsPerAction(int maxAttempts) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder3 retryInitialInterval(long duration, TimeUnit unit) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder3 retryBackoffFactor(double factor) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder3 retryMaxInterval(long duration, TimeUnit unit) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the level of randomness applied to the next retry interval. The next
         * calculated retry interval is multiplied by
         * {@code (1 - jitter * Math.random())}. A value of zero means no jitter, 1
         * means max jitter.
         *
         * @param jitter level of randomness applied to the retry interval
         * @return this
         */
        public Builder3 retryJitter(double jitter) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder3 transformCreateRequest(Function<? super Request, ? extends Request> transform) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void upload(byte[] bytes, int offset, int length) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void upload(byte[] bytes) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void upload(File file) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void upload(Callable<? extends InputStream> factory) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public MultipartOutputStream outputStream() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    private static void copy(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[8192];
        int n;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
    }
}
