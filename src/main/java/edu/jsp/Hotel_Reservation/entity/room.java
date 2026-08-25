package edu.jsp.Hotel_Reservation.entity;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.UniqueElements;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class room {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
   @NotNull(message = "Roomno is Required")
	private long roomno;
	private String roomtype;
	private double price;
	private boolean available;
	private int guest;
	private String image;
	@ManyToOne
	@JoinColumn(name="hotel_id")
	@JsonBackReference
	private hotel hotel;
	@OneToMany
	(cascade = CascadeType.ALL,mappedBy = "room")
	@JsonManagedReference("room-booking")
	private List<booking> booking;
	
	public void addbooking(booking b) {
		booking.add(b);
		b.setRoom(this);
	}
}