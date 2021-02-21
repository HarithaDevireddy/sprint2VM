package com.cg.vm.controllers;

 

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cg.vm.VmtoolapiApplication;
import com.cg.vm.bean.Booking;
import com.cg.vm.bean.Customer;
import com.cg.vm.bean.Driver;
import com.cg.vm.bean.Vehicle;
import com.cg.vm.serviceimpl.BookingServiceImpl;

 


@SpringBootTest(classes = VmtoolapiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookingControllerTest {
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @MockBean
    private BookingServiceImpl bookingService;
    @LocalServerPort
    private int port;
    private Booking b1,b2;
    private String getRootUrl()
    {
        return "http://localhost:" + port;
    }
    
    @BeforeEach
    public void setMockData()
    {
    	Driver driver = new Driver(1, "Ram", "sharma", "mumbai", "8237776772", "ram@gmail", "GA45789");
		Vehicle vehicle = new Vehicle((long) 2, "MH124997", "CAR", "AC", "Maruti", "pune", 5, 12, 2000, driver);
		Customer customer = new Customer((long) 1, "swastika", "shetti", "swastikashet@gmail.com", "2345678976", "goa",
				null);
		LocalDate date = LocalDate.of(2020, 02, 1);
		LocalDate date1 = LocalDate.of(2020, 02, 2);
		b1 = new Booking(9, customer, vehicle, date, date1, "driver", 100, 1000);
        
        
        LocalDate date2 = LocalDate.of(2020, 02, 3);
        LocalDate date3 = LocalDate.of(2020, 02, 4);
        b2 = new Booking(2,null,null,date2,date3,"self",200,2000);
    }

 

    @Test
    public void testAddBooking()
    {
        
        ResponseEntity<Booking> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/vehiclemanagement/booking/add", b1,
                Booking.class);
        System.out.println("Booking 1 : "+b1);
        assertNotNull(postResponse);

 

    }
    
    @SuppressWarnings("unused")
    @Test
    void testgetBookingById()
    {
    
    ResponseEntity<Booking> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/vehiclemanagement/booking/add", b2,
            Booking.class);    
        ResponseEntity<Booking> postResponse1 = restTemplate.getForEntity(getRootUrl() + "/api/vehiclemanagement/booking/2",Booking.class);
        System.out.println("Booking 2 : "+b2);
        System.out.println("Booking 2 after fetching by ID : "+b2);
        assertEquals(postResponse1.getStatusCode(),HttpStatus.OK);
    }
    
    @SuppressWarnings("unused")
    @Test
    void testCancelBookingById()
    {
        ResponseEntity<Booking> postResponse1 = restTemplate.postForEntity(getRootUrl() + "/api/vehiclemanagement/booking/add", b1,
            Booking.class);
        Booking postResponse2 = restTemplate.getForObject(getRootUrl() + "/api/vehiclemanagement/booking/1",Booking.class);
        restTemplate.delete(getRootUrl() + "/api/vehiclemanagement/booking/delete/1", Booking.class);
        Booking postResponse4 = restTemplate.getForObject(getRootUrl() + "/api/vehiclemanagement/booking/1",Booking.class);
        assertEquals(null, postResponse4);
    
    }

 

    
    @Test
    public void testgetBookingByBookingDate() {
        LocalDate date = LocalDate.of(2020, 02, 01);
        @SuppressWarnings("unused")
		ResponseEntity<Booking> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/vehiclemanagement/booking/add", b2,
                    Booking.class);    
        ResponseEntity<List<Booking>> getResponse =
                restTemplate.exchange((getRootUrl() +"/api/vehiclemanagement/booking/date/2020-02-01"),
                            HttpMethod.GET, null, new ParameterizedTypeReference<List<Booking>>() {
                    },date);
        List<Booking> books = getResponse.getBody();
        assertNotNull(books);
    }

 

    
}