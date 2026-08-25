package edu.jsp.Hotel_Reservation.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.jsp.Hotel_Reservation.entity.room;

public interface roomrepository extends JpaRepository<room, Long> {

    @Query("select r from room r where r.hotel.id=?1")
    public List<room> findRoomByHotelId(long id);

    @Query("select r from room r where r.hotel.name=?1")
    public List<room> findRoomByHotelName(String name);

    public List<room> findByRoomtypeIgnoreCase(String roomtype);
    
    List<room> findByGuestGreaterThanEqual(int guests);


    @Query("""
    	    SELECT r
    	    FROM room r
    	    WHERE r.guest >= :guests
    	    AND r.available = true
    	    AND NOT EXISTS (
    	        SELECT b
    	        FROM booking b
    	        WHERE b.room = r
    	        AND b.status <> 'Cancelled'
    	        AND b.checkindate < :checkout
    	        AND b.checkoutdate > :checkin
    	    )
    	""")
    	List<room> searchRooms(
    	        @Param("checkin") LocalDateTime checkin,
    	        @Param("checkout") LocalDateTime checkout,
    	        @Param("guests") int guests
    	);
}