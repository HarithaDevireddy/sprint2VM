package com.cg.vm.service;

import java.time.LocalDate;

import java.util.List;

import com.cg.vm.bean.Booking;

public interface BookingService {
	public Booking saveOrUpdate(Booking booking);

	public Booking findByBookingId(Long bookingId);

	public String cancelBookingById(Long bookingId);

	public List<Booking> viewBookingByBookingDate(LocalDate bookingDate);

	public List<Booking> viewAllBookings();

	public List<Booking> viewAllBookingsByCustomer(Long cust_id);

	public List<Booking> viewAllBookingsByVehicle(Long vehicle_id);

}