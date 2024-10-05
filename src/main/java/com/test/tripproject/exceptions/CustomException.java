package com.test.tripproject.exceptions;

public class CustomException extends RuntimeException{

    public CustomException(String exceptionDetails){
        super(exceptionDetails);
    }
}
