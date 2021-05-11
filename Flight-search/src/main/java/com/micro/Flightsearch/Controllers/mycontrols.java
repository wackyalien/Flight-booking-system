package com.micro.Flightsearch.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.micro.Flightsearch.models.Audience;
import com.micro.Flightsearch.models.AvailableFlight;
import com.micro.Flightsearch.models.Flight;
import com.micro.Flightsearch.repository.AudienceRepo;
import com.micro.Flightsearch.service.FlightService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flight")
public class mycontrols {

    @Autowired
    private FlightService flightService;

    @Autowired
    private AudienceRepo audienceRepo;
    
    @GetMapping("/hello")
    public String run(){
        return "Running Flight Search MicroService";
    }

    @PostMapping("/")
    public String postdata(@RequestBody @Valid Flight flight){
            return this.flightService.postdata(flight);
    }

    @PostMapping("/audience")
    public String add(@RequestBody Audience audience){
        this.audienceRepo.save(audience);
        return "Data updated";
    }

    @GetMapping("/all")
    public List<Flight> getallflight(){
        return this.flightService.getallflight();
    }

    @GetMapping("/{flightNo}")
    public Flight getdata(@PathVariable String flightNo){
        return this.flightService.getdata(flightNo);
    }

    @GetMapping("/flightsearch")
    public ResponseEntity<AvailableFlight> getsearchflightwithfare(@RequestParam String flightfrom,@RequestParam String flightto,@RequestParam String date){
        var availableflight= this.flightService.getsearchflightwithfare(flightfrom, flightto, date);
        if (availableflight.getAvailableflight().size()==0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(availableflight);
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