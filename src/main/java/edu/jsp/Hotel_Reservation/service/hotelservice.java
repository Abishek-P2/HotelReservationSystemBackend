package edu.jsp.Hotel_Reservation.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.jsp.Hotel_Reservation.Exception.NotFoundException;
import edu.jsp.Hotel_Reservation.entity.hotel;
import edu.jsp.Hotel_Reservation.repository.hotelrepository;

@Service
public class hotelservice {
    @Autowired
    private hotelrepository hotelrepo;
    
    public hotel savehotel(hotel hotel) {
    	
    	return hotelrepo.save(hotel);
    }
    public hotel getbyid(long id) {
    	return hotelrepo.findById(id).orElseThrow(()->new NotFoundException("Hotel","id",id));
    }
    public List<hotel> getall(){
    	return hotelrepo.findAll();
    }
    public hotel updatebyid(long id,hotel hotel) {
    	hotel h=hotelrepo.findById(id).orElseThrow(()->new NotFoundException("Hotel", "Id", id));
    	h.setName(hotel.getName());
    	h.setCity(hotel.getCity());
    	h.setRating(hotel.getRating());
    	h.setPrice(hotel.getPrice());
    	h.setImage(hotel.getImage());
    	return hotelrepo.save(h);
    }
    public List<hotel> getbycity(String city) {
      List<hotel> h= hotelrepo.findByCity(city);
      if(h.isEmpty()) {
    	  throw new NotFoundException("Hotel",city, 0);
      }
      return h;
    }
    
    public List<hotel> gethotelbyname(String name){
    	List<hotel> h=hotelrepo.findByName(name);
    	if(h.isEmpty()) {
    		throw new NotFoundException("Hotel", name, 0);
    	}
    	return h;
    }
    public List<hotel> gethotelbyrating(double rating){
       List<hotel> h=hotelrepo.findByRating(rating);
       if(h.isEmpty()) {
    	   throw new NotFoundException("Hotel", null, 0);
       }
       return h;
    }
    public List<hotel> searchHotels(
            String city,
            LocalDateTime checkin,
            LocalDateTime checkout,
            int guests) {

        return hotelrepo.searchHotels(
                city,
                checkin,
                checkout,
                guests
        );
    }
}
