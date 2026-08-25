package edu.jsp.Hotel_Reservation.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyBookingDTO {

    private long bookingId;

    private String hotelName;

    private String city;

    private String hotelImage;

    private String roomType;

    private double price;

    private LocalDateTime checkin;

    private LocalDateTime checkout;

    private int guests;
    private double totalAmount;

    private String status;
    
    private  String name;
}