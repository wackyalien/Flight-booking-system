package com.micro.checkin.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    
    private int id;
    private String firstname;
    private String lastname;
    private String gender;
    private String flightno;
    private String flightfrom;
    private String flightto;
    private String date;
    private String fare;
    private String checkin;

}
