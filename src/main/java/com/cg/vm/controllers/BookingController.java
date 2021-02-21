package com.cg.vm.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.cg.vm.bean.Booking;
import com.cg.vm.serviceimpl.BookingServiceImpl;
import com.cg.vm.serviceimpl.MapValidationErrorService;

@RestController
@RequestMapping("/api/vehiclemanagement/booking")
public class BookingController {

	@Autowired
	private BookingServiceImpl bookingService;

	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	@PostMapping("/add")
	public ResponseEntity<?> createNewBooking(@RequestBody Booking booking, BindingResult result) {
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if (errorMap != null)
			return errorMap;
		Booking book = bookingService.saveOrUpdate(booking);
		return new ResponseEntity<Booking>(book, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getByBookingId(@PathVariable Long id) {
		Booking book = bookingService.findByBookingId(id);
		return new ResponseEntity<Booking>(book, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Booking>> viewAllBookings() {
		List<Booking> bookings = bookingService.viewAllBookings();
		return new ResponseEntity<List<Booking>>(bookings, HttpStatus.OK);
	}

	@GetMapping("/date/{bookingDate}")
	public ResponseEntity<List<Booking>> viewAllByBookingDate(@PathVariable String bookingDate)

	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
		LocalDate d = LocalDate.parse(bookingDate, formatter);
		List<Booking> bookingByDate = bookingService.viewBookingByBookingDate(d);
		return new ResponseEntity<List<Booking>>(bookingByDate, HttpStatus.FOUND);
	}

	@GetMapping("/customer/{cust_id}")
	public ResponseEntity<List<Booking>> viewAllByCustomerId(@PathVariable Long cust_id) {
		List<Booking> bookingsByCustomer = bookingService.viewAllBookingsByCustomer(cust_id);
		return new ResponseEntity<List<Booking>>(bookingsByCustomer, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{bookingId}")
	public ResponseEntity<String> cancelBookingById(Long bookingId) {
		bookingService.cancelBookingById(bookingId);
		return new ResponseEntity<String>("Booking Id" + " " + bookingId + " " + "cancelled successfully",
				HttpStatus.OK);
	}

	@GetMapping("/vehicle/{vehicle_id}")
	public ResponseEntity<List<Booking>> viewAllByVehicleId(@PathVariable Long vehicle_id) {
		List<Booking> bookingsByVehicle = bookingService.viewAllBookingsByVehicle(vehicle_id);
		return new ResponseEntity<List<Booking>>(bookingsByVehicle, HttpStatus.OK);
	}
}