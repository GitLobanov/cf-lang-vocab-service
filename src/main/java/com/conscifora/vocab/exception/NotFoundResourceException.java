package com.conscifora.vocab.exception;

public class NotFoundResourceException extends RuntimeException{

    String message;

    public NotFoundResourceException(String message) {
        super(message);
    }

}
