package com.micro.Flightsearch.models;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
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

    public Flight() {
    }

    public Flight(int id, String flightno, String flightfrom, String flightto, String date, int fare) {
        this.id = id;
        this.flightno = flightno;
        this.flightfrom = flightfrom;
        this.flightto = flightto;
        this.date = date;
        this.fare = fare;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlightno() {
        return flightno;
    }

    public void setFlightno(String flightno) {
        this.flightno = flightno;
    }

    public String getFlightfrom() {
        return flightfrom;
    }

    public void setFlightfrom(String flightfrom) {
        this.flightfrom = flightfrom;
    }

    public String getFlightto() {
        return flightto;
    }

    public void setFlightfo(String flightto) {
        this.flightto = flightto;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getFare() {
        return fare;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }

    @Override
    public String toString() {
        return "Flight [date=" + date + ", fare=" + fare + ", flightto=" + flightto + ", flightfrom=" + flightfrom
                + ", flightno=" + flightno + ", id=" + id + "]";
    }
    
}   