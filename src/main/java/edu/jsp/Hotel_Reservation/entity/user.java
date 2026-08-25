package edu.jsp.Hotel_Reservation.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class user {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @NotBlank(message = "Please Enter User Name")
  private String name;
  @Email(message = "Invalid Email")
  private String email;
  private long mobile;
  @Size(min =5 ,message = "Password must be atleast 5 characters")
  private String password;
  private String confirmpassword;
  @OneToMany
  (cascade = CascadeType.ALL,mappedBy = "user")
  @JsonManagedReference("user-booking")
  private List<booking>  booking;
  
  public void addbooking(booking b) {
	  booking.add(b);
	  b.setUser(this);
  }
  public void removebooking(booking b) {
	  booking.remove(b);
	  b.setUser(null);
  }
}
