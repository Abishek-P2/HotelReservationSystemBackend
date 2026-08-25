package edu.jsp.Hotel_Reservation.entity;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class hotel {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
   @NotBlank(message = "Hotel name is Required")
	private String name;
   @NotBlank(message = "Loaction is Required")
	private String city;
	private double rating;
	private String image;
	private double price;
	@OneToMany
	(cascade = CascadeType.ALL,mappedBy = "hotel")
	@JsonManagedReference
	private List<room> room;
	public void addroom(List<room> rooms) {
		for(room r: rooms) {
			room.add(r);
			r.setHotel(this);
		}
	}
}
