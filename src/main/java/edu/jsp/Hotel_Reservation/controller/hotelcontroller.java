package edu.jsp.Hotel_Reservation.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.jsp.Hotel_Reservation.entity.hotel;
import edu.jsp.Hotel_Reservation.service.hotelservice;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/hotel")
@CrossOrigin(origins = "http://localhost:5173")
public class hotelcontroller {
    private hotelservice hotelser;
    
    
    @PostMapping
    public hotel savehotel(@RequestBody hotel hotel) {
    	return hotelser.savehotel(hotel);
    }
    @GetMapping("/getbyid/{id}")
    public hotel getbyid(@PathVariable long id) {
    	return hotelser.getbyid(id);
    }
    @GetMapping("/getall")
    public List<hotel> getall(){
    	return hotelser.getall();
    }
    @PutMapping("/updatebyid/{id}")
    public hotel updatebyid(@PathVariable long id,@RequestBody hotel hotel) {
    	return hotelser.updatebyid(id, hotel);
    }
    @GetMapping("/getbycity/{city}")
    public List<hotel> getbycity(@PathVariable String city){
    	return hotelser.getbycity(city);
    }
    @GetMapping("/getbyname/{name}")
    public List<hotel> getbyname(@PathVariable String name){
    	return hotelser.gethotelbyname(name);
    }
    @GetMapping("/getbyrating/{rating}")
    public List<hotel> getbyrating(@PathVariable double rating){
    	return hotelser.gethotelbyrating(rating);
    }
    @GetMapping("/hotelsearch")
    public List<hotel> searchHotels(
            @RequestParam String city,
            @RequestParam LocalDateTime checkin,
            @RequestParam LocalDateTime checkout,
            @RequestParam int guests) {

        if (!checkout.isAfter(checkin)) {
            throw new RuntimeException(
                    "Checkout date must be after check-in date");
        }

        if (guests <= 0) {
            throw new RuntimeException(
                    "Guests must be greater than 0");
        }

        return hotelser.searchHotels(
                city,
                checkin,
                checkout,
                guests
        );
    }
}
