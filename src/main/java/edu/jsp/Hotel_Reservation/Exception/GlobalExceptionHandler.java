package edu.jsp.Hotel_Reservation.Exception;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity<String> HandleNotFoundException(NotFoundException ex){
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> HandleValidation(MethodArgumentNotValidException ex){
    	LinkedHashMap<String, String> map=new LinkedHashMap<String, String>();
    	List<FieldError> errors=ex.getBindingResult().getFieldErrors();
    	
    	for(FieldError err:errors) {
    		map.put(err.getField(), err.getDefaultMessage());
    	}
    	return new ResponseEntity<Map<String,String>>(map,HttpStatus.BAD_REQUEST);
    }
}
