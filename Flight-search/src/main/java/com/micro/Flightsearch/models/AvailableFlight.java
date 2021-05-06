package com.micro.Flightsearch.models;

import java.util.List;

public class AvailableFlight {
    private List<Flight> availableflight;

    public AvailableFlight() {
    }

    public AvailableFlight(List<Flight> availableflight) {
        this.availableflight = availableflight;
    }

    public List<Flight> getAvailableflight() {
        return availableflight;
    }

    public void setAvailableflight(List<Flight> availableflight) {
        this.availableflight = availableflight;
    }

    @Override
    public String toString() {
        return "AvailableFlight [availableflight=" + availableflight + "]";
    }

}
