package com.viettel.imdb.type.exception;


public class IMDBException extends RuntimeException {
    private ErrorCode errorCode;

    public IMDBException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public IMDBException(ErrorCode errorCode, Exception ex) {
        super(ex);
        this.errorCode = errorCode;
    }

    public IMDBException(ErrorCode errorCode, Throwable ex) {
        super(ex);
        this.errorCode = errorCode;
    }

    public IMDBException(ErrorCode errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public IMDBException(String message, Exception ex) {
        super(message, ex);
    }

    public IMDBException(String message) {
        super(message);
    }

    public IMDBException(Throwable throwable) {
        super(throwable);
    }

    public IMDBException() {

    }

    @Override
    public String toString() {
        return "IMDBException{" +
                "errorCode=" + errorCode +
                ", message = \"" + getMessage() + "\"" +
                '}';
    }

    public final ErrorCode getErrorCode() {
        return errorCode;
    }
}
