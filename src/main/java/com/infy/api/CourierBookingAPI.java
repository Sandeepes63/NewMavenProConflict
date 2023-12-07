package com.infy.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.dto.BookingDTO;
import com.infy.exception.InfyCourierException;
import com.infy.service.BookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/infycourier")
@Validated
public class CourierBookingAPI {

	@Autowired
	private BookingService bookingService;

	@Autowired
	private Environment environment;

	@PostMapping(value="/courier")
	public ResponseEntity<String> bookCourier(@Valid @RequestBody BookingDTO bookingDTO) throws InfyCourierException {
		
		
		if(bookingDTO.getWeight()==null) {
			
			return ResponseEntity.badRequest().body(environment.getProperty("booking.weight.absent")); 
		}
		else if(bookingDTO.getWeight()<30) {
			return ResponseEntity.badRequest().body(environment.getProperty("booking.invalid.weight"));
		}
		
		if(bookingDTO.getPriority()==null) {
			return ResponseEntity.badRequest().body(environment.getProperty("booking.priority.absent"));
		}
		else if (!bookingDTO.getPriority().matches("(LOW|MEDIUM|HIGH)")) {
			return ResponseEntity.badRequest().body(environment.getProperty("booking.invalid.priority"));
		}
		
		if(bookingDTO.getStatus()!=null) {
			return ResponseEntity.badRequest().body(environment.getProperty("booking.invalid.status"));
		}
		
		if(bookingDTO.getSource()==null) {
			return ResponseEntity.badRequest().body(environment.getProperty("booking.source.absent"));
		}else if (!bookingDTO.getSource().matches("^[A-Za-z][A-Za-z ]*$")) {
			return ResponseEntity.badRequest().body(environment.getProperty("booking.source.invalid"));
		}
		
		if(bookingDTO.getDestination()==null) {
			return ResponseEntity.badRequest().body(environment.getProperty("booking.destination.absent"));
		} else if (!bookingDTO.getDestination().matches("^[A-Za-z][A-Za-z ]*$")) {
			return ResponseEntity.badRequest().body(environment.getProperty("booking.destination.invalid"));
		}
		
		Integer bookingId = bookingService.bookCourier(bookingDTO);
		
		String successMessage = environment.getProperty("API.BOOKING_SUCCESS")+ bookingId;
		
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);

	}

	@GetMapping(value= "/courier/{bookingId}")
	public ResponseEntity<String> getCourierDetails(@Valid @PathVariable Integer bookingId) throws InfyCourierException {
		
		if(bookingId.toString().length()!=5) {
			return ResponseEntity.badRequest().body(environment.getProperty("booking.invalid.bookingId"));
		}
		
		BookingDTO courierDetail = bookingService.getCourierDetail(bookingId);
		return new ResponseEntity(courierDetail, HttpStatus.OK);
				
	}

}
