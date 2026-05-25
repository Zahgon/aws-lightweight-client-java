package com.github.davidmoten.aws.lw.client.internal;

public final class ClockDefault implements Clock {

    @Override
    public long time() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
