package com.company;

public class CommandArgsExeption extends Exception {
    public CommandArgsExeption() {
    }

    public CommandArgsExeption(String message) {
        super(message);
    }

    public CommandArgsExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandArgsExeption(Throwable cause) {
        super(cause);
    }

    public CommandArgsExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
