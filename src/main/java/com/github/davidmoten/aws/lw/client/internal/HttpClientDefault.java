package com.github.davidmoten.aws.lw.client.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import com.github.davidmoten.aws.lw.client.HttpClient;
import com.github.davidmoten.aws.lw.client.ResponseInputStream;
import com.github.davidmoten.aws.lw.client.internal.util.Util;

public final class HttpClientDefault implements HttpClient {

    public static final HttpClientDefault INSTANCE = new HttpClientDefault();

    private HttpClientDefault() {
    }

    @Override
    public ResponseInputStream request(URL endpointUrl, String httpMethod, Map<String, String> headers, byte[] requestBody, int connectTimeoutMs, int readTimeoutMs) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // VisibleForTesting
    static ResponseInputStream request(HttpURLConnection connection, byte[] requestBody) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static boolean isOk(int responseCode) {
        return responseCode >= 200 && responseCode <= 299;
    }
}
