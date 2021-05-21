package com.micro.checkin.services;

import java.util.Random;

import com.micro.checkin.exceptions.AlreadyCheckInException;
import com.micro.checkin.exceptions.IdNotFoundException;
import com.micro.checkin.filters.JwtRequestFilter;
import com.micro.checkin.models.CheckingMessage;
import com.micro.checkin.repository.AudienceRepo;
import com.micro.checkin.repository.Userrepo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class checkinServiceImpl implements checkinService{

    Logger logger = Logger.getLogger(checkinServiceImpl.class);

    @Autowired
    Userrepo userrepo;

    @Autowired
    private AudienceRepo audienceRepo;

    @Autowired
    private JwtRequestFilter filters;

    public char toChar(int c) {
        return (char)c;
    }

    @Override
    public CheckingMessage checkIn(int id) { 
        
        logger.info("Sending asyn message to booking microservice using rabbitmq");

        var loggedInUser=filters.getLoggedInUserName();
        var audienceList = this.audienceRepo.findByUsername(loggedInUser).getBookingid();
        if(audienceList.contains(id)){
            var currentidobj = userrepo.findById(id);
            if(currentidobj.getCheckin()==null){
                Random rand = new Random();
                var rand1 = rand.nextInt(50-1) + 1;
                var rand2 = rand.nextInt(122-97)+97;
                var checkin = "Checked In, Seat Number is "+rand1+toChar(rand2)+", checkedin id is "+ id;
                CheckingMessage checkingMessage = new CheckingMessage(id,checkin);
                return checkingMessage;
            }
            else{
                logger.error("Checkin id is failed");
                throw new AlreadyCheckInException("User already Checked In || "+currentidobj.getCheckin());
            }  
        }
        throw new IdNotFoundException("Invalid Id");    
    }  
}
