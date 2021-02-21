package com.cg.vm.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookingId;
	
	@ManyToOne(optional = false , fetch = FetchType.LAZY)//booking cannot be stored without customer
	@JoinColumn(name = "cust_id")
	@Valid
	private Customer customer;
    
	@OneToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name = "vehicle_id")
	@Valid
    private Vehicle vehicle;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate bookingDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate bookedTillDate;

	@NotBlank(message = "Booking Description Required")
	private String bookingDescription;
    
	//@NotEmpty(message="Please Enter Distance")
	private double distance;
    
	private double totalCost;

	public Booking()
	{
		super();
		
	}
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
 
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public LocalDate getBookedTillDate() {
		return bookedTillDate;
	}

	public void setBookedTillDate(LocalDate bookedTillDate) {
		this.bookedTillDate = bookedTillDate;
	}

	public String getBookingDescription() {
		return bookingDescription;
	}

	public void setBookingDescription(String bookingDescription) {
		this.bookingDescription = bookingDescription;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public Booking(int bookingId, @Valid Customer customer, @Valid Vehicle vehicle, LocalDate bookingDate,
			LocalDate bookedTillDate, @NotBlank(message = "Booking Description Required") String bookingDescription,
			double distance, double totalCost) {
		super();
		this.bookingId = bookingId;
		this.customer = customer;
		this.vehicle = vehicle;
		this.bookingDate = bookingDate;
		this.bookedTillDate = bookedTillDate;
		this.bookingDescription = bookingDescription;
		this.distance = distance;
		this.totalCost = totalCost;
	}
}
