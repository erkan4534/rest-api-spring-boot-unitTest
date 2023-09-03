package com.vendor.exception;

import org.springframework.http.HttpStatus;

public record VendorException(String message, Throwable throwable,
                              HttpStatus httpStatus) {
}
