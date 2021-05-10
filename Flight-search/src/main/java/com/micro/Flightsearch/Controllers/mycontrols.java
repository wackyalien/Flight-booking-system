package com.micro.Flightsearch.Controllers;

import java.util.List;

import com.micro.Flightsearch.models.Audience;
import com.micro.Flightsearch.models.AvailableFlight;
import com.micro.Flightsearch.models.Flight;
import com.micro.Flightsearch.repository.AudienceRepo;
import com.micro.Flightsearch.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String postdata(@RequestBody Flight flight){
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

}