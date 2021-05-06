package com.micro.Flightsearch.models;

import java.util.List;

public class FlightFare {
    
    private List<Fare> flightFare;

    public FlightFare() {
    }

    public FlightFare(List<Fare> flightFare) {
        this.flightFare = flightFare;
    }

    public List<Fare> getFlightFare() {
        return flightFare;
    }

    public void setFlightFare(List<Fare> flightFare) {
        this.flightFare = flightFare;
    }

    @Override
    public String toString() {
        return "FlightFare [flightFare=" + flightFare + "]";
    }

}
