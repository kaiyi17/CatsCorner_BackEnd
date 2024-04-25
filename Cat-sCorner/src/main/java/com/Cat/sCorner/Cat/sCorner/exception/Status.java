package com.Cat.sCorner.Cat.sCorner.exception;

import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

public enum Status implements Supplier<BizException> {
    OK,
    Unknown(HttpStatus.INTERNAL_SERVER_ERROR),
    BadRequest(HttpStatus.BAD_REQUEST),
    Unauthorized(HttpStatus.UNAUTHORIZED),
    NotFound(HttpStatus.NOT_FOUND),
    TooManyRequests(HttpStatus.TOO_MANY_REQUESTS),
    Retry(HttpStatus.INTERNAL_SERVER_ERROR),
    ;
    private final HttpStatus httpStatus;

    Status() {
        this(HttpStatus.OK);
    }

    Status(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }


    @Override
    public BizException get() {
        return BizExceptionKit.of(this, this.name());
    }
}
