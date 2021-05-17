package com.micro.Booking.Controller;

import java.util.Random;

import com.micro.Booking.config.MessagingConfig;
import com.micro.Booking.models.AuthenticationRequest;
import com.micro.Booking.models.AuthenticationResponse;
import com.micro.Booking.models.CheckingMessage;
import com.micro.Booking.models.User;
import com.micro.Booking.repository.Bookingrepo;
import com.micro.Booking.service.BookingService;
import com.micro.Booking.service.MyUserDetailsService;
import com.micro.Booking.util.JwtUtil;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class mycontrols {

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
    public String getspecificflight(@PathVariable String flightno, @RequestParam String firstname,@RequestParam String lastname,@RequestParam String gender,@RequestParam String email){
        return this.bookingService.getspecificflight(flightno, firstname, lastname, gender, email);
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/search/{id}")
    public ResponseEntity<User> run(@PathVariable int id){
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
