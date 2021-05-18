package com.micro.Booking.services;

import com.micro.Booking.exception.IdNotFoundException;
import com.micro.Booking.models.User;
import com.micro.Booking.service.BookingServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class BookingServiceImplTest {

    BookingServiceImpl bookingServiceImplMock = mock(BookingServiceImpl.class);

    @Test
    public void getRefDetailTest(){
        User user = new User(1,"kunal","jain","Male","sda@faacf.com","BF101","NYC","SFO","2019-01-01",101,null);
        when(bookingServiceImplMock.getRefDetail(1)).thenReturn(user);
        assertEquals(user, bookingServiceImplMock.getRefDetail(1));
    }

    @Test(expected = IdNotFoundException.class)
    public void getRefDetailTest_withInvalidId(){
        User user = null;
        // doThrow(new IdNotFoundException("Invalid Id")).when(bookingServiceImplMock.getRefDetail(1));
        when(bookingServiceImplMock.getRefDetail(1)).thenThrow(IdNotFoundException.class);
        assertEquals(user, bookingServiceImplMock.getRefDetail(1));
    }

    @Test
    public void getspecificflightTest(){
        when(bookingServiceImplMock.getSpecificFlight("BF101", "kunal", "jain", "male", "scxsdc")).thenReturn("Check booking details on your Email");
        assertEquals("Check booking details on your Email", bookingServiceImplMock.getSpecificFlight("BF101", "kunal", "jain", "male", "scxsdc"));
    }

    @Test
    public void getspecificflightTest_paymentfailed(){
        when(bookingServiceImplMock.getSpecificFlight("BF101", "kunal", "jain", "male", "scxsdc")).thenReturn("Payment failed");
        assertEquals("Payment failed", bookingServiceImplMock.getSpecificFlight("BF101", "kunal", "jain", "male", "scxsdc"));
    }
    
}
