package edu.jsp.Hotel_Reservation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Healthcontroller {
	@GetMapping("/health")
	public String health() {
		return "OK";
	}

}
