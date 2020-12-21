package com.assignment.controller.exceptionAdvisor;

import com.assignment.controller.exceptionAdvisor.exceptions.DataNotFoundException;
import com.assignment.model.enums.ErrorCodes;
import com.assignment.model.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorResponse DataNotFoundError(DataNotFoundException e){
        ErrorResponse errorResponse = new ErrorResponse().builder()
                .errorCode(ErrorCodes.valueOf("DataNotFoundException").getCode())
                .message(ErrorCodes.valueOf("DataNotFoundException").getMessage())
                .status(ErrorCodes.valueOf("DataNotFoundException").getStatus())
                .timeStamp(LocalDateTime.now())
                .build();

        return errorResponse;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse MethodArgumentNotValidError(MethodArgumentNotValidException e){
        ErrorResponse errorResponse = new ErrorResponse().builder()
                .timeStamp(LocalDateTime.now())
                .errorCode(ErrorCodes.valueOf("MethodArgumentNotValidException").getCode())
                .message(e.getBindingResult().getAllErrors().get(0).getDefaultMessage())
                .status(ErrorCodes.valueOf("MethodArgumentNotValidException").getStatus())
                .build();


        return errorResponse;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse MethodArgumentTypeMismatchError(MethodArgumentTypeMismatchException e){
        ErrorResponse errorResponse = new ErrorResponse().builder()
                .timeStamp(LocalDateTime.now())
                .errorCode(ErrorCodes.valueOf("MethodArgumentTypeMismatchException").getCode())
                .message(ErrorCodes.valueOf("MethodArgumentTypeMismatchException").getMessage())
                .status(ErrorCodes.valueOf("MethodArgumentTypeMismatchException").getStatus())
                .build();

        return errorResponse;
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse NumberFormatError(NumberFormatException e){
        ErrorResponse errorResponse = new ErrorResponse().builder()
                .timeStamp(LocalDateTime.now())
                .errorCode(ErrorCodes.valueOf("NumberFormatException").getCode())
                .message(ErrorCodes.valueOf("NumberFormatException").getMessage())
                .status(ErrorCodes.valueOf("NumberFormatException").getStatus())
                .build();

        return errorResponse;
    }
}
