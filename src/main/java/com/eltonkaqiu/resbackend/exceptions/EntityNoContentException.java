package com.eltonkaqiu.resbackend.exceptions;

public class EntityNoContentException extends ApiRuntimeException{
    public EntityNoContentException() {
    }

    public EntityNoContentException(String message) {
        super(message);
    }
}
