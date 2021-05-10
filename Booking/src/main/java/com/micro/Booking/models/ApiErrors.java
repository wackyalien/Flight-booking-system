package com.micro.Booking.models;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiErrors {
    String message;
    List<String> details;
    HttpStatus status;
    LocalDateTime date;
    
    public ApiErrors() {
    }

    public ApiErrors(String message, List<String> details, HttpStatus status, LocalDateTime date) {
        this.message = message;
        this.details = details;
        this.status = status;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }


}
