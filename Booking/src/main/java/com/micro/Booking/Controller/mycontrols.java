package com.micro.Booking.Controller;

import com.micro.Booking.models.User;
import com.micro.Booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class mycontrols {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/hello")
    public String hello(){
        return "running booking service";
    }

    @GetMapping("/{flightno}")
    public String getspecificflight(@PathVariable String flightno, @RequestParam String firstname,@RequestParam String lastname,@RequestParam String gender,@RequestParam String email){
        return this.bookingService.getspecificflight(flightno, firstname, lastname, gender, email);
    }

    @GetMapping("/search/{id}")
    public User run(@PathVariable int id){
        return this.bookingService.getRefDetail(id);
    }
}

// @GetMapping("/{flightno}")
//     public String getspecificflight(@PathVariable String flightno, @RequestBody User user){
//         return this.bookingService.getspecificflight(flightno, user);
//     }
