package com.inits.productservice.constants;

/**
 * Created by Odinaka Onah on on 02 Feb, 2021.
 */
public enum ResponseCode {
    SUCCESS(200,"00","{} Successful"),
    OK(200,"00","OK"),
    ITEM_NOT_FOUND(404,"04","Sorry... {} record cannot be found on our system,please make sure you pass the correct value"),
    BAD_REQUEST(400,"16","Invalid request: {} ... please try again "),
    OUT_OF_STOCK(403,"21","We are currently running of out stock for this product, there are only {} available at the moment."),
    REQUEST_TIMEOUT(200,"50","Sorry...request is taking longer than expected.. please trying again later"),
    UNAVAILABLE1(503,"100","Sorry we cannot process {} at the moment, please try again later or call any of our service help desk for assistance");
    String value;
    Integer code;
    String statusCode;
    ResponseCode(Integer code, String statusCode, String value) {
        this.value = value;
        this.code = code;
        this.statusCode =statusCode;
    }

    public String getValue() {
        return value;
    }

    public Integer getCode() {
        return code;
    }

    public String getStatusCode() {
        return statusCode;
    }
}
