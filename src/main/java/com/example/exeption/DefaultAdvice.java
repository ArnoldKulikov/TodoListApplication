package com.example.exeption;

import com.example.data.models.common.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class DefaultAdvice {

    @ExceptionHandler(MyException.class)
    public ResponseEntity<ErrorDto> handleException(MyException e) {
        log.error(e.getMessage());
        ErrorDto error = new ErrorDto("Bad Request", e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
