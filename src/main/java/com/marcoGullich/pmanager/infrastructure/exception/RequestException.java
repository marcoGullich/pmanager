package com.marcoGullich.pmanager.infrastructure.exception;

public class RequestException extends RuntimeException {

    private String errorCode;

    public RequestException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
