package com.api.exception;

import com.api.payload.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    public  ResponseEntity<ErrorDto> resourceNotFound(
            ResourceNotFound r,
            WebRequest request// automatically get the object address from the springboot
    ){
            ErrorDto error = new ErrorDto(r.getMessage(), new Date(), request.getDescription(true));
            return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
