package com.vendor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class VendorExceptionHandler {

    @ExceptionHandler(value = {VendorNotFoundException.class})
    public ResponseEntity<Object> handleVendorNotFoundException(VendorNotFoundException vendorNotFoundException) {
        VendorException vendorException = new VendorException(
                vendorNotFoundException.getMessage(),
                vendorNotFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(vendorException, HttpStatus.NOT_FOUND);
    }
}
