package edu.jsp.Hotel_Reservation.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.jsp.Hotel_Reservation.entity.hotel;

public interface hotelrepository extends JpaRepository<hotel, Long>{
	
	public List<hotel>  findByCity(String city);
	public List<hotel> findByName(String name);
	public List<hotel> findByRating(double rating);
	 @Query("""
		        SELECT DISTINCT h
		        FROM hotel h
		        JOIN h.room r
		        WHERE LOWER(TRIM(h.city)) = LOWER(TRIM(:city))
		        AND r.guest = :guests
		        AND r.available = true
		        AND NOT EXISTS (
		            SELECT b
		            FROM booking b
		            WHERE b.room = r
		            AND b.checkindate < :checkout
		            AND b.checkoutdate > :checkin
		        )
		    """)
		    List<hotel> searchHotels(
		            @Param("city") String city,
		            @Param("checkin") LocalDateTime checkin,
		            @Param("checkout") LocalDateTime checkout,
		            @Param("guests") int guests
		    );

}
