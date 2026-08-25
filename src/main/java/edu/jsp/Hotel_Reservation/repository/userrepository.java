package edu.jsp.Hotel_Reservation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.jsp.Hotel_Reservation.entity.user;

public interface userrepository extends JpaRepository<user, Long> {
	public List<user> findByName(String name);
	
	public Optional<user> findByEmail(String email);

}
