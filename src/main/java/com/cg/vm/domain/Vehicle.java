package com.cg.vm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="vehicle")
public class Vehicle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="vehicle_id")
	private Long vehicleId;
	private String vehicleNumber;
	
	@NotBlank(message = "Vehicle Type  Required")
	private String type;
	
	@NotBlank(message = "Vehicle Category Required")
	private String category;
	
	@NotBlank(message = "Vehicle Description Required")
    private String description;
	
	@NotBlank(message = "Vehicle Location Required")
    private String location;
	
	@NotNull(message = "Vehicle Capacity Required")
    private int capacity;
	
	@NotNull(message = "chargeperKm required")
	private double chargesPerKM;
	
	@NotNull(message = "fixedcharges required")
	private double fixedCharges;
	
	@OneToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "driver_id", nullable = true, updatable = true)
	private Driver driver;
	
	public Vehicle() {
		super();
	}
	
	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	public Long getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public double getChargesPerKM() {
		return chargesPerKM;
	}
	public void setChargesPerKM(double chargesPerKM) {
		this.chargesPerKM = chargesPerKM;
	}
	public double getFixedCharges() {
		return fixedCharges;
	}
	public void setFixedCharges(double fixedCharges) {
		this.fixedCharges = fixedCharges;
	}
	public Vehicle(Long vehicleId, String vehicleNumber, @NotBlank(message = "Vehicle Type  Required") String type,
			@NotBlank(message = "Vehicle Category Required") String category,
			@NotBlank(message = "Vehicle Description Required") String description,
			@NotBlank(message = "Vehicle Location Required") String location,
			@NotNull(message = "Vehicle Capacity Required") int capacity,
			@NotNull(message = "chargeperKm required") double chargesPerKM,
			@NotNull(message = "fixedcharges required") double fixedCharges, @Valid Driver driver) {
		super();
		this.vehicleId = vehicleId;
		this.vehicleNumber = vehicleNumber;
		this.type = type;
		this.category = category;
		this.description = description;
		this.location = location;
		this.capacity = capacity;
		this.chargesPerKM = chargesPerKM;
		this.fixedCharges = fixedCharges;
		this.driver = driver;
	}

	
}
