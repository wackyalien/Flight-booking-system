package com.micro.Booking.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Flight {
    @Id
    private int id;
    private String flightno;
    private String flightfrom;
    private String flightto;
    private String date;
    private String fare;

    public Flight() {
    }

    public Flight(int id, String flightno, String flightfrom, String flightto, String date, String fare) {
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

    public String getFare() {
        return fare;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }

    @Override
    public String toString() {
        return "Flight [date=" + date + ", fare=" + fare + ", flightto=" + flightto + ", flightfrom=" + flightfrom
                + ", flightno=" + flightno + ", id=" + id + "]";
    }
    
}   