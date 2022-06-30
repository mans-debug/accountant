package com.andersen.exception;

public class FailedUpdate extends RuntimeException {
    public FailedUpdate(String message) {
        super(message);
    }
}
