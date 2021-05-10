package com.micro.Flightsearch.service;

import java.util.List;

import com.micro.Flightsearch.models.AvailableFlight;
import com.micro.Flightsearch.models.Flight;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface FlightService {
    public List<Flight> getallflight();
    public List<Flight> getdata();
    public Flight getdata(@PathVariable String flightNo);
    public String postdata(@RequestBody Flight flight);
    public AvailableFlight getsearchflightwithfare(@RequestParam String flightfrom,@RequestParam String flightto,@RequestParam String date);
}
