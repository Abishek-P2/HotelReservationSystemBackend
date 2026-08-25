package edu.jsp.Hotel_Reservation.dto;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
   
	private String email;
	private String password;
}
