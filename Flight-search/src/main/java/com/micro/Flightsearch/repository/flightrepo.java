package com.micro.Flightsearch.repository;

import com.micro.Flightsearch.models.Flight;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface flightrepo extends MongoRepository<Flight,Integer>{
    Flight findByFlightno(String flightno);
}




