package com.micro.cloudgateway.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/message")
    public String FlightFallback() {
        return "Flight Service is down at this moment, please try later."; 
    }
      
}
