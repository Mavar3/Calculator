package com.company;

public class CommandArgsException extends Exception {
    public CommandArgsException() {
    }

    public CommandArgsException(String message) {
        super(message);
    }

    public CommandArgsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandArgsException(Throwable cause) {
        super(cause);
    }

    public CommandArgsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
