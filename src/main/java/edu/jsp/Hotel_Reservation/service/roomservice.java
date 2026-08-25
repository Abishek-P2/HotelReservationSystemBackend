package edu.jsp.Hotel_Reservation.service;
import java.time.LocalDateTime;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.jsp.Hotel_Reservation.Exception.NotFoundException;
import edu.jsp.Hotel_Reservation.entity.hotel;
import edu.jsp.Hotel_Reservation.entity.room;
import edu.jsp.Hotel_Reservation.repository.bookingrepository;
import edu.jsp.Hotel_Reservation.repository.hotelrepository;
import edu.jsp.Hotel_Reservation.repository.roomrepository;
import edu.jsp.Hotel_Reservation.repository.userrepository;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class roomservice {
      private bookingrepository bookingrepo;
      private roomrepository roomrepo;
      private hotelrepository hotelrepo;
      public List<room> createroom(long id,List<room> room) {
    	  hotel h=hotelrepo.findById(id).orElseThrow(()->new NotFoundException("Hotel", "Id", id));
    	  h.addroom(room);
    	  return roomrepo.saveAll(room);
      }
      public List<room> getallroom(){
    	  return roomrepo.findAll();
      }
      public room getbyid(long id) {
    	  return roomrepo.findById(id).orElseThrow(()->new NotFoundException("Room","ID",id));
      }
      public String deleteroom() {
    	   roomrepo.deleteAll();
    	   return "Data Deleted";
      }
      public String deletebyid(long id) {
    	  room r=roomrepo.findById(id).orElseThrow(()->new NotFoundException("Room", "ID", id));
    	  roomrepo.delete(r);
    	  return "Data Deleted";
      }
      public room updatebyid(long id,room room) {
    	  room r=roomrepo.findById(id).orElseThrow(()->new  NotFoundException("Room", "Id", id));
    	  r.setRoomno(room.getRoomno());
    	  r.setRoomtype(room.getRoomtype());
    	  r.setAvailable(room.isAvailable());
    	  r.setPrice(room.getPrice());
    	  r.setGuest(room.getGuest());
    	  return roomrepo.save(r);
      }
      public List<room> getroomsbyhotelid(long id){
    	  hotel h=hotelrepo.findById(id).orElseThrow(()->new NotFoundException("Hotel","Id", id));
    	  
    	  return roomrepo.findRoomByHotelId(id);
      }
      public List<room> getroombyhotelname(String name){
    	  return roomrepo.findRoomByHotelName(name);
      }
      public List<room> getroombyroomtype(String roomtype){
    	  return roomrepo.findByRoomtypeIgnoreCase(roomtype);
      }
      public List<room> updateall(List<room> rooms){
    	 List<room> updatedrooms =new ArrayList<room>();
    	 for(room room :rooms) {
    		 room existing=roomrepo.findById(room.getId()).orElseThrow();
    		 existing.setImage(room.getImage());
    		 updatedrooms.add(existing);
    	 }
    	 return roomrepo.saveAll(updatedrooms);
      }
     
      public List<room> searchAvailableRooms(
    	        LocalDateTime checkin,
    	        LocalDateTime checkout,
    	        int guests) {

    	    List<room> rooms = roomrepo.findByGuestGreaterThanEqual(guests);

    	    return rooms.stream()
    	            .filter(r -> {

    	                boolean booked =
    	                        bookingrepo.existsActiveBooking(
    	                                r.getId(),
    	                                checkin,
    	                                checkout
    	                        );

    	                return !booked;
    	            })
    	            .toList();
    	}
}
