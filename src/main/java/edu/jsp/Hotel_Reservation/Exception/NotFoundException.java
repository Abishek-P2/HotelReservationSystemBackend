package edu.jsp.Hotel_Reservation.Exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NotFoundException extends RuntimeException{
    
	private String resourcename;
	private String fieldname;
	private long fieldid;
	
	public String getMessage() {
		return resourcename +"\t"+"Not Found For \t" +fieldname +"-"+fieldid;
	}
}
