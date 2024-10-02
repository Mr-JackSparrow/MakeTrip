package com.test.tripproject.exceptions;

public class CustomUserException extends RuntimeException{

    public CustomUserException(String exceptionDetails){
        super(exceptionDetails);
    }
}
