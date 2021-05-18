package com.micro.flightsearches.models;

import javax.validation.constraints.*;

// import javax.validation.constraints.NotNull;

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
public class Flight {
    @Id
    private int id;
    // @Size(min=5, max=7, message="flightno invalid")
    @NotBlank(message = "flightno is mandatory")
    private String flightno;
    // @Size(min=1, max=5, message="flightfrom invalid")
    @NotBlank(message = "flightfrom is mandatory")
    private String flightfrom;
    // @Size(min=1, max=5, message="flightto invalid")
    @NotBlank(message = "flightto is mandatory")
    private String flightto;
    @Size(min=1, max=15, message="date invalid")
    @NotBlank(message = "date is mandatory")
    private String date;
    private int fare;
    
}   