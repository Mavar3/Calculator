package com.company;

public class ExpressionException extends Exception {
    public ExpressionException() {
    }

    public ExpressionException(String message) {
        super(message);
    }

    public ExpressionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExpressionException(Throwable cause) {
        super(cause);
    }

    public ExpressionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
