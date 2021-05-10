package com.micro.checkin.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import com.micro.checkin.models.Audience;
import com.micro.checkin.repository.AudienceRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;

@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private AudienceRepo audienceRepo;
    
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // return new User("foo","foo",new ArrayList<>());
        Audience audience = this.audienceRepo.findByUsername(userName);
        return new User(audience.getUsername(),audience.getPassword(),new ArrayList<>());
    }
}