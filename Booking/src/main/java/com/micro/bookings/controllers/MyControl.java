package com.micro.bookings.controllers;

import java.util.Random;

import javax.validation.Valid;

import com.micro.bookings.config.MessagingConfig;
import com.micro.bookings.models.AuthenticationRequest;
import com.micro.bookings.models.AuthenticationResponse;
import com.micro.bookings.models.CheckingMessage;
import com.micro.bookings.models.User;
import com.micro.bookings.repository.Bookingrepo;
import com.micro.bookings.service.BookingService;
import com.micro.bookings.service.MyUserDetailsService;
import com.micro.bookings.util.JwtUtil;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/booking")
public class MyControl{

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    JwtUtil jwtTokenUtil;

    @Autowired
    private BookingService bookingService;

    @Autowired
    Bookingrepo bookingrepo;

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        Random rand = new Random();
        System.out.println(rand.nextInt(2-0) + 0);
        return new ResponseEntity<String>("running booking service",HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/{flightno}")
    public String getSpecificFlight(@PathVariable String flightno, @Valid @RequestParam String firstname,@Valid @RequestParam String lastname,@Valid @RequestParam String gender,@Valid @RequestParam String email){
        return this.bookingService.getSpecificFlight (flightno, firstname, lastname, gender, email);
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/search/{id}")
    public ResponseEntity<User> runRefDetail(@PathVariable int id){
        User user =this.bookingService.getRefDetail(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(CheckingMessage message){
        int id = message.getId();
        User user = this.bookingrepo.findById(id);
        System.out.println(user);
        user.setCheckin(message.getMessage());
        this.bookingrepo.save(user);
        System.out.println(user);  
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
