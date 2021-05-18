package com.micro.Flightsearch.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.micro.Flightsearch.models.AvailableFlight;
import com.micro.Flightsearch.models.Flight;
import com.micro.Flightsearch.service.FlightServiceImpl;

import org.junit.Test;

public class FlightServiceImplTest {

    FlightServiceImpl flightServiceImplMock = mock(FlightServiceImpl.class);

    @Test
    public void postDataTest(){
        Flight flight = new Flight(1,"BF101","NYC","SFO","2019-01-01",101);
        when(flightServiceImplMock.postData(flight)).thenReturn("Successfully added");
        assertEquals("Successfully added", flightServiceImplMock.postData(flight));
    }

    @Test
    public void getDataTest(){
        Flight flight1 = new Flight(1,"BF101","NYC","SFO","2019-01-01",101);
        Flight flight2 = new Flight(2,"BF102","NYC","SFO","2019-01-01",102);
        List<Flight> flightList = new ArrayList<>();
        flightList.add(flight1);
        flightList.add(flight2);
        when(flightServiceImplMock.getData()).thenReturn(flightList);
        assertEquals(flightList, flightServiceImplMock.getData());
    }

    @Test
    public void getAllFlightTest(){
        Flight flight1 = new Flight(1,"BF101","NYC","SFO","2019-01-01",101);
        Flight flight2 = new Flight(2,"BF102","NYC","SFO","2019-01-01",102);
        List<Flight> flightList = new ArrayList<>();
        flightList.add(flight1);
        flightList.add(flight2);
        when(flightServiceImplMock.getAllFlight()).thenReturn(flightList);
        assertEquals(flightList, flightServiceImplMock.getAllFlight());

    }
    @Test
    public void getFlightDataTest(){
        Flight flight = new Flight(1,"BF101","NYC","SFO","2019-01-01",101);
        when(flightServiceImplMock.getData("BF101")).thenReturn(flight);
        assertNotEquals(flight, flightServiceImplMock.getData("BF1012"));
    }

    @Test
    public void getSearchFlightWithFareTest_WithWrongInput(){
        Flight flight1 = new Flight(1,"BF101","NYC","SFO","2019-01-01",101);
        Flight flight2 = new Flight(2,"BF102","NYC","SFO","2019-01-01",102);
        List<Flight> flightList = new ArrayList<>();
        flightList.add(flight1);
        flightList.add(flight2);
        AvailableFlight availableFlightList = new AvailableFlight(flightList);
        when(flightServiceImplMock.getSearchFlightWithFare("NYC","DEL","2019-01-01")).thenReturn(availableFlightList);
        assertNotEquals(availableFlightList, flightServiceImplMock.getSearchFlightWithFare("NYC","SFO","2019-01-01"));
    }

    @Test
    public void getSearchFlightWithFareTest(){
        Flight flight1 = new Flight(1,"BF101","NYC","SFO","2019-01-01",101);
        Flight flight2 = new Flight(2,"BF102","NYC","SFO","2019-01-01",102);
        List<Flight> flightList = new ArrayList<>();
        flightList.add(flight1);
        flightList.add(flight2);
        AvailableFlight availableFlightList = new AvailableFlight(flightList);
        when(flightServiceImplMock.getSearchFlightWithFare("NYC","SFO","2019-01-01")).thenReturn(availableFlightList);
        assertEquals(availableFlightList, flightServiceImplMock.getSearchFlightWithFare("NYC","SFO","2019-01-01"));
    } 
}
