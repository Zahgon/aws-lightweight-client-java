package com.github.davidmoten.aws.lw.client;

import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.github.davidmoten.aws.lw.client.internal.util.Preconditions;

public final class Response {

    private final Map<String, List<String>> headers;

    private final Map<String, List<String>> headersLowerCaseKey;

    private final byte[] content;

    private final int statusCode;

    public Response(Map<String, List<String>> headers, byte[] content, int statusCode) {
        this.headers = headers;
        this.headersLowerCaseKey = lowerCaseKey(headers);
        this.content = content;
        this.statusCode = statusCode;
    }

    public Map<String, List<String>> headers() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Map<String, List<String>> headersLowerCaseKey() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Optional<String> firstHeader(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Optional<Instant> firstHeaderFullDate(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns those headers that start with {@code x-amz-meta-} (and removes that
     * prefix).
     *
     * @return headers that start with {@code x-amz-meta-} (and removes that prefix)
     */
    public Metadata metadata() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Optional<String> metadata(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public byte[] content() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String contentUtf8() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int statusCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isOk() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns true if and only if status code is 2xx. Returns false if status code
     * is 404 (NOT_FOUND) and throws a {@link ServiceException} otherwise.
     *
     * @return true if status code 2xx, false if 404 otherwise throws
     *         ServiceException
     * @throws ServiceException if status code other than 2xx or 404
     */
    public boolean exists() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static Map<String, List<String>> lowerCaseKey(Map<String, List<String>> m) {
        return //
        m.entrySet().stream().//
        collect(//
        Collectors.//
        toMap(//
        entry -> lowerCase(entry.getKey()), entry -> entry.getValue()));
    }

    private static String lowerCase(String s) {
        return s == null ? s : s.toLowerCase(Locale.ENGLISH);
    }
    // TODO add toString method
}
