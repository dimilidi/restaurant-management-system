package com.lididimi.restaurant.exception.common;

public class SomethingWentWrongException extends RuntimeException {
    public SomethingWentWrongException(String message, Throwable cause) {
        super(message, cause);
    }
}

