package com.micro.faressearch.repository;

import com.micro.faressearch.models.Fare;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface farerepo extends MongoRepository<Fare,Integer>{
    Fare findByFlightno(String flightno);
}
