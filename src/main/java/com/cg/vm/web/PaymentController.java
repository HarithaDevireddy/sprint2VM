package com.cg.vm.web;

import java.util.List;
import javax.validation.Valid;
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
import com.cg.vm.domain.Payment;
import com.cg.vm.serviceimpl.MapValidationErrorService;
import com.cg.vm.serviceimpl.PaymentServiceImpl;

@RestController
@RequestMapping("/api/vehiclemanagement/payment")

public class PaymentController {
	@Autowired
	private PaymentServiceImpl paymentService;
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("/add")
	public ResponseEntity<?> createNewPayment(@Valid @RequestBody Payment payment,BindingResult result)
	{
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if(errorMap!=null) return errorMap;
		Payment pay=paymentService.saveorUpdatePayment(payment);
		return new ResponseEntity<Payment>(pay,HttpStatus.OK);
	}
	@GetMapping("/all")
	public ResponseEntity<List<Payment>>viewAllPayments() {
		List<Payment> payments = paymentService.viewAllPayments();
		return new ResponseEntity<List<Payment>>(payments,HttpStatus.OK);
		
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> cancelPayment(@PathVariable Long id)
	{
	   paymentService.removePaymentById(id);
	  return new ResponseEntity<String>("Deleted payment Successfully",HttpStatus.OK);
	}
	@GetMapping("/find/{id}")
	public ResponseEntity<Payment> findById(@PathVariable Long id)
	{
	Payment payment= paymentService.findPaymentById(id);
	  return new ResponseEntity<Payment>(payment,HttpStatus.OK);
	}
	
	@GetMapping("/booking/{booking_id}")
	public ResponseEntity<Payment> findByBookingId(@PathVariable int booking_id)
	{
	Payment payment= paymentService.findByBookingId(booking_id);
	  return new ResponseEntity<Payment>(payment,HttpStatus.OK);
	}
	
	@GetMapping("/vehicle/{vehicle_id}")
	public ResponseEntity<Payment> findByVehicleId(@PathVariable long vehicle_id)
	{
	Payment payment= paymentService.findByVehicleId(vehicle_id);
	  return new ResponseEntity<Payment>(payment,HttpStatus.OK);
	}
	
	
}
