package com.micro.checkin.services;

import org.springframework.web.bind.annotation.PathVariable;

public interface checkinService {
    public String checkin(@PathVariable int id);
}
