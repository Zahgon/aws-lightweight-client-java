package com.github.davidmoten.aws.lw.client;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import com.github.davidmoten.aws.lw.client.internal.Clock;
import com.github.davidmoten.aws.lw.client.internal.Environment;
import com.github.davidmoten.aws.lw.client.internal.ExceptionFactoryExtended;
import com.github.davidmoten.aws.lw.client.internal.Retries;
import com.github.davidmoten.aws.lw.client.internal.util.Preconditions;

public final class Client {

    private final Clock clock;

    private final String serviceName;

    private final Optional<String> region;

    private final Credentials credentials;

    private final HttpClient httpClient;

    private final int connectTimeoutMs;

    private final int readTimeoutMs;

    private final ExceptionFactory exceptionFactory;

    private final BaseUrlFactory baseUrlFactory;

    private final Retries<ResponseInputStream> retries;

    private Client(Clock clock, String serviceName, Optional<String> region, Credentials credentials, HttpClient httpClient, int connectTimeoutMs, int readTimeoutMs, ExceptionFactory exceptionFactory, BaseUrlFactory baseUrlFactory, Retries<ResponseInputStream> retries) {
        this.clock = clock;
        this.serviceName = serviceName;
        this.region = region;
        this.credentials = credentials;
        this.httpClient = httpClient;
        this.connectTimeoutMs = connectTimeoutMs;
        this.readTimeoutMs = readTimeoutMs;
        this.exceptionFactory = exceptionFactory;
        this.baseUrlFactory = baseUrlFactory;
        this.retries = retries;
    }

    public static Builder service(String serviceName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    ///////////////////////////////////////////////////
    //
    // Convenience methods for a few common services
    // Use service(serviceName) method for the rest
    //
    ///////////////////////////////////////////////////
    public static Builder s3() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Builder sqs() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Builder iam() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Builder ec2() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Builder sns() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Builder lambda() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    ///////////////////////////////////////////////////
    String serviceName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Optional<String> region() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    Credentials credentials() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    HttpClient httpClient() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    Clock clock() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    ExceptionFactory exceptionFactory() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    BaseUrlFactory baseUrlFactory() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    int connectTimeoutMs() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    int readTimeoutMs() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    Retries<ResponseInputStream> retries() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Request url(String url) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Specify the path (can include query starting with ? at end of final segment).
     * The segments you pass will be url encoded for you. If a {@code /} is present
     * then it will act as a parameter delimiter (won't be url encoded). Note that this
     * behaviour will not be preserved in a future (breaking) release to enable the
     * encoding of a {@code /} character in a segment. For this reason, favour passing
     * the path segments as separate parameters.
     *
     * @param segments that will be joined together with the '/' character
     * @return request
     */
    public Request path(String... segments) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Specify the path (can include query starting with ? at end of final segment).
     * The segments you pass will be url encoded for you. If {@code encodeForwardSlashes}
     * is false and {@code /} is present then it will act as a parameter delimiter
     * (won't be url encoded).
     *
     * @param encodeForwardSlashes if true then forward slashes will be encoded
     * @param segments that will be joined together with the '/' character
     * @return request
     */
    public Request path(boolean encodeForwardSlashes, String... segments) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static String[] expandForwardSlashes(String[] segments) {
        return Arrays.asList(segments).stream().flatMap(item -> {
            if (item.contains("/")) {
                String[] expanded = item.split("/");
                return Arrays.stream(expanded);
            } else {
                return Stream.of(item);
            }
        }).toArray(String[]::new);
    }

    public Request query(String name, String value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Request attributePrefix(String attributePrefix) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Request attribute(String name, String value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static final class Builder {

        // from
        // https://docs.aws.amazon.com/sdkref/latest/guide/feature-retry-behavior.html
        private static final Set<Integer> RETRY_STATUS_CODES = new //
        HashSet<>(//
        Arrays.//
        asList(// BAD_REQUEST
        400, // FORBIDDEN
        403, // REQUEST_TIMEOUT
        408, // TOO_MANY_REQUESTS
        429, // INTERNAL_SERVER_ERROR
        500, // BAD_GATEWAY
        502, // SERVICE_UNAVAILABLE
        503, // BANDWIDTH_LIMIT_EXCEEDED
        509));

        private final String serviceName;

        private Optional<String> region = Optional.empty();

        private String accessKey;

        private Credentials credentials;

        private HttpClient httpClient = HttpClient.defaultClient();

        private int connectTimeoutMs = 30000;

        private int readTimeoutMs = 300000;

        private ExceptionFactory exceptionFactory = ExceptionFactory.DEFAULT;

        private Clock clock = Clock.DEFAULT;

        private Environment environment = Environment.instance();

        private BaseUrlFactory baseUrlFactory = BaseUrlFactory.DEFAULT;

        private Retries<ResponseInputStream> retries = Retries.create(//
        ris -> RETRY_STATUS_CODES.contains(ris.statusCode()), t -> t instanceof IOException || t instanceof UncheckedIOException);

        private Builder(String serviceName) {
            this.serviceName = serviceName;
        }

        // VisibleForTesting
        Builder environment(Environment environment) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder4 defaultClient() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder4 from(Client client) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder2 regionFromEnvironment() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder2 region(Optional<String> region) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder2 region(String region) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder2 regionNone() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    public static final class Builder2 {

        private final Builder b;

        private Builder2(Builder b) {
            this.b = b;
        }

        public Builder4 credentialsFromEnvironment() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder4 credentialsFromSystemProperties() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder3 accessKey(String accessKey) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder4 credentials(Credentials credentials) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    public static final class Builder3 {

        private final Builder b;

        private Builder3(Builder b) {
            this.b = b;
        }

        public Builder4 secretKey(String secretKey) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    public static final class Builder4 {

        private final Builder b;

        private Builder4(Builder b) {
            this.b = b;
        }

        public Builder4 baseUrlFactory(BaseUrlFactory factory) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder4 httpClient(HttpClient httpClient) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder4 retryInitialInterval(long duration, TimeUnit unit) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder4 retryMaxAttempts(int maxAttempts) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder4 retryBackoffFactor(double factor) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder4 retryMaxInterval(long duration, TimeUnit unit) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the level of randomness applied to the next retry interval. The next
         * calculated retry interval is multiplied by
         * {@code (1 - jitter * Math.random())}. A value of zero means no jitter, 1
         * means max jitter.
         *
         * @param jitter level of randomness applied to the retry interval
         * @return this
         */
        public Builder4 retryJitter(double jitter) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder4 retryCondition(Predicate<? super ResponseInputStream> shouldRetry) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder4 retryStatusCodes(Integer... statusCodes) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder4 retryStatusCodes(Collection<Integer> statusCodes) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Default behaviour is to retry IOException and UncheckedIOException.
         *
         * @param shouldRetry returns true if should retry
         * @return this
         */
        public Builder4 retryException(Predicate<? super Throwable> shouldRetry) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder4 connectTimeout(long duration, TimeUnit unit) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder4 readTimeout(long duration, TimeUnit unit) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder4 exceptionFactory(ExceptionFactory exceptionFactory) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder4 exception(Predicate<? super Response> predicate, Function<? super Response, ? extends RuntimeException> factory) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder4 clock(Clock clock) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Client build() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
