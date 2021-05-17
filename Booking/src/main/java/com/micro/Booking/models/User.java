package com.micro.Booking.models;

import javax.validation.constraints.NotNull;

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
    @NotNull(message = "Firstname is mandatory")
    private String firstname;
    @NotNull(message = "Lastname is mandatory")
    private String lastname;
    @NotNull(message = "Gender is mandatory")
    private String gender;
    @NotNull(message = "Email is mandatory")
    private String email;
    private String flightno;
    private String flightfrom;
    private String flightto;
    private String date;
    private int fare;
    private String checkin;
    
}
