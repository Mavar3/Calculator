package com.company;

public class DivisionException extends Exception {
    public DivisionException() {
    }

    public DivisionException(String message) {
        super(message);
    }

    public DivisionException(String message, Throwable cause) {
        super(message, cause);
    }

    public DivisionException(Throwable cause) {
        super(cause);
    }

    public DivisionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
