package com.company;

public class EnviromentException extends Exception{
    public EnviromentException() {
    }

    public EnviromentException(String message) {
        super(message);
    }

    public EnviromentException(String message, Throwable cause) {
        super(message, cause);
    }

    public EnviromentException(Throwable cause) {
        super(cause);
    }

    public EnviromentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
