package com.telegram.bot.exception;

public class DeleteException extends RuntimeException {

    public DeleteException(String errorMessage) {
        super(errorMessage);
    }
}