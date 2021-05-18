package com.micro.faressearch.service;

import java.util.*;

import com.micro.faressearch.models.Fare;
import com.micro.faressearch.models.FlightFare;
import com.micro.faressearch.repository.farerepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FareServicesImpl implements FareServices {

    @Autowired
    private farerepo farerepo;

    @Override
    public String postData(Fare fare) {
        this.farerepo.save(fare);
        return "Successfully added";
    }

    @Override
    public List<Fare> getData() {
        return this.farerepo.findAll();
    }
    

    @Override
    public FlightFare getPrice() {
        FlightFare p = new FlightFare(this.farerepo.findAll());
        return p;
    }

    @Override
    public Fare getFlight(String flightno) {
        return this.farerepo.findByFlightno(flightno);
    }
    
}
