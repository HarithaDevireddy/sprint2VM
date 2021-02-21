package com.cg.vm.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice//rest controller advice
@RestController
public class CustomResponseEntityHandler extends ResponseEntityExceptionHandler {

	/**
	 * Customer ID Exception Handler
	 * @param ex Customer Id Exception
	 * @param wx Web Request
	 * @return Response Entity
	 */
	@ExceptionHandler 
	public ResponseEntity<Object> handleCustomerIdException(CustomerIdException ex, WebRequest wx) {

		CustomerIdExceptionResponse customerIdExceptionResponse = new CustomerIdExceptionResponse(ex.getMessage());
		return new ResponseEntity<Object>(customerIdExceptionResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Customer Email ID Exception Handler
	 * @param ex Customer Email Id Exception
	 * @param wx Web Request
	 * @return Response Entity
	 */
	@ExceptionHandler
	public ResponseEntity<Object> handleEmailIdException(EmailIdException ex, WebRequest wx) {

		EmailIdExceptionResponse emailIdExceptionRespone = new EmailIdExceptionResponse(ex.getMessage());
		return new ResponseEntity<Object>(emailIdExceptionRespone, HttpStatus.BAD_REQUEST);
	}

	/**
	 * User ID Exception Handler
	 * @param ex User Id Exception
	 * @param wx Web Request
	 * @return Response Entity
	 */
	@ExceptionHandler
	public ResponseEntity<Object> handleUserIdException(UserIdException ex, WebRequest wx) {

		UserIdExceptionResponse userIdExceptionRespone = new UserIdExceptionResponse(ex.getMessage());
		return new ResponseEntity<Object>(userIdExceptionRespone, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Driver ID Exception Handler
	 * @param ex Driver Id Exception
	 * @param wx Web Request
	 * @return Response Entity
	 */
	@ExceptionHandler
	public ResponseEntity<Object> handleDriverIdException(DriverNotFoundException ex, WebRequest request) {
		DriverNotFoundExceptionResponse driverNotFoundExceptionResponse = new DriverNotFoundExceptionResponse(
				ex.getMessage());
		return new ResponseEntity<Object>(driverNotFoundExceptionResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Vehicle ID Exception Handler
	 * @param ex Vehicle Id Exception
	 * @param wx Web Request
	 * @return Response Entity
	 */
	@ExceptionHandler
	public ResponseEntity<Object> handleVehicleIdException(VehicleNotFoundException ex, WebRequest request) {
		VehicleNotFoundExceptionResponse vehicleNotFoundExceptionResponse = new VehicleNotFoundExceptionResponse(
				ex.getMessage());
		return new ResponseEntity<Object>(vehicleNotFoundExceptionResponse, HttpStatus.BAD_REQUEST);
	}
	/**
	 * Booking ID Exception Handler
	 * @param ex Booking Id Exception
	 * @param wx Web Request
	 * @return Response Entity
	 */
	@ExceptionHandler
	public ResponseEntity<Object> handleBookingIdException(BookingIdException ex, WebRequest wx) {

		BookingIdExceptionResponse bookingIdExceptionRespone = new BookingIdExceptionResponse (ex.getMessage());
		return new ResponseEntity<Object>(bookingIdExceptionRespone ,HttpStatus.BAD_REQUEST);
		}
	
	/**
	 * Payment ID Exception Handler
	 * @param ex Payment Id Exception
	 * @param wx Web Request
	 * @return Response Entity
	 */
	@ExceptionHandler
	public ResponseEntity<Object> handlePaymentIdException(PaymentIdException ex, WebRequest wx) {

		PaymentIdExceptionResponse paymentIdExceptionRespone = new PaymentIdExceptionResponse(ex.getMessage());
		return new ResponseEntity<Object>(paymentIdExceptionRespone,HttpStatus.BAD_REQUEST);
		}
}
