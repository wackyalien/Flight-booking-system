package com.micro.Flightsearch.models;

// import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
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
    @NotEmpty(message = "flightno is mandatory")
    private String flightno;
    @NotEmpty(message = "flightfrom is mandatory")
    private String flightfrom;
    @NotEmpty(message = "flightto is mandatory")
    private String flightto;
    @NotNull(message = "date is mandatory")
    private String date;
    private int fare;
    
}   