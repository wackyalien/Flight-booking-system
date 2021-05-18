package com.micro.flightsearches.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.micro.flightsearches.models.Audience;
import com.micro.flightsearches.models.AuthenticationRequest;
import com.micro.flightsearches.models.AuthenticationResponse;
import com.micro.flightsearches.models.AvailableFlight;
import com.micro.flightsearches.models.Flight;
import com.micro.flightsearches.repository.AudienceRepo;
import com.micro.flightsearches.service.FlightService;
import com.micro.flightsearches.service.MyUserDetailsService;
import com.micro.flightsearches.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/flight")
public class MyControl{

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    JwtUtil jwtTokenUtil;

    @Autowired
    private FlightService flightService;

    @Autowired
    private AudienceRepo audienceRepo;
    
    @GetMapping("/hello")
    public String run(){
        return "Running Flight Search MicroService";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/")
    public String postData(@RequestBody @Valid Flight flight){
            return this.flightService.postData(flight);
    }

    @PostMapping("/audience")
    public String add(@RequestBody Audience audience){
        this.audienceRepo.save(audience);
        return "Data updated";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    public List<Flight> getAllFlight(){
        return this.flightService.getAllFlight();
    }

    @GetMapping("/{flightNo}")
    public Flight getData(@PathVariable String flightNo){
        return this.flightService.getData(flightNo);
    }

    @GetMapping("/flightsearch")
    public ResponseEntity<AvailableFlight> getSearchFlightWithFare(@RequestParam @Valid String flightfrom,@RequestParam @Valid String flightto,@RequestParam @Valid String date){
        var availableflight= this.flightService.getSearchFlightWithFare(flightfrom, flightto, date);
        if (availableflight.getAvailableflight().size()==0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(availableflight);
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}