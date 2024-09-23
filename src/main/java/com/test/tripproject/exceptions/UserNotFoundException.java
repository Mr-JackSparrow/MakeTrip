package com.test.tripproject.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(){
        super("User Not Found");
    }
}
