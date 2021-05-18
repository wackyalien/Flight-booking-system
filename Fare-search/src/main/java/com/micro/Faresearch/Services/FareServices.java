package com.micro.Faresearch.Services;

import java.util.List;

import com.micro.Faresearch.models.Fare;
// import com.micro.Faresearch.models.Fare;
import com.micro.Faresearch.models.FlightFare;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface FareServices {
    public FlightFare getPrice();
    public List<Fare> getData();
    public String postData(@RequestBody Fare fare);
    public Fare getFlight(@PathVariable String flightno);
}
