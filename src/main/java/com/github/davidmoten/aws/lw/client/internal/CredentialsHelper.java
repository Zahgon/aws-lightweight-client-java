package com.github.davidmoten.aws.lw.client.internal;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import com.github.davidmoten.aws.lw.client.Credentials;
import com.github.davidmoten.aws.lw.client.HttpClient;
import com.github.davidmoten.aws.lw.client.ResponseInputStream;
import com.github.davidmoten.aws.lw.client.internal.util.Util;

final class CredentialsHelper {

    private static final int CONNECT_TIMEOUT_MS = 10000;

    private static final int READ_TIMEOUT_MS = 10000;

    private CredentialsHelper() {
        // prevent instantiation
    }

    static Credentials credentialsFromEnvironment(Environment env, HttpClient client) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // VisibleForTesting
    static String resolveContainerToken(String containerToken, String containerTokenFile) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // VisibleForTesting
    static String readUtf8(String file) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
