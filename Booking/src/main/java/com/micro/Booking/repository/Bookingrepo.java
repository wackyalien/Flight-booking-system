package com.micro.Booking.repository;
import com.micro.Booking.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Bookingrepo extends MongoRepository<User,Integer>{
    User findById(int id);
}
