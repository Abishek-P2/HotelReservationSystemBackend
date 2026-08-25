package edu.jsp.Hotel_Reservation.entity;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime checkindate;
    private LocalDateTime checkoutdate;
    private double totalamount;
   private String name;
   private int guests;
    private String status;
    @ManyToOne
    @JoinColumn
    @JsonBackReference("user-booking")
    private user user;
    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonBackReference("room-booking")
    private room room;
    
}