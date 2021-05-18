package com.micro.bookings.repository;
import com.micro.bookings.models.User;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface Bookingrepo extends MongoRepository<User,Integer>{
    User findById(int id);
}
