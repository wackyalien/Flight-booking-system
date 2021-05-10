package com.micro.Booking.repository;

import com.micro.Booking.models.Audience;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AudienceRepo extends MongoRepository<Audience,Integer>{
    Audience findByUsername(String username);
}
