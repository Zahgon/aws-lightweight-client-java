package com.github.davidmoten.aws.lw.client;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import com.github.davidmoten.aws.lw.client.internal.Retries;
import com.github.davidmoten.aws.lw.client.internal.util.Preconditions;
import com.github.davidmoten.aws.lw.client.internal.util.Util;
import com.github.davidmoten.aws.lw.client.xml.XmlElement;

public final class Request {

    private final Client client;

    private Optional<String> region;

    private String url;

    private HttpMethod method = HttpMethod.GET;

    private final Map<String, List<String>> headers = new HashMap<>();

    private byte[] requestBody;

    private int connectTimeoutMs;

    private int readTimeoutMs;

    private int attributeNumber = 1;

    private Retries<ResponseInputStream> retries;

    private String attributePrefix = "Attribute";

    private String[] pathSegments;

    private final List<NameValue> queries = new ArrayList<>();

    private boolean signPayload = true;

    Request(Client client, String url, String... pathSegments) {
        this.client = client;
        this.url = url;
        this.pathSegments = pathSegments;
        this.region = client.region();
        this.connectTimeoutMs = client.connectTimeoutMs();
        this.readTimeoutMs = client.readTimeoutMs();
        this.retries = client.retries().copy();
    }

    public Request method(HttpMethod method) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Request query(String name, String value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Request query(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Request attributePrefix(String attributePrefix) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Request attribute(String name, String value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Request header(String name, String value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Request signPayload(boolean signPayload) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Request unsignedPayload() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Adds the header {@code x-amz-meta-KEY:value}. {@code KEY} is obtained from
     * {@code key} by converting to lower-case (headers are case-insensitive) and
     * only retaining alphabetical and digit characters.
     *
     * @param key   metadata key
     * @param value metadata value
     * @return request builder
     */
    public Request metadata(String key, String value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Request requestBody(byte[] requestBody) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Request requestBody(String requestBody) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Request region(String region) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Request connectTimeout(long duration, TimeUnit unit) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Request readTimeout(long duration, TimeUnit unit) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Request retryInitialInterval(long duration, TimeUnit unit) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Request retryMaxAttempts(int maxAttempts) {
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
    public Request retryJitter(double jitter) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Request retryBackoffFactor(double factor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Request retryMaxInterval(long duration, TimeUnit unit) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Opens a connection and makes the request. This method returns all the
     * response information including headers, status code, request body as an
     * InputStream. If an error status code is encountered (outside 200-299) then an
     * exception is <b>not</b> thrown (unlike the other methods .response*). The
     * caller <b>must close</b> the InputStream when finished with it.
     *
     * @return all response information, the caller must close the InputStream when
     *         finished with it
     */
    public ResponseInputStream responseInputStream() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Opens a connection and makes the request. This method returns all the
     * response information including headers, status code, request body as a byte
     * array. If an error status code is encountered (outside 200-299) then an
     * exception is <b>not</b> thrown (unlike the other methods .response*).
     *
     * @return all response information
     */
    public Response response() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Opens a connection and makes the request. This method returns all the
     * response information including headers, status code, request body as a byte
     * array. If the expected status code is not encountered then a
     * {@link ServiceException} is thrown.
     *
     * @return all response information
     * @throws ServiceException
     */
    public Response responseExpectStatusCode(int expectedStatusCode) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // VisibleForTesting
    static boolean hasBody(ResponseInputStream r) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static String calculateUrl(String url, String serviceName, Optional<String> region, List<NameValue> queries, List<String> pathSegments, BaseUrlFactory baseUrlFactory) {
        String u = url;
        if (u == null) {
            String baseUrl = baseUrlFactory.create(serviceName, region);
            Preconditions.checkNotNull(baseUrl, "baseUrl cannot be null");
            u = //
            trimAndEnsureHasTrailingSlash(baseUrl) + //
            pathSegments.//
            stream().map(//
            x -> Util.urlEncode(x, false)).map(//
            x -> trimAndRemoveLeadingAndTrailingSlashes(x)).collect(Collectors.joining("/"));
        }
        // add queries
        for (NameValue nv : queries) {
            if (!u.contains("?")) {
                u += "?";
            }
            if (!u.endsWith("?")) {
                u += "&";
            }
            if (nv.value != null) {
                u += Util.urlEncode(nv.name, false) + "=" + Util.urlEncode(nv.value, false);
            } else {
                u += Util.urlEncode(nv.name, false);
            }
        }
        return u;
    }

    // VisibleForTesting
    static String trimAndEnsureHasTrailingSlash(String s) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public byte[] responseAsBytes() {
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

    public void execute() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String responseAsUtf8() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public XmlElement responseAsXml() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String presignedUrl(long expiryDuration, TimeUnit unit) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // VisibleForTesting
    static String trimAndRemoveLeadingAndTrailingSlashes(String s) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static final class NameValue {

        final String name;

        final String value;

        NameValue(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }
}
