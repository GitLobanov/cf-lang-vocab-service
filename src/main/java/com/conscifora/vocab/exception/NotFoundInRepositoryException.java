package com.conscifora.vocab.exception;

public class NotFoundInRepositoryException extends RuntimeException{

    public NotFoundInRepositoryException(String message) {
        super(message);
    }
}
