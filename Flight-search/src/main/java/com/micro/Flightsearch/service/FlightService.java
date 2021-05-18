package com.micro.Flightsearch.service;

import java.util.List;

import com.micro.Flightsearch.models.AvailableFlight;
import com.micro.Flightsearch.models.Flight;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface FlightService {
    public List<Flight> getAllFlight();
    public List<Flight> getData();
    public Flight getData(@PathVariable String flightNo);
    public String postData(@RequestBody Flight flight);
    public AvailableFlight getSearchFlightWithFare(@RequestParam String flightfrom,@RequestParam String flightto,@RequestParam String date);
}
