package com.example.data.models.common;

import lombok.Getter;

@Getter
public class ErrorDto {
    private String errorCode;
    private String message;

    public ErrorDto(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

}
