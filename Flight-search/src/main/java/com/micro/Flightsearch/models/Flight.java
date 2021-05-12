package com.micro.Flightsearch.models;

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
public class Flight {
    @Id
    private int id;
    @NotNull(message = "flightno is mandatory")
    private String flightno;
    @NotNull(message = "flightfrom is mandatory")
    private String flightfrom;
    @NotNull(message = "flightto is mandatory")
    private String flightto;
    @NotNull(message = "date is mandatory")
    private String date;
    private int fare;
    
}   