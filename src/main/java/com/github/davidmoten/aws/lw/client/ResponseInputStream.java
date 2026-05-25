package com.github.davidmoten.aws.lw.client;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public final class ResponseInputStream extends InputStream {

    // nullable
    private final Closeable closeable;

    private final int statusCode;

    private final Map<String, List<String>> headers;

    private final InputStream content;

    public ResponseInputStream(HttpURLConnection connection, int statusCode, Map<String, List<String>> headers, InputStream content) {
        this(() -> connection.disconnect(), statusCode, headers, content);
    }

    public ResponseInputStream(Closeable closeable, int statusCode, Map<String, List<String>> headers, InputStream content) {
        this.closeable = closeable;
        this.statusCode = statusCode;
        this.headers = headers;
        this.content = content;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int read() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void close() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int statusCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Map<String, List<String>> headers() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Optional<String> header(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
