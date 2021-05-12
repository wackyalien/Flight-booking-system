package com.micro.Booking.models;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrors {
    
    String message;
    List<String> details;
    HttpStatus status;
    LocalDateTime date;
 
}
