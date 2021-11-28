package com.example.data.models;

import lombok.Getter;
import org.springframework.web.bind.annotation.ResponseBody;

@Getter
@ResponseBody
public class ErrorDto {
    private String errorCode;
    private String message;

    public ErrorDto(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

}
