package com.cg.vm.serviceimpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cg.vm.bean.Booking;
import com.cg.vm.bean.Customer;
import com.cg.vm.bean.Driver;
import com.cg.vm.bean.Payment;
import com.cg.vm.bean.Vehicle;

import com.cg.vm.repository.PaymentRepository;
import com.cg.vm.service.PaymentService;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

public class PaymentServiceImplTest {

	PaymentService paymentService;

	@Mock
	PaymentRepository paymentRepository;

	@SuppressWarnings("deprecation")
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		paymentService = new PaymentServiceImpl(paymentRepository);

	}

	@Test
	public void testAddPayment() {
		Driver driver = new Driver(1, "Ram", "sharma", "mumbai", "8237776772", "ram@gmail", "GA45789");
		Vehicle vehicle = new Vehicle((long) 2, "MH124997", "CAR", "AC", "Maruti", "pune", 5, 12, 2000, driver);
		Customer customer = new Customer((long) 1, "swastika", "shetti", "swastikashet@gmail.com", "2345678976", "goa",
				null);
		LocalDate date = LocalDate.of(2020, 02, 1);
		LocalDate date1 = LocalDate.of(2020, 02, 2);
		Booking booking = new Booking(9, customer, vehicle, date, date1, "driver", 100, 1000);

		Payment payment = new Payment((long) 1, "online", date1, "accepted", booking);
		when(paymentRepository.save(payment)).thenReturn(payment);
		Payment expected = paymentService.saveorUpdatePayment(payment);
		assertEquals(expected, payment);

	}

	@Test
	public void testRemovePaymentById() {
		Driver driver = new Driver(1, "Ram", "sharma", "mumbai", "8237776772", "ram@gmail", "GA45789");
		Vehicle vehicle = new Vehicle((long) 2, "MH124997", "CAR", "AC", "Maruti", "pune", 5, 12, 2000, driver);
		Customer customer = new Customer((long) 1, "swastika", "shetti", "swastikashet@gmail.com", "2345678976", "goa",
				null);
		LocalDate date = LocalDate.of(2020, 02, 1);
		LocalDate date1 = LocalDate.of(2020, 02, 2);
		Booking booking = new Booking(9, customer, vehicle, date, date1, "driver", 100, 1000);

		Payment payment = new Payment((long) 1, "online", date1, "accepted", booking);
		long l = 1;
		when(paymentRepository.findByPaymentId(l)).thenReturn(payment);
		Payment expected = paymentService.removePaymentById(l);
		verify(paymentRepository, times(1)).deleteById(l);
		assertNull(expected);

	}

	@Test(expected = NullPointerException.class)
	public void testfindPaymentByBookingId() throws NullPointerException {
		Driver driver = new Driver(1, "Ram", "sharma", "mumbai", "8237776772", "ram@gmail", "GA45789");
		Vehicle vehicle = new Vehicle((long) 2, "MH124997", "CAR", "AC", "Maruti", "pune", 5, 12, 2000, driver);
		Customer customer = new Customer((long) 1, "swastika", "shetti", "swastikashet@gmail.com", "2345678976", "goa",
				null);
		LocalDate date = LocalDate.of(2020, 02, 1);
		LocalDate date1 = LocalDate.of(2020, 02, 2);
		Booking booking = new Booking(9, customer, vehicle, date, date1, "driver", 100, 1000);

		Payment payment = new Payment((long) 1, "online", date1, "accepted", booking);
		long l = 1;
		when(paymentRepository.findByBooking(booking)).thenReturn(payment);
		Payment expected = paymentService.findByBookingId(l);
		assertEquals(expected, payment);

	}

	@Test(expected = NullPointerException.class)
	public void testfindPaymentByVehicleId() throws NullPointerException {
		Driver driver = new Driver(1, "Ram", "sharma", "mumbai", "8237776772", "ram@gmail", "GA45789");
		Vehicle vehicle = new Vehicle((long) 2, "MH124997", "CAR", "AC", "Maruti", "pune", 5, 12, 2000, driver);
		Customer customer = new Customer((long) 1, "swastika", "shetti", "swastikashet@gmail.com", "2345678976", "goa",
				null);
		LocalDate date = LocalDate.of(2020, 02, 1);
		LocalDate date1 = LocalDate.of(2020, 02, 2);
		Booking booking = new Booking(9, customer, vehicle, date, date1, "driver", 100, 1000);

		Payment payment = new Payment((long) 1, "online", date1, "accepted", booking);
		when(paymentRepository.findByVehicleId((long) 2)).thenReturn(payment);
		Payment expected = paymentService.findByVehicleId((long) 2);
		assertEquals(expected, payment);

	}

	@Test
	public void testFindByPaymentId() {
		Driver driver = new Driver(1, "Ram", "sharma", "mumbai", "8237776772", "ram@gmail", "GA45789");
		Vehicle vehicle = new Vehicle((long) 2, "MH124997", "CAR", "AC", "Maruti", "pune", 5, 12, 2000, driver);
		Customer customer = new Customer((long) 1, "swastika", "shetti", "swastikashet@gmail.com", "2345678976", "goa",
				null);
		LocalDate date = LocalDate.of(2020, 02, 1);
		LocalDate date1 = LocalDate.of(2020, 02, 2);
		Booking booking = new Booking(9, customer, vehicle, date, date1, "driver", 100, 1000);

		Payment payment = new Payment((long) 1, "online", date1, "accepted", booking);
		when(paymentRepository.findByPaymentId((long) 1)).thenReturn(payment);
		Payment expected = paymentService.findPaymentById((long) 1);
		assertEquals(expected, payment);
	}
}