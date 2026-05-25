package com.github.davidmoten.aws.lw.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.github.davidmoten.aws.lw.client.internal.Clock;
import com.github.davidmoten.aws.lw.client.internal.auth.AwsSignatureVersion4;
import com.github.davidmoten.aws.lw.client.internal.util.Preconditions;
import com.github.davidmoten.aws.lw.client.internal.util.Util;

final class RequestHelper {

    private RequestHelper() {
        // prevent instantiation
    }

    static void put(Map<String, List<String>> map, String name, String value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static Map<String, String> combineHeaders(Map<String, List<String>> headers) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static String presignedUrl(Clock clock, String url, String method, Map<String, String> headers, byte[] requestBody, String serviceName, Optional<String> regionName, Credentials credentials, int connectTimeoutMs, int readTimeoutMs, long expirySeconds, boolean signPayload) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static void includeTokenIfPresent(Credentials credentials, Map<String, String> h) {
        if (credentials.sessionToken().isPresent()) {
            h.put("x-amz-security-token", credentials.sessionToken().get());
        }
    }

    static ResponseInputStream request(Clock clock, HttpClient httpClient, String url, HttpMethod method, Map<String, String> headers, byte[] requestBody, String serviceName, //
    Optional<String> regionName, //
    Credentials credentials, //
    int connectTimeoutMs, //
    int readTimeoutMs, boolean signPayload) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static List<Parameter> extractQueryParameters(URL endpointUrl) {
        String query = endpointUrl.getQuery();
        if (query == null) {
            return Collections.emptyList();
        } else {
            return extractQueryParameters(query);
        }
    }

    private static final char QUERY_PARAMETER_SEPARATOR = '&';

    private static final char QUERY_PARAMETER_VALUE_SEPARATOR = '=';

    /**
     * Extract parameters from a query string, preserving encoding.
     * <p>
     * We can't use Apache HTTP Client's URLEncodedUtils.parse, mainly because we
     * don't want to decode names/values.
     *
     * @param rawQuery the query to parse
     * @return The list of parameters, in the order they were found.
     */
    // VisibleForTesting
    static List<Parameter> extractQueryParameters(String rawQuery) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // VisibleForTesting
    static Parameter parameter(String name, String value, String charset) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // VisibleForTesting
    static final class Parameter {

        final String name;

        final String value;

        Parameter(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }

    static boolean isEmpty(byte[] array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
