package com.github.davidmoten.aws.lw.client.internal.auth;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.SimpleTimeZone;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import com.github.davidmoten.aws.lw.client.internal.Clock;
import com.github.davidmoten.aws.lw.client.internal.util.Preconditions;
import com.github.davidmoten.aws.lw.client.internal.util.Util;

/**
 * Common methods and properties for all AWS4 signer variants
 */
public final class AwsSignatureVersion4 {

    static final String ALGORITHM_HMAC_SHA256 = "HmacSHA256";

    /**
     * SHA256 hash of an empty request body *
     */
    public static final String EMPTY_BODY_SHA256 = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";

    public static final String UNSIGNED_PAYLOAD = "UNSIGNED-PAYLOAD";

    public static final String SCHEME = "AWS4";

    public static final String ALGORITHM = "HMAC-SHA256";

    public static final String TERMINATOR = "aws4_request";

    /**
     * format strings for the date/time and date stamps required during signing *
     */
    private static final String ISO8601BasicFormat = "yyyyMMdd'T'HHmmss'Z'";

    private static final String DateStringFormat = "yyyyMMdd";

    private AwsSignatureVersion4() {
        // prevent instantiation
    }

    /**
     * Computes an AWS4 authorization for a request, suitable for embedding in query
     * parameters.
     *
     * @param endpointUrl     the url to which the request is being made
     * @param httpMethod      the HTTP method (GET, POST, PUT, etc.)
     * @param serviceName     the AWS service code (e.g iam)
     * @param regionName      the AWS region name
     * @param clock           provides a timestamp
     * @param headers         The request headers; 'Host' and 'X-Amz-Date' will be
     *                        added to this set.
     * @param queryParameters Any query parameters that will be added to the
     *                        endpoint. The parameters should be specified in
     *                        canonical format.
     * @param bodyHash        Precomputed SHA256 hash of the request body content;
     *                        this value should also be set as the header
     *                        'X-Amz-Content-SHA256' for non-streaming uploads.
     * @param awsAccessKey    The user's AWS Access Key.
     * @param awsSecretKey    The user's AWS Secret Key.
     * @param sessionToken
     * @return The computed authorization string for the request. This value needs
     *         to be set as the header 'Authorization' on the subsequent HTTP
     *         request.
     */
    public static String computeSignatureForQueryAuth(URL endpointUrl, String httpMethod, String serviceName, Optional<String> regionName, Clock clock, Map<String, String> headers, Map<String, String> queryParameters, String bodyHash, String awsAccessKey, String awsSecretKey, Optional<String> sessionToken) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Computes an AWS4 signature for a request, ready for inclusion as an
     * 'Authorization' header.
     *
     * @param endpointUrl     the url to which the request is being made
     * @param httpMethod      the HTTP method (GET, POST, PUT, etc.)
     * @param serviceName     the AWS service code (e.g iam)
     * @param regionName      the AWS region name
     * @param clock           provides a timestamp
     * @param headers         The request headers; 'Host' and 'X-Amz-Date' will be
     *                        added to this set.
     * @param queryParameters Any query parameters that will be added to the
     *                        endpoint. The parameters should be specified in
     *                        canonical format.
     * @param bodyHash        Precomputed SHA256 hash of the request body content;
     *                        this value should also be set as the header
     *                        'X-Amz-Content-SHA256' for non-streaming uploads.
     * @param awsAccessKey    The user's AWS Access Key.
     * @param awsSecretKey    The user's AWS Secret Key.
     * @return The computed authorization string for the request. This value needs
     *         to be set as the header 'Authorization' on the subsequent HTTP
     *         request.
     */
    public static String computeSignatureForAuthorizationHeader(URL endpointUrl, String httpMethod, String serviceName, String regionName, Clock clock, Map<String, String> headers, Map<String, String> queryParameters, String bodyHash, String awsAccessKey, String awsSecretKey) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static SimpleDateFormat dateTimeFormat() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static SimpleDateFormat dateStampFormat() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the canonical string of header names that will be included in the
     * signature. For AWS4, all header names must be included in the process in
     * sorted canonicalized order.
     *
     * @param headers input to convert to canonical string
     * @return canonical header names string
     */
    static String getCanonicalizeHeaderNames(Map<String, String> headers) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the canonical headers string. For AWS4, all headers must be included
     * in the signing process.
     *
     * @param headers input to convert to canonical string
     * @return canonical headers string
     */
    static String getCanonicalizedHeaderString(Map<String, String> headers) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the canonical request string to go into the signer process; this
     * consists of several canonical sub-parts.
     *
     * @param endpoint                 url to which the request is being made
     * @param httpMethod               http method (e.g GET, POST)
     * @param canonicalQueryParameters canonical query parameters string
     * @param canonicalizedHeaderNames canonical header names string
     * @param canonicalizedHeaders     canonical headers string
     * @param bodyHash                 SHA-256 hash of request body
     * @return canonical request string
     */
    static String getCanonicalRequest(URL endpoint, String httpMethod, String canonicalQueryParameters, String canonicalizedHeaderNames, String canonicalizedHeaders, String bodyHash) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the canonicalized resource path for the service endpoint.
     *
     * @param endpoint url to which the request is being made
     * @return canonicalized resource path
     */
    static String getCanonicalizedResourcePath(URL endpoint) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Examines the specified query string parameters and returns a canonicalized
     * form.
     * <p>
     * The canonicalized query string is formed by first sorting all the query
     * string parameters, then URI encoding both the key and value and then joining
     * them, in order, separating key value pairs with an '&'.
     *
     * @param parameters The query string parameters to be canonicalized.
     *
     * @return A canonicalized form for the specified query string parameters.
     */
    static String getCanonicalizedQueryString(Map<String, String> parameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static String blankIfNull(String s) {
        return s == null ? "" : s;
    }

    static String getStringToSign(String scheme, String algorithm, String dateTime, String scope, String canonicalRequest) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static byte[] sign(String stringData, byte[] key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // VisibleForTesting
    static byte[] sign(String stringData, byte[] key, String algorithm) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
