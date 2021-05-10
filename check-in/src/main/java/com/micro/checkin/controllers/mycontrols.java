package com.micro.checkin.controllers;

import com.micro.checkin.config.MessagingConfig;
import com.micro.checkin.models.AuthenticationRequest;
import com.micro.checkin.models.AuthenticationResponse;
import com.micro.checkin.models.CheckingMessage;
import com.micro.checkin.services.MyUserDetailsService;
import com.micro.checkin.services.checkinService;
import com.micro.checkin.util.JwtUtil;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class mycontrols {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    JwtUtil jwtTokenUtil;

    @Autowired
    private checkinService checkinservice;

    @Autowired
    private RabbitTemplate template;

    @GetMapping("/hello")
    public String run(){
        return "running checkin service";
    }
    
    @GetMapping("/{id}/checkin")
    public String checkin(@PathVariable int id){
        var obj = this.checkinservice.checkin(id);
        template.convertAndSend(MessagingConfig.QUEUE,obj);
        System.out.println("Check Console!!");
        return obj.getMessage();
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e){
            throw new Exception("Incorrect UserName or Password !",e);
        }

        final UserDetails userDetails = myUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
