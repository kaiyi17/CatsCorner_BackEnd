package com.Cat.sCorner.Cat.sCorner.exception;

import lombok.Getter;

public class BizException extends RuntimeException{

    @Getter
    private final Status status;

    public BizException(Status status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

    public void throwIfNot(Boolean b) {
        if (!Boolean.TRUE.equals(b))
            throw this;
    }

    public void throwIf(Boolean b) {
        if (Boolean.TRUE.equals(b))
            throw this;
    }

    public void throwIt() {
        this.throwIfNot(false);
    }}
