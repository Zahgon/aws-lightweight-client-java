package com.github.davidmoten.aws.lw.client;

import java.util.Optional;
import com.github.davidmoten.aws.lw.client.internal.CredentialsImpl;
import com.github.davidmoten.aws.lw.client.internal.Environment;

public interface Credentials {

    String accessKey();

    String secretKey();

    Optional<String> sessionToken();

    static Credentials of(String accessKey, String secretKey) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static Credentials of(String accessKey, String secretKey, String sessionToken) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static Credentials fromEnvironment() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static Credentials fromSystemProperties() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
