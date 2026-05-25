package com.github.davidmoten.aws.lw.client;

public final class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -6963816822115090962L;

    private final int statusCode;

    private final String message;

    public ServiceException(int statusCode, String message) {
        super("statusCode=" + statusCode + ": " + message);
        this.statusCode = statusCode;
        this.message = message;
    }

    public int statusCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String message() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
