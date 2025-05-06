package com.intensis.api.vendas.exception.handlers;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
