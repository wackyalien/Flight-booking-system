package com.micro.checkin.services;

import java.util.Random;

import com.micro.checkin.models.CheckingMessage;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class checkinServiceImpl implements checkinService{

    Logger logger = Logger.getLogger(checkinServiceImpl.class);

    public char toChar(int c) {
        return (char)c;
    }

    @Override
    public CheckingMessage checkin(int id) { 
        logger.info("Sending asyn message to booking microservice using rabbitmq");
        Random rand = new Random();
        var rand1 = rand.nextInt(50-1) + 1;
        var rand2 = rand.nextInt(122-97)+97;
        var checkin = "Checked In, Seat Number is "+rand1+toChar(rand2)+", checkedin id is "+ id;
        CheckingMessage obj = new CheckingMessage(id,checkin);
        System.out.println(obj);
        return obj;
    }
    
}
