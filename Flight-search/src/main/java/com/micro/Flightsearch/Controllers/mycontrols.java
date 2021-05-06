package com.micro.Flightsearch.Controllers;

import java.util.List;

import com.micro.Flightsearch.models.AvailableFlight;
import com.micro.Flightsearch.models.Flight;
import com.micro.Flightsearch.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @GetMapping("/hello")
    public String run(){
        return "Running Flight Search MicroService";
    }

    @PostMapping("/")
    public String postdata(@RequestBody Flight flight){
        return this.flightService.postdata(flight);
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
    public AvailableFlight getsearchflightwithfare(@RequestParam String flightfrom,@RequestParam String flightto,@RequestParam String date){
        return this.flightService.getsearchflightwithfare(flightfrom, flightto, date);
    }

}


    // @GetMapping("/flightsearch")
    // public AvailableFlight getsearchflightwithfare(@RequestBody Flight flight){
    //     return this.flightService.getsearchflightwithfare(flight);
    // }