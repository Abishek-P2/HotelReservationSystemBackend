package edu.jsp.Hotel_Reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.jsp.Hotel_Reservation.Exception.NotFoundException;
import edu.jsp.Hotel_Reservation.dto.MyBookingDTO;
import edu.jsp.Hotel_Reservation.entity.booking;
import edu.jsp.Hotel_Reservation.entity.room;
import edu.jsp.Hotel_Reservation.entity.user;
import edu.jsp.Hotel_Reservation.repository.bookingrepository;
import edu.jsp.Hotel_Reservation.repository.roomrepository;
import edu.jsp.Hotel_Reservation.repository.userrepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class bookingservice {
    private userrepository userrepo;
     private bookingrepository bookingrepo;
     private roomrepository roomrepo;
     
     public List<MyBookingDTO> getMyBookings(long userId) {

    	    List<booking> bookings =
    	            bookingrepo.findByUserId(userId);

    	    return bookings.stream().map(b -> {

    	        MyBookingDTO dto = new MyBookingDTO();

    	        dto.setBookingId(b.getId());

    	        // Hotel details
    	        dto.setHotelName(
    	                b.getRoom().getHotel().getName()
    	        );

    	        dto.setCity(
    	                b.getRoom().getHotel().getCity()
    	        );

    	        dto.setHotelImage(
    	                b.getRoom().getHotel().getImage()
    	        );

    	        // Room details
    	        dto.setRoomType(
    	                b.getRoom().getRoomtype()
    	        );

    	        dto.setPrice(
    	                b.getRoom().getPrice()
    	        );

    	        // Booking details
    	        dto.setCheckin(
    	                b.getCheckindate()
    	        );

    	        dto.setCheckout(
    	                b.getCheckoutdate()
    	        );

    	        dto.setGuests(
    	                b.getRoom().getGuest()  );
    	        
    	        dto.setTotalAmount(b.getTotalamount());

    	        dto.setStatus(b.getStatus());
    	        
    	        dto.setName(b.getName());
    	        
    	        

    	        return dto;

    	    }).toList();
    	}
     
     public booking entry(long userid, long roomid, booking booking) {

    	    user user = userrepo.findById(userid)
    	            .orElseThrow(() ->
    	                new RuntimeException("User not found")
    	            );

    	    room room = roomrepo.findById(roomid)
    	            .orElseThrow(() ->
    	                new RuntimeException("Room not found")
    	            );
    	    

    	    if (booking.getGuests() > room.getGuest()) {
    	        throw new RuntimeException(
    	            "Room cannot accommodate these guests"
    	        );
    	    }

    	    if (!booking.getCheckoutdate()
    	            .isAfter(booking.getCheckindate())) {

    	        throw new RuntimeException(
    	            "Checkout must be after check-in"
    	        );
    	    }
    	    boolean alreadyBooked =
    	            bookingrepo.existsActiveBooking(
    	                    roomid,
    	                    booking.getCheckindate(),
    	                    booking.getCheckoutdate()
    	            );

    	    if (alreadyBooked) {
    	        throw new RuntimeException(
    	            "This room is already booked for the selected dates"
    	        );
    	    }

    	    // Calculate number of nights
    	    long nights = java.time.Duration.between(
    	            booking.getCheckindate(),
    	            booking.getCheckoutdate()
    	    ).toDays();
    	    
    	    double totalamount=room.getPrice()*nights;

    	    // Room price is for one day/night
    	    double totalAmount =
    	            room.getPrice() * nights;

    	    booking.setTotalamount(totalAmount);

    	    booking.setUser(user);
    	    booking.setStatus("Confirmed");

    	    booking.setRoom(room);

    	    return bookingrepo.save(booking);
    	}
     public booking getbyid(long userid,long bookingid) {
    	 user u= userrepo.findById(userid).orElseThrow(()->new NotFoundException("User","Id",userid)); 
    	 return  bookingrepo.findById(bookingid).orElseThrow(()->new NotFoundException("Booking","Id",bookingid));
     }
     public List<booking> getbookingbyuserid(long id){
    	 userrepo.findById(id).orElseThrow(()->new NotFoundException("User", "Id", id));
    	 return bookingrepo.GetBookingByUserId(id);
     }
     public String deletebooking(long id) {
    	 booking b=bookingrepo.findById(id).orElseThrow(()->new NotFoundException("Booking", "Id", id));
    	 bookingrepo.delete(b);
    	 return "Data Deleted";
     }
     public booking updatebooking(long id,booking booking){
    	 booking b=bookingrepo.findById(id).orElseThrow(()->new NotFoundException("Booking", "Id", id));
    	 b.setCheckindate(booking.getCheckindate());
    	 b.setCheckoutdate(booking.getCheckoutdate());
    	 b.setTotalamount(booking.getTotalamount());
    	 
    	 return bookingrepo.save(b);
     }
     
     public List<booking> getbookingbyroomno(long roomno){
    	 return bookingrepo.GetBookingByRoomNo(roomno);
     }
     
     public void cancelBooking(long id) {

    	    booking booking = bookingrepo.findById(id)
    	            .orElseThrow(() ->
    	                new RuntimeException("Booking not found")
    	            );

    	    booking.setStatus("Cancelled");

    	    bookingrepo.save(booking);
    	}
     
}
