package com.conscifora.common.exception;

public class NotFoundInRepositoryException extends RuntimeException{

    public NotFoundInRepositoryException(String message) {
        super(message);
    }
}
