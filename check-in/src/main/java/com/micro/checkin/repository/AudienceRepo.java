package com.micro.checkin.repository;

import com.micro.checkin.models.Audience;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AudienceRepo extends MongoRepository<Audience,Integer>{
    Audience findByUsername(String username);
}
