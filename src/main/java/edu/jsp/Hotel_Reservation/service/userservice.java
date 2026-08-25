package edu.jsp.Hotel_Reservation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.jsp.Hotel_Reservation.Exception.NotFoundException;
import edu.jsp.Hotel_Reservation.dto.LoginRequest;
import edu.jsp.Hotel_Reservation.entity.user;
import edu.jsp.Hotel_Reservation.repository.userrepository;

@Service
public class userservice {
	@Autowired
   private userrepository userrepo; 
	
	public user createuser(user user) {
		return userrepo.save(user);
	}
	public user updatebyid(user user,long id) {
		user u=userrepo.findById(id).orElseThrow(()->new NotFoundException("User","Id",id));
		u.setName(user.getName());
		u.setEmail(user.getEmail());
		u.setPassword(user.getPassword());
		u.setConfirmpassword(user.getConfirmpassword());
		return userrepo.save(u);
	}
	public String deleteuser(long id) {
	   user u=userrepo.findById(id).orElseThrow(()->new NotFoundException("User","Id",id));
	   
	   userrepo.delete(u);
	  return "Data Deleted";
	}
	public user getbyid(long id) {
//		Optional<user> o=userrepo.findById(id);
//		return o.isPresent()?o.get():null;
		return userrepo.findById(id).orElseThrow(()->new NotFoundException("User","Id",id));
	}
	public List<user> getalluser(){
		return userrepo.findAll();
	}
	public List<user> getuserbyname(String name){
		List<user> u=userrepo.findByName(name);
		if(u.isEmpty()) {
			throw new NotFoundException("User", name, 0);
		}
		return u;
	}
	public user login(LoginRequest request) {
		user u=userrepo.findByEmail(request.getEmail()).orElseThrow(()->new RuntimeException("User Not Found"));
		
		if(!u.getPassword().equals(request.getPassword())) {
			throw new RuntimeException("Invalid Password");
		}
		return u;
	}
}
