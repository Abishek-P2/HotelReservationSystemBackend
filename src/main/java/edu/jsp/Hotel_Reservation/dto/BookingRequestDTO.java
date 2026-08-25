package edu.jsp.Hotel_Reservation.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingRequestDTO {

    private long roomId;

    private LocalDateTime checkindate;

    private LocalDateTime checkoutdate;

    private int guests;
}
