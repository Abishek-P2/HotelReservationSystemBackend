package edu.jsp.Hotel_Reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.jsp.Hotel_Reservation.dto.MyBookingDTO;
import edu.jsp.Hotel_Reservation.entity.booking;
import edu.jsp.Hotel_Reservation.service.bookingservice;
import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "https://hotel-reservation-system-dun-zeta.vercel.app")
@AllArgsConstructor
public class bookingcontroller {
   private bookingservice bookingser;
   @PostMapping("/user/{userid}/rooms/{roomid}/bookings")
   public booking createbooking(@PathVariable long userid,@PathVariable long roomid,@RequestBody booking booking) {
	   return bookingser.entry(userid,roomid,booking);
   }
   @GetMapping("/user/{userid}/booking/{bookingid}")
   public booking getbyid(@PathVariable long userid,@PathVariable long bookingid) {
	   return bookingser.getbyid(userid,bookingid);
   }
   @GetMapping("/getallbyuser/{id}")
   public List<booking> getallbookingbuuserid(@PathVariable long id){
	   return bookingser.getbookingbyuserid(id);
   }
   @DeleteMapping("/deletebooking/{id}")
   public String deletebyid(@PathVariable long id) {
	  bookingser.deletebooking(id) ;
	  return "Data Deleted";
   }
   @PutMapping("/user/updatebooking/{id}")
   public booking updatebooking(@PathVariable long id,@RequestBody booking booking){        
	   return bookingser.updatebooking(id, booking);
   }
   @GetMapping("/rooms/{roomno}/bookings")
   public List<booking> getbookingbyroomno(@PathVariable long roomno){
	   return bookingser.getbookingbyroomno(roomno);
   }
   @GetMapping("/mybookings/{userId}")
   public List<MyBookingDTO> getMyBookings(
           @PathVariable long userId) {

       return bookingser.getMyBookings(userId);
   }
   
   @DeleteMapping("/cancel/{id}")
   public String cancelBooking(@PathVariable long id) {

       bookingser.cancelBooking(id);

       return "Booking cancelled successfully";
   }
}