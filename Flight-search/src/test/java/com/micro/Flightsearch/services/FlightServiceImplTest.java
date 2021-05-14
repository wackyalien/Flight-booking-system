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
    public void postdataTest(){
        Flight flight = new Flight(1,"BF101","NYC","SFO","2019-01-01",101);
        when(flightServiceImplMock.postdata(flight)).thenReturn("Successfully added");
        assertEquals("Successfully added", flightServiceImplMock.postdata(flight));
    }

    @Test
    public void getdataTest(){
        Flight flight1 = new Flight(1,"BF101","NYC","SFO","2019-01-01",101);
        Flight flight2 = new Flight(2,"BF102","NYC","SFO","2019-01-01",102);
        List<Flight> flightList = new ArrayList<>();
        flightList.add(flight1);
        flightList.add(flight2);
        when(flightServiceImplMock.getdata()).thenReturn(flightList);
        assertEquals(flightList, flightServiceImplMock.getdata());
    }

    @Test
    public void getallflightTest(){
        Flight flight1 = new Flight(1,"BF101","NYC","SFO","2019-01-01",101);
        Flight flight2 = new Flight(2,"BF102","NYC","SFO","2019-01-01",102);
        List<Flight> flightList = new ArrayList<>();
        flightList.add(flight1);
        flightList.add(flight2);
        when(flightServiceImplMock.getallflight()).thenReturn(flightList);
        assertEquals(flightList, flightServiceImplMock.getallflight());

    }
    @Test
    public void getFlightdataTest(){
        Flight flight = new Flight(1,"BF101","NYC","SFO","2019-01-01",101);
        when(flightServiceImplMock.getdata("BF101")).thenReturn(flight);
        assertNotEquals(flight, flightServiceImplMock.getdata("BF1012"));
    }

    @Test
    public void getsearchflightwithfareTest_withwronginput(){
        Flight flight1 = new Flight(1,"BF101","NYC","SFO","2019-01-01",101);
        Flight flight2 = new Flight(2,"BF102","NYC","SFO","2019-01-01",102);
        List<Flight> flightList = new ArrayList<>();
        flightList.add(flight1);
        flightList.add(flight2);
        AvailableFlight availableFlightList = new AvailableFlight(flightList);
        when(flightServiceImplMock.getsearchflightwithfare("NYC","DEL","2019-01-01")).thenReturn(availableFlightList);
        assertNotEquals(availableFlightList, flightServiceImplMock.getsearchflightwithfare("NYC","SFO","2019-01-01"));
    }

    @Test
    public void getsearchflightwithfareTest(){
        Flight flight1 = new Flight(1,"BF101","NYC","SFO","2019-01-01",101);
        Flight flight2 = new Flight(2,"BF102","NYC","SFO","2019-01-01",102);
        List<Flight> flightList = new ArrayList<>();
        flightList.add(flight1);
        flightList.add(flight2);
        AvailableFlight availableFlightList = new AvailableFlight(flightList);
        when(flightServiceImplMock.getsearchflightwithfare("NYC","SFO","2019-01-01")).thenReturn(availableFlightList);
        assertEquals(availableFlightList, flightServiceImplMock.getsearchflightwithfare("NYC","SFO","2019-01-01"));
    } 
}
