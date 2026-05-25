package com.github.davidmoten.aws.lw.client.internal;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.concurrent.Callable;
import java.util.function.Predicate;
import com.github.davidmoten.aws.lw.client.MaxAttemptsExceededException;
import com.github.davidmoten.aws.lw.client.internal.util.Preconditions;

public final class Retries<T> {

    private final long initialIntervalMs;

    private final int maxAttempts;

    private final double backoffFactor;

    private final long maxIntervalMs;

    private final double jitter;

    private final Predicate<? super T> valueShouldRetry;

    private final Predicate<? super Throwable> throwableShouldRetry;

    public Retries(long initialIntervalMs, int maxAttempts, double backoffFactor, double jitter, long maxIntervalMs, Predicate<? super T> valueShouldRetry, Predicate<? super Throwable> throwableShouldRetry) {
        Preconditions.checkArgument(jitter >= 0 && jitter <= 1, "jitter must be between 0 and 1 inclusive");
        this.initialIntervalMs = initialIntervalMs;
        this.maxAttempts = maxAttempts;
        this.backoffFactor = backoffFactor;
        this.jitter = jitter;
        this.maxIntervalMs = maxIntervalMs;
        this.valueShouldRetry = valueShouldRetry;
        this.throwableShouldRetry = throwableShouldRetry;
    }

    public static <T> Retries<T> create(Predicate<? super T> valueShouldRetry, Predicate<? super Throwable> throwableShouldRetry) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public T call(Callable<T> callable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public <S> S call(Callable<S> callable, Predicate<? super S> valueShouldRetry) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // VisibleForTesting
    static boolean reachedMaxAttempts(int attempt, int maxAttempts) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static void sleep(long intervalMs) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public <S> Retries<S> withValueShouldRetry(Predicate<? super S> valueShouldRetry) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Retries<T> withInitialIntervalMs(long initialIntervalMs) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Retries<T> withMaxAttempts(int maxAttempts) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Retries<T> withBackoffFactor(double backoffFactor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Retries<T> withMaxIntervalMs(long maxIntervalMs) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Retries<T> withJitter(double jitter) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Retries<T> withThrowableShouldRetry(Predicate<? super Throwable> throwableShouldRetry) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Retries<T> copy() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // VisibleForTesting
    static void rethrow(Throwable t) throws Error {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
