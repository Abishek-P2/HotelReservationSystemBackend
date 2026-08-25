package edu.jsp.Hotel_Reservation.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.jsp.Hotel_Reservation.entity.booking;

public interface bookingrepository extends JpaRepository<booking, Long>{
	
	List<booking> findByUserId(long userid);
	
	 boolean existsByRoomIdAndCheckindateLessThanAndCheckoutdateGreaterThan(
	            long roomId,
	            LocalDateTime checkout,
	            LocalDateTime checkin
	    );
   
	@Query(value = "select b from booking b where b.user.id=?1")
	public List<booking> GetBookingByUserId(long id);
	
	@Query("select b from booking b where b.room.roomno=?1")
	public List<booking> GetBookingByRoomNo(long roomno);
	
	@Query("""
		    SELECT b.room.id
		    FROM booking b
		    WHERE b.checkindate < :checkout
		    AND b.checkoutdate > :checkin
		""")
		List<Long> findBookedRoomIds(
		        @Param("checkin") LocalDateTime checkin,
		        @Param("checkout") LocalDateTime checkout);
	
	
	@Query("""
		    SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END
		    FROM booking b
		    WHERE b.room.id = :roomId
		    AND b.status = 'Confirmed'
		    AND b.checkindate < :checkout
		    AND b.checkoutdate > :checkin
		""")
		boolean existsActiveBooking(
		        @Param("roomId") long roomId,
		        @Param("checkin") LocalDateTime checkin,
		        @Param("checkout") LocalDateTime checkout
		);
} 
      

