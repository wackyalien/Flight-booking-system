package com.micro.checkin.exceptions;

public class IdNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    
    public IdNotFoundException() {
    }

    public IdNotFoundException(String message) {
        super(message);
    }
    
}