package com.micro.Booking.service;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.micro.Booking.exception.IdNotFoundException;
import com.micro.Booking.models.Flight;
import com.micro.Booking.models.User;
import com.micro.Booking.repository.Bookingrepo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookingServiceImpl implements BookingService{

    Logger logger = Logger.getLogger(BookingServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Bookingrepo bookingrepo;

    @Override
    public User getRefDetail(int id) {
        logger.info("search by id");
        User user=this.bookingrepo.findById(id);
        if(user == null){
            throw new IdNotFoundException("Invalid Id");
        }
        return user;
    }

    @Override
    public String getSpecificFlight(String flightno, String firstname, String lastname, String gender, String email) {
        logger.info("book flight for user");
        Random rand = new Random();
        var payment = rand.nextInt(2-0) + 0;
        if(payment==1){
            int count=this.bookingrepo.findAll().size();
            Flight flight = this.restTemplate.getForObject("http://flight-search/flight/"+flightno, Flight.class);
            User user = new User();
            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setGender(gender);
            user.setEmail(email);
            user.setFlightno(flightno);
            user.setFlightfrom(flight.getFlightfrom());
            user.setFlightto(flight.getFlightto());
            user.setDate(flight.getDate());
            user.setFare(flight.getFare());
            user.setId(count+1);
            this.bookingrepo.save(user);

            String sender = "kunaljain5021@gmail.com";
            String host = "smtp.gmail.com";
            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");
            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

                protected PasswordAuthentication getPasswordAuthentication() {

                    return new PasswordAuthentication("kunaljain5021@gmail.com", "Kuttabhaiya1");

                }

            });

            session.setDebug(true);

            try 
            {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Ticket Confirmation");
            message.setText("Your booking is confirmed. Reference number is "+user.getId());
            Transport.send(message);
            System.out.println("Mail successfully sent");
            }
            catch (MessagingException mex) 
            {
            mex.printStackTrace();
            }
            return "Check booking details on your Email";
        }
        else{
            return "Payment failed";
        }
    }
        
}