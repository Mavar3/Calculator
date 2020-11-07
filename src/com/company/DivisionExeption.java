package com.company;

public class DivisionExeption extends Exception {
    public DivisionExeption() {
    }

    public DivisionExeption(String message) {
        super(message);
    }

    public DivisionExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public DivisionExeption(Throwable cause) {
        super(cause);
    }

    public DivisionExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
