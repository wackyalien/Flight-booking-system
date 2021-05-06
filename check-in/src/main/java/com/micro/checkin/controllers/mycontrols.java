package com.micro.checkin.controllers;

import com.micro.checkin.services.checkinService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class mycontrols {

    @Autowired
    private checkinService checkinservice;

    @GetMapping("/hello")
    public String run(){
        return "running checkin service";
    }
    
    @GetMapping("/{id}/checkin")
    public String checkin(@PathVariable int id){
        return this.checkinservice.checkin(id);
    }

}
