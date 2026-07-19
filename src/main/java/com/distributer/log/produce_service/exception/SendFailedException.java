package com.distributer.log.produce_service.exception;

public class SendFailedException extends RuntimeException{
    public SendFailedException() {
    }

    public SendFailedException(String message) {
        super(message);
    }

    public SendFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SendFailedException(Throwable cause) {
        super(cause);
    }

    public SendFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
