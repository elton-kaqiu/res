package com.eltonkaqiu.resbackend.exceptions;

public class NotAuthorizedException extends ApiRuntimeException{
    public NotAuthorizedException() {
    }

    public NotAuthorizedException(String message) {
        super(message);
    }
}
