package com.telegram.bot.exception;

public class SaveException extends RuntimeException {

    public SaveException(String errorMessage) {
        super(errorMessage);
    }
}