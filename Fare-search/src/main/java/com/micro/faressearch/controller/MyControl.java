package com.micro.faressearch.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.micro.faressearch.models.Fare;
import com.micro.faressearch.models.FlightFare;
import com.micro.faressearch.service.FareServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fare")
public class MyControl{

    @Autowired
    private FareServices fareServices;

    @GetMapping("/hello")
    public String run() {
        return "Running Fare Search MicroService";
    }

    @PostMapping("/")
    public String postData(@RequestBody @Valid Fare fare) {
        return this.fareServices.postData(fare);
    }

    @GetMapping("/")
    public List<Fare> getData() {
        return this.fareServices.getData();
    }

    @GetMapping("{flightno}")
    public Fare getFlight(@PathVariable String flightno) {
        return this.fareServices.getFlight(flightno);
    }

    @GetMapping("/getAll")
    public FlightFare getPrice() {
        return this.fareServices.getPrice();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
    
}
