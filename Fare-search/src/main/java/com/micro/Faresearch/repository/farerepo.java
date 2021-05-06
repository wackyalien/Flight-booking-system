package com.micro.Faresearch.repository;

import com.micro.Faresearch.models.Fare;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface farerepo extends MongoRepository<Fare,Integer>{
    Fare findByFlightno(String flightno);
}
