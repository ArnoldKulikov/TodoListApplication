package com.example.models.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorDto {
    private final String errorCode;
    private final String message;

}
