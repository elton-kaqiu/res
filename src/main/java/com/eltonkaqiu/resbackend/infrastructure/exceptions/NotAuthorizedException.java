package com.eltonkaqiu.resbackend.infrastructure.exceptions;

public class NotAuthorizedException extends ApiRuntimeException{
    public NotAuthorizedException() {
    }

    public NotAuthorizedException(String message) {
        super(message);
    }
}
