package com.micro.Faresearch.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Fare {
    @Id
    private int id;
    private String flightno;
    private String fprice;

    public Fare() {
    }

    public Fare(int id, String flightno, String fprice) {
        this.id = id;
        this.flightno = flightno;
        this.fprice = fprice;
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

    public String getFprice() {
        return fprice;
    }

    public void setFprice(String fprice) {
        this.fprice = fprice;
    }

    @Override
    public String toString() {
        return "Fare [flightno=" + flightno + ", fprice=" + fprice + ", id=" + id + "]";
    }
  
}
