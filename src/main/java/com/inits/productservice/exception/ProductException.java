package com.inits.productservice.exception;

import lombok.Data;

/**
 * Created by Odinaka Onah on 02 Feb, 2021.
 */
@Data
public class ProductException extends Exception {
    private final Integer httpCode;
    private String statusCode;
    public ProductException(Integer httpCode, String message, String statusCode)
    {
        super(message);
        this.httpCode = httpCode;
        this.statusCode = statusCode;
    }
}