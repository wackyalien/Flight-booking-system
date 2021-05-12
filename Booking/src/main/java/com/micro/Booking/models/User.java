package com.micro.Booking.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    
    @Id
    private int id;
    private String firstname;
    private String lastname;
    private String gender;
    private String email;
    private String flightno;
    private String flightfrom;
    private String flightto;
    private String date;
    private int fare;
    private String checkin;
    
}
