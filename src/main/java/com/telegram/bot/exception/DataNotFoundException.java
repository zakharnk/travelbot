package com.telegram.bot.exception;

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}