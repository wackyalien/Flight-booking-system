package com.micro.checkin.services;

import java.util.Random;

import com.micro.checkin.models.User;
import com.micro.checkin.repository.Userrepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class checkinServiceImpl implements checkinService{

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Userrepo userRepo;

    public char toChar(int c) {
        return (char)c;
    }

    @Override
    public String checkin(int id) {
        User user=this.restTemplate.getForObject("http://booking-service/booking/search/"+id,User.class);
        Random rand = new Random();
        var rand1 = rand.nextInt(50-1) + 1;
        var rand2 = rand.nextInt(122-97)+97;
        var checkin = "Checked In, Seat Number is "+rand1+toChar(rand2)+", checkedin id is "+ user.getId();
        user.setCheckin(checkin);
        this.userRepo.save(user);
        return checkin;
    }
    
}
