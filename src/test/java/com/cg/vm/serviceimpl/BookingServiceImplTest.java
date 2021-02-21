package com.cg.vm.serviceimpl;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.vm.bean.Booking;
import com.cg.vm.bean.Customer;
import com.cg.vm.bean.Driver;
import com.cg.vm.bean.Vehicle;
import com.cg.vm.exceptions.BookingIdException;
import com.cg.vm.repository.BookingRepository;
import com.cg.vm.service.BookingService;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingServiceImplTest {

	private Booking booking1;
	private Booking booking2;
	private Booking booking3;

	@Autowired
	BookingService bookingService;

	@MockBean
	BookingRepository bookingRepository;

	@BeforeEach
	public void setUpMockData() {
		Driver driver = new Driver(1, "Ram", "sharma", "mumbai", "8237776772", "ram@gmail", "GA45789");
		Vehicle vehicle = new Vehicle((long) 2, "MH124997", "CAR", "AC", "Maruti", "pune", 5, 12, 2000, driver);
		Customer customer = new Customer((long) 1, "swastika", "shetti", "swastikashet@gmail.com", "2345678976", "goa",
				null);
		LocalDate date = LocalDate.of(2020, 02, 1);
		LocalDate date1 = LocalDate.of(2020, 02, 2);
		booking1 = new Booking(9, customer, vehicle, date, date1, "driver", 100, 1000);

		LocalDate date4 = LocalDate.of(2020, 02, 3);
		LocalDate date5 = LocalDate.of(2020, 02, 4);
		booking2 = new Booking(2, null, null, date4, date5, "self", 200, 2000);

		Driver driver1 = new Driver(2, "Ramya", "sharma", "mumbai", "8237776778", "ramya@gmail", "GA45799");
		Vehicle vehicle1 = new Vehicle((long) 3, "MH124999", "CAR", "AC", "Maruti", "pune", 5, 12, 2000, driver1);
		Customer customer1 = new Customer((long) 2, "swas", "shetti", "swas@gmail.com", "2345678976", "goa", null);
		LocalDate date3 = LocalDate.of(2020, 02, 1);
		LocalDate date2 = LocalDate.of(2020, 02, 2);
		booking3 = new Booking(10, customer1, vehicle1, date3, date2, "driver", 100, 1000);
	}

	@Test(expected = NullPointerException.class)
	public void testAddBooking() throws NullPointerException {
		when(bookingRepository.save(booking1)).thenReturn(booking1);
		Booking booking = bookingService.saveOrUpdate(booking1);
		assertEquals(booking1, booking);
	}

	@Test
	public void testgetAllBooking() {
		when(bookingRepository.findAll()).thenReturn(Stream.of(booking1, booking2).collect(Collectors.toList()));
		assertEquals(2, bookingService.viewAllBookings().size());

	}

	@Test
	public void testfindByBookingId() {
		long id = 2;
		when(bookingRepository.findByBookingId(id)).thenReturn(booking2);
		Booking result = bookingService.findByBookingId(id);
		assertEquals(booking2, result);
	}

	@Test
	public void testfindByCustomerId() {
		long customerId = 1;
		when(bookingRepository.findAllByBooking_Cust_Id(customerId))
				.thenReturn(Stream.of(booking1, booking3).collect(Collectors.toList()));
		List<Booking> result = bookingService.viewAllBookingsByCustomer(customerId);
		assertEquals(2, result.size());
	}

	@Test
	public void testfindByVehicleId() {
		long vehicleId = 2;
		when(bookingRepository.findAllByBooking_Vehicle_Id(vehicleId))
				.thenReturn(Stream.of(booking1).collect(Collectors.toList()));
		List<Booking> result = bookingService.viewAllBookingsByVehicle(vehicleId);
		assertEquals(1, result.size());
	}

	@Test(expected = BookingIdException.class)
	public void cancelBookingByIdTest() {
		long id = 2;
		when(bookingRepository.findByBookingId(id)).thenReturn(booking2);
		bookingService.cancelBookingById(id);
		verify(bookingRepository, times(1)).deleteById(id);
		assertNull(booking2);
	}

	@Test
	public void testFindAllBookingIdNotPresentException() {
		when(bookingRepository.findById(null)).thenThrow(new BookingIdException("No Booking Id entered"));
		assertThrows(BookingIdException.class, () -> bookingService.viewAllBookings());
	}

	@Test
	public void testfindByBookingDate() {
		LocalDate date = LocalDate.of(2020, 02, 1);
		when(bookingRepository.findAllByBookingDate(date)).thenReturn(Stream.of(booking3).collect(Collectors.toList()));
		List<Booking> result = bookingService.viewBookingByBookingDate(date);
		assertEquals(1, result.size());
	}

}