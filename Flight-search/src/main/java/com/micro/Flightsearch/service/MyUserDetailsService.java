package com.micro.Flightsearch.service;

import java.util.Arrays;

import com.micro.Flightsearch.models.Audience;
import com.micro.Flightsearch.repository.AudienceRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private AudienceRepo audienceRepo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Audience audience = this.audienceRepo.findByUsername(userName);
        GrantedAuthority authority=new SimpleGrantedAuthority(audience.getRole());
        return new User(audience.getUsername(),audience.getPassword(),Arrays.asList(authority));
    }
}

