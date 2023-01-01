package com.fasttrackit.BugetPersonal.controller;

import com.fasttrackit.BugetPersonal.exception.ParseException;
import com.fasttrackit.BugetPersonal.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)

    public ErrorResponse handlerRuntimeException(ResourceNotFoundException exception){
        return  new ErrorResponse(exception.getMessage() + " " + exception.getEntityId());

    }


    @ExceptionHandler(ParseException.class)
    public ErrorResponse parseException(ParseException parseException){
        return new ErrorResponse(parseException.getMessage() + " " + parseException.getErrorOffset());

    }
    record ErrorResponse(String message){

    }
}
