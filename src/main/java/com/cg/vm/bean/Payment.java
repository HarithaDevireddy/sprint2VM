package com.cg.vm.bean;

import java.time.LocalDate;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;



@Entity
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Long paymentId;
	
	@NotBlank(message = "select payment mode")
	private String paymentMode;
	
	
	private LocalDate paymentDate;
	
    @NotBlank(message="select payment status")
	private String paymentStatus;
    
    
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name="booking_id",nullable = false)
    private Booking booking;

	
	public Payment(Long paymentId, @NotBlank(message = "select payment mode") String paymentMode, LocalDate date1,
			@NotBlank(message = "select payment status") String paymentStatus, Booking booking) {
		super();
		this.paymentId = paymentId;
		this.paymentMode = paymentMode;
		this.paymentDate = date1;
		this.paymentStatus = paymentStatus;
		this.booking = booking;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
		
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Payment()
	{
		
	}

}

