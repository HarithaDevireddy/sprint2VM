package com.cg.vm.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.cg.vm.bean.Booking;
import com.cg.vm.bean.Payment;


public interface PaymentRepository extends JpaRepository<Payment, Long> {

	Payment findByPaymentId(Long paymentId);
	
	Payment findByBooking(Booking booking);
	
	@Query(value = "SELECT * FROM payment where booking_id IN (Select booking_id from booking where vehicle_id =:vehicle_id);", nativeQuery = true)
	Payment findByVehicleId(Long vehicle_id);
 

}