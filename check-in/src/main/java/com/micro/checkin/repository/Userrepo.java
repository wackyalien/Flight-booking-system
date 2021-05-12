package com.micro.checkin.repository;

import com.micro.checkin.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Userrepo extends MongoRepository<User,Integer>{
    User findById(int id);
}