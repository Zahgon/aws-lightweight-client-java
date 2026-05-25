package com.github.davidmoten.aws.lw.client.internal;

import java.util.Optional;
import com.github.davidmoten.aws.lw.client.Credentials;
import com.github.davidmoten.aws.lw.client.internal.util.Preconditions;

public final class CredentialsImpl implements Credentials {

    private final String accessKey;

    private final String secretKey;

    private final Optional<String> sessionToken;

    public CredentialsImpl(String accessKey, String secretKey, Optional<String> sessionToken) {
        Preconditions.checkNotNull(accessKey);
        Preconditions.checkNotNull(secretKey);
        Preconditions.checkNotNull(sessionToken);
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.sessionToken = sessionToken;
    }

    @Override
    public String accessKey() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String secretKey() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Optional<String> sessionToken() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
