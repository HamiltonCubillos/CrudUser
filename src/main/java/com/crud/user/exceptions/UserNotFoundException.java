package com.crud.user.exceptions;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(final String s) {
        super(s);
    }

    @Override
    public String toString() {
        return "UserNotFoundException [" + getMessage() + "]";
    }
    
    
}
