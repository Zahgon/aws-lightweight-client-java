package com.github.davidmoten.aws.lw.client.internal;

import com.github.davidmoten.aws.lw.client.Credentials;
import com.github.davidmoten.aws.lw.client.HttpClient;

@FunctionalInterface
public interface Environment {

    String get(String name);

    default Credentials credentials() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static Environment instance() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
