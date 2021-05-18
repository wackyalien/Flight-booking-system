package com.micro.Booking.service;

import com.micro.Booking.exception.IdNotFoundException;
import com.micro.Booking.models.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface BookingService {
    // public String getspecificflight(@PathVariable String flightno, @RequestBody User user);
    public User getRefDetail(@PathVariable int id) throws IdNotFoundException;
    public String getSpecificFlight(@PathVariable String flightno, @RequestParam String firstname,@RequestParam String lastname,@RequestParam String gender,@RequestParam String email);
}
