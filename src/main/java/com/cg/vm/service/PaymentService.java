package com.cg.vm.service;

import java.util.List;

import com.cg.vm.bean.Payment;

public interface PaymentService {
	public Payment saveorUpdatePayment(Payment payment);

	public List<Payment> viewAllPayments();

	public Payment removePaymentById(Long id);

	public Payment findPaymentById(Long id);

	public Payment findByBookingId(Long bookingId);

	public Payment findByVehicleId(Long id);

}
