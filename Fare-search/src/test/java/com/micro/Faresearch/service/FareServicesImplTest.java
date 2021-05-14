package com.micro.Faresearch.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.micro.Faresearch.Services.FareServicesImpl;
import com.micro.Faresearch.models.Fare;
import com.micro.Faresearch.models.FlightFare;

import org.junit.Test;

public class FareServicesImplTest {

    FareServicesImpl fareServiceImplMock = mock(FareServicesImpl.class);

    @Test
    public void postdataTest(){
        Fare fareList = new Fare(null,"BF101",101);
        when(fareServiceImplMock.postdata(fareList)).thenReturn("Successfully added");
        assertEquals("Successfully added", fareServiceImplMock.postdata(fareList));
    }

    @Test
    public void getdataTest(){
        Fare fareList1 = new Fare(null,"BF101",101);
        Fare fareList2 = new Fare(null,"BF101",101);
        List<Fare> farelist = new ArrayList<Fare>();
        farelist.add(fareList1);
        farelist.add(fareList2);
        when(fareServiceImplMock.getdata()).thenReturn(farelist);
        assertEquals(farelist, fareServiceImplMock.getdata());
    }

    @Test
    public void getflightTest(){
        Fare fareList = new Fare(null,"BF101",101);
        when(fareServiceImplMock.getflight("BF101")).thenReturn(fareList);
        assertEquals(fareList, fareServiceImplMock.getflight("BF101"));
    }

    @Test
    public void getPriceTest(){
        Fare fareList1 = new Fare(null,"BF101",101);
        Fare fareList2 = new Fare(null,"BF101",101);
        List<Fare> farelist = new ArrayList<Fare>();
        farelist.add(fareList1);
        farelist.add(fareList2);

        FlightFare flightFare = new FlightFare(farelist);
        when(fareServiceImplMock.getPrice()).thenReturn(flightFare);
        assertEquals(flightFare, fareServiceImplMock.getPrice());

    }



    

}
