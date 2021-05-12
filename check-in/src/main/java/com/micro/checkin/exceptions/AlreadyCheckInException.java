package com.micro.checkin.exceptions;

public class AlreadyCheckInException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public AlreadyCheckInException() {
    }

    public AlreadyCheckInException(String message) {
        super(message);
    }

}
