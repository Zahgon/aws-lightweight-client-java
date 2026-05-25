package com.github.davidmoten.aws.lw.client;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import com.github.davidmoten.aws.lw.client.internal.util.Preconditions;
import com.github.davidmoten.aws.lw.client.internal.util.Util;

public final class Metadata {

    private final Map<String, String> map;

    Metadata(Map<String, String> map) {
        this.map = map;
    }

    public Optional<String> value(String key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Set<Entry<String, String>> entrySet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
