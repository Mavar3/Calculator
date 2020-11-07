package com.company;

public class ExpressionExeption extends Exception {
    public ExpressionExeption() {
    }

    public ExpressionExeption(String message) {
        super(message);
    }

    public ExpressionExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public ExpressionExeption(Throwable cause) {
        super(cause);
    }

    public ExpressionExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
