package com.micro.Faresearch.Services;

import java.util.*;
import com.micro.Faresearch.models.Fare;
import com.micro.Faresearch.models.FlightFare;
import com.micro.Faresearch.repository.farerepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FareServicesImpl implements FareServices {

    @Autowired
    private farerepo farerepo;

    @Override
    public String postdata(Fare fare) {
        this.farerepo.save(fare);
        return "Successfully added";
    }

    @Override
    public List<Fare> getdata() {
        return this.farerepo.findAll();
    }
    

    @Override
    public FlightFare getPrice() {
        FlightFare p = new FlightFare(this.farerepo.findAll());
        return p;
    }

    @Override
    public Fare getflight(String flightno) {
        return this.farerepo.findByFlightno(flightno);
    }
    
}
