package com.micro.checkin.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.micro.checkin.exceptions.AlreadyCheckInException;
import com.micro.checkin.exceptions.IdNotFoundException;
import com.micro.checkin.models.CheckingMessage;

import org.junit.Test;

public class checkinServiceImplTest {

    checkinServiceImpl checkinServiceImplMock = mock(checkinServiceImpl.class);

    @Test
    public void checkinTest(){
        CheckingMessage checkingMessage = new CheckingMessage(1,"message");
        when(checkinServiceImplMock.checkIn(1)).thenReturn(checkingMessage);
        assertEquals(checkingMessage, checkinServiceImplMock.checkIn(1));
    }

    @Test(expected = IdNotFoundException.class)
    public void checkinTest_invalidId(){
        // CheckingMessage checkingMessage = new CheckingMessage(1,"message");
        when(checkinServiceImplMock.checkIn(2)).thenThrow(IdNotFoundException.class);
        assertEquals(IdNotFoundException.class,checkinServiceImplMock.checkIn(2));
    }

    @Test(expected = AlreadyCheckInException.class)
    public void checkinTest_AlreadyCheckIn(){
        // CheckingMessage checkingMessage = new CheckingMessage(1,"message");
        when(checkinServiceImplMock.checkIn(2)).thenThrow(AlreadyCheckInException.class);
        assertEquals(AlreadyCheckInException.class,checkinServiceImplMock.checkIn(2));
    }
}
