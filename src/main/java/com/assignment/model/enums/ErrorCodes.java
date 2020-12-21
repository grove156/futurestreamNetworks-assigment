package com.assignment.model.enums;

import lombok.Getter;

public enum ErrorCodes {
    DataNotFoundException(1,"Sorry, data could not be found",404),
    MethodArgumentTypeMismatchException(2,"Sorry, wrong date or time",400),
    NumberFormatException(3,"Sorry, second parameter isn't a number",400),
    MethodArgumentNotValidException(4,"",400);
    @Getter
    private int code;

    @Getter
    private String message;

    @Getter
    private int status;

    private ErrorCodes(int code, String message, int status){
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
