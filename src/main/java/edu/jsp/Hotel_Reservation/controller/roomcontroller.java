package edu.jsp.Hotel_Reservation.controller;
import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.jsp.Hotel_Reservation.entity.room;
import edu.jsp.Hotel_Reservation.service.roomservice;

@RestController
@CrossOrigin(origins = "https://hotel-reservation-system-dun-zeta.vercel.app")
public class roomcontroller {
  @Autowired
  private roomservice roomser;
  @PostMapping("/hotel/{id}/room")
  public List<room>  createroom(@PathVariable long id,@RequestBody List<room> room) {
	 return roomser.createroom(id,room) ; 
  }
  @GetMapping("/getbyid/{id}")
  public room getbyid(@PathVariable long id) {
	  return roomser.getbyid(id);
  }
  @GetMapping("/getall")
  public List<room> getall(){
	  return roomser.getallroom();
  }
  @DeleteMapping("/deleteall")
  public String deleteall() {
	  roomser.deleteroom();
	  return "Data Deleted";
  }
  @DeleteMapping("/deletebyid/{id}")
  public String deletebyid(@PathVariable long id) {
	  roomser.deletebyid(id);
	  return "Data Deleted";
  }
  @PutMapping("/updatebyid/{id}")
  public room updatebyid(@PathVariable long id,@RequestBody room room) {
	  return roomser.updatebyid(id,room);
  }
  @GetMapping("/getroomsbyhotelid/{id}")
  public List<room> getroomsbyhotelid(@PathVariable long id){
	  return roomser.getroomsbyhotelid(id);
  }
  @GetMapping("/getroombyhotelname/{name}")
  public List<room> getroombyhotelname(@PathVariable String name){
	  return roomser.getroombyhotelname(name);
  }
  @GetMapping("/getbyroomtype/{roomtype}")
  public List<room> getroombyroomtype(@PathVariable String roomtype){
	  return roomser.getroombyroomtype(roomtype);
  }
  @PutMapping("/updateall")
  public List<room> updateall(@RequestBody List<room> rooms){
	  return roomser.updateall(rooms);
  }
 
       
  @GetMapping("/rooms/search")
  public List<room> searchAvailableRooms(
          @RequestParam LocalDateTime checkin,
          @RequestParam LocalDateTime checkout,
          @RequestParam int guests) {

      return roomser.searchAvailableRooms(
              checkin,
              checkout,
              guests
      );
  }
}