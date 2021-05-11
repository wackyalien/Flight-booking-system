package com.micro.Faresearch.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.micro.Faresearch.Services.FareServices;
import com.micro.Faresearch.models.Fare;
import com.micro.Faresearch.models.FlightFare;

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
public class mycontrols {

    @Autowired
    private FareServices fareServices;

    @GetMapping("/hello")
    public String run() {
        return "Running Fare Search MicroService";
    }

    @PostMapping("/")
    public String postdata(@RequestBody @Valid Fare fare) {
        return this.fareServices.postdata(fare);
    }

    @GetMapping("/")
    public List<Fare> getdata() {
        return this.fareServices.getdata();
    }

    @GetMapping("{flightno}")
    public Fare getflight(@PathVariable String flightno) {
        return this.fareServices.getflight(flightno);
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
