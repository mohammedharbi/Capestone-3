package com.example.wiqaya.ApiResponse;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}