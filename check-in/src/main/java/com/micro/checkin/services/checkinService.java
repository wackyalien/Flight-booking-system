package com.micro.checkin.services;

import com.micro.checkin.exceptions.AlreadyCheckInException;
import com.micro.checkin.exceptions.IdNotFoundException;
import com.micro.checkin.models.CheckingMessage;

import org.springframework.web.bind.annotation.PathVariable;

public interface checkinService {
    public CheckingMessage checkin(@PathVariable int id) throws AlreadyCheckInException,IdNotFoundException;
}
