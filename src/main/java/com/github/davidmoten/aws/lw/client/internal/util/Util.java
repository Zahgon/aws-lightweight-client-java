package com.github.davidmoten.aws.lw.client.internal.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

/**
 * Utilities for encoding and decoding binary data to and from different forms.
 */
public final class Util {

    private Util() {
        // prevent instantiation
    }

    public static HttpURLConnection createHttpConnection(URL endpointUrl, String httpMethod, Map<String, String> headers, int connectTimeoutMs, int readTimeoutMs) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String canonicalMetadataKey(String meta) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts byte data to a Hex-encoded string.
     *
     * @param data data to hex encode.
     *
     * @return hex-encoded string.
     */
    public static String toHex(byte[] data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static URL toUrl(String url) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String urlEncode(String url, boolean keepPathSlash) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // VisibleForTesting
    static String urlEncode(String url, boolean keepPathSlash, String charset) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Hashes the string contents (assumed to be UTF-8) using the SHA-256 algorithm.
     */
    public static byte[] sha256(String text) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static byte[] sha256(byte[] data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // VisibleForTesting
    static byte[] hash(byte[] data, String algorithm) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static byte[] readBytesAndClose(InputStream in) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static final InputStream EMPTY_INPUT_STREAM = new InputStream() {

        @Override
        public int read() throws IOException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    };

    public static final InputStream emptyInputStream() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Optional<String> jsonFieldText(String json, String fieldName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
