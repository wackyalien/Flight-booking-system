package com.micro.Flightsearch.service;

import java.util.*;
import java.util.stream.Collectors;
import com.micro.Flightsearch.models.AvailableFlight;
import com.micro.Flightsearch.models.Fare;
import com.micro.Flightsearch.models.Flight;
import com.micro.Flightsearch.models.FlightFare;
import com.micro.Flightsearch.repository.flightrepo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class FlightServiceImpl implements FlightService{

    Logger logger = Logger.getLogger(FlightServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private flightrepo flightrepo;

    @Override
    public String postdata(Flight flight) {
        logger.info("post data of flight");
        try{
            this.flightrepo.save(flight);
            return "Successfully added";
        }
        catch(Exception e){
            return "Wrong flight data";
        }
    }

    @Override
    public List<Flight> getdata() {
        return this.flightrepo.findAll();
    }

    @Override
    public List<Flight> getallflight() {
        logger.info("show all flights");
        var flightfare = restTemplate.getForObject("http://fare-search/fare/getAll/", FlightFare.class);
        var f=flightfare.getFlightFare().stream().map(i->i.getFlightno()).collect(Collectors.toList());
        for(var i:this.flightrepo.findAll()){
            if(f.contains(i.getFlightno())){
                var index = i.getId();
                i.setFare(flightfare.getFlightFare().get(index-1).getFprice());
            }
        }
        return this.flightrepo.findAll();
    }

    @Override
    public AvailableFlight getsearchflightwithfare(String flightfrom, String flightto, String date) {
        logger.info("flight search by from, to and date");
        List<Flight> AvailableFLightList = new ArrayList<>();
        var flightfare = restTemplate.getForObject("http://fare-search/fare/getAll/", FlightFare.class);
        var f=flightfare.getFlightFare().stream().map(i->i.getFlightno()).collect(Collectors.toList());
        for(var i:this.flightrepo.findAll()){
            if(i.getFlightfrom().equals(flightfrom) 
                && i.getFlightto().equals(flightto)
                && i.getDate().equals(date)
                && f.contains(i.getFlightno())){
                    var index = i.getId();
                    i.setFare(flightfare.getFlightFare().get(index-1).getFprice());
                    AvailableFLightList.add(i);
            }
        }
        AvailableFlight availableFlight = new AvailableFlight(AvailableFLightList);
        return availableFlight;
    }

    @Override
    public Flight getdata(String flightNo) {
        logger.info("search flight by flightno");
        var fare = this.restTemplate.getForObject("http://fare-search/fare/"+flightNo, Fare.class);
        Flight flight=this.flightrepo.findByFlightno(flightNo);
        flight.setFare(fare.getFprice());
        return flight;
    }
    
}