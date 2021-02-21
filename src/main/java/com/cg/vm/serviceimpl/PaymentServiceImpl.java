package com.cg.vm.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.vm.bean.Booking;
import com.cg.vm.bean.Payment;
import com.cg.vm.bean.Vehicle;
import com.cg.vm.exceptions.BookingIdException;
import com.cg.vm.exceptions.PaymentIdException;
import com.cg.vm.exceptions.VehicleNotFoundException;
import com.cg.vm.repository.BookingRepository;
import com.cg.vm.repository.PaymentRepository;
import com.cg.vm.repository.VehicleRepository;
import com.cg.vm.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private VehicleRepository vehicleRepository;

	public PaymentServiceImpl(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}

	public Payment saveorUpdatePayment(Payment payment) {
		return paymentRepository.save(payment);
	}

	public List<Payment> viewAllPayments() {
		List<Payment> payments = paymentRepository.findAll();
		return payments;
	}

	public Payment removePaymentById(Long id) {

		Payment payment1 = paymentRepository.findByPaymentId(id);
		if (payment1 == null) {
			throw new PaymentIdException("Invalid PaymentId");
		} else
			paymentRepository.deleteById(id);
		return null;
	}

	public Payment findPaymentById(Long id) {
		Payment payment = paymentRepository.findByPaymentId(id);
		if (payment == null)
			throw new PaymentIdException("Payment Id not found");
		else
			return payment;
	}

	@Override
	public Payment findByBookingId(Long bookingId) {
		Booking booking = bookingRepository.findByBookingId(bookingId);
		if (booking == null)
			throw new BookingIdException("Invalid Booking Id");
		return paymentRepository.findByBooking(booking);
	}

	@Override
	public Payment findByVehicleId(Long id) {
		Vehicle vehicle = vehicleRepository.findByVehicleId(id);
		if (vehicle == null)
			throw new VehicleNotFoundException("Vehicle Not Present");
		return paymentRepository.findByVehicleId(id);
	}

}