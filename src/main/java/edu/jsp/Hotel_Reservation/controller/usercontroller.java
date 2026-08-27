package edu.jsp.Hotel_Reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.jsp.Hotel_Reservation.dto.LoginRequest;
import edu.jsp.Hotel_Reservation.entity.user;
import edu.jsp.Hotel_Reservation.service.userservice;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "https://hotel-reservation-system-dun-zeta.vercel.app")
public class usercontroller {
	@Autowired
    private userservice userser;
	@PostMapping
	public ResponseEntity<user> createuser(@RequestBody user user) {
		return new ResponseEntity<>(userser.createuser(user),HttpStatus.CREATED);
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<user> updateuser(@RequestBody user user,@PathVariable long id) {
		return new ResponseEntity<> (userser.updatebyid(user,id),HttpStatus.OK);
	}
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<user> getbyid(@PathVariable long id) {
		return new ResponseEntity<user>(userser.getbyid(id),HttpStatus.OK);
	}
	@GetMapping("/getall")
	public ResponseEntity<List<user>>  gettalluser(){
		return new ResponseEntity<List<user>>(userser.getalluser(),HttpStatus.OK);
	}
	@DeleteMapping("/deletebyid/{id}")
	public ResponseEntity<String> deletebyid(@PathVariable long id) {
	   userser.deleteuser(id);	
	   return new ResponseEntity<String>("Data deleted",HttpStatus.OK);
	}
	@GetMapping("/getbyname/{name}")
	public ResponseEntity<List<user>> getbyname(@PathVariable String name){
		return new ResponseEntity<List<user>>(userser.getuserbyname(name),HttpStatus.OK);
	}
	@PostMapping("/login")
	public user login(@RequestBody LoginRequest request) {
		return userser.login(request);
	}
}
