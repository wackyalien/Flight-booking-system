package com.micro.Flightsearch.models;

import org.bson.types.ObjectId;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Fare {
    @Id
    private ObjectId _id;
    @NotNull(message = "flightno is mandatory")
    private String flightno;
    @Min(value=1, message="must be equal or greater than 1")
    private int fprice;

    public Fare() {
    }

    public Fare(ObjectId _id, String flightno, int fprice) {
        this._id = _id;
        this.flightno = flightno;
        this.fprice = fprice;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getFlightno() {
        return flightno;
    }

    public void setFlightno(String flightno) {
        this.flightno = flightno;
    }

    public int getFprice() {
        return fprice;
    }

    public void setFprice(int fprice) {
        this.fprice = fprice;
    }

    @Override
    public String toString() {
        return "Fare [_id=" + _id + ", flightno=" + flightno + ", fprice=" + fprice + "]";
    }

}
