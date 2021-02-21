package com.cg.vm.repository;

import java.time.LocalDate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.vm.bean.Booking;


public interface BookingRepository extends JpaRepository<Booking, Long> {

	Booking findByBookingId(long bookingId);

	List<Booking> findAllByBookingDate(LocalDate bookingDate);

	@Query(value = "SELECT * FROM public.booking WHERE cust_id = ?;", nativeQuery = true)
	List<Booking> findAllByBooking_Cust_Id(Long id);

	@Query(value = "SELECT * FROM public.booking WHERE vehicle_id = ?;", nativeQuery = true)
	List<Booking> findAllByBooking_Vehicle_Id(Long id);

}