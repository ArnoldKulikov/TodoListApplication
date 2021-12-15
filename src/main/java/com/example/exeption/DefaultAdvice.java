package com.example.exeption;

import com.example.models.common.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class DefaultAdvice {

    @ExceptionHandler(MyException.class)
    public ResponseEntity<ErrorDto> handleMyException(MyException e) {
        log.error(e.getMessage());
        if (log.isDebugEnabled()) {
            log.debug(ExceptionUtils.getStackTrace(e));
        }
        ErrorDto error = new ErrorDto("Bad Request", e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ErrorDto> handleEmptyResultDataAccessException(EmptyResultDataAccessException e) {
        if (log.isDebugEnabled()) {
            log.debug(ExceptionUtils.getStackTrace(e));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
