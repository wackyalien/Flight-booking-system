package com.micro.bookings.models;

import javax.validation.constraints.*;

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
    @NotBlank(message = "Firstname is mandatory")
    private String firstname;
    @NotBlank(message = "Lastname is mandatory")
    private String lastname;
    @NotBlank(message = "Gender is mandatory")
    private String gender;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid emailId")
    private String email;
    private String flightno;
    private String flightfrom;
    private String flightto;
    private String date;
    private int fare;
    private String checkin;
    
}
