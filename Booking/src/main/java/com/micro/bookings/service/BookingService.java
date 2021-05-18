package com.micro.bookings.service;

import com.micro.bookings.exception.IdNotFoundException;
import com.micro.bookings.models.User;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface BookingService {
    // public String getspecificflight(@PathVariable String flightno, @RequestBody User user);
    public User getRefDetail(@PathVariable int id) throws IdNotFoundException;
    public String getSpecificFlight(@PathVariable String flightno, @RequestParam String firstname,@RequestParam String lastname,@RequestParam String gender,@RequestParam String email);
}
