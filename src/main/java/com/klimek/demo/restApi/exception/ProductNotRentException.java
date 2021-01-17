package com.klimek.demo.restApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Vehicle isn't rent. Wrong data.")
public class ProductNotRentException extends RuntimeException {
    private static final long serialVersionUID = 2L;
}
