package com.micro.flightsearches.repository;

import com.micro.flightsearches.models.Flight;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface flightrepo extends MongoRepository<Flight,Integer>{
    Flight findByFlightno(String flightno);
}




