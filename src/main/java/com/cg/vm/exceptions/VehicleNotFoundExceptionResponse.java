package com.cg.vm.exceptions;

public class VehicleNotFoundExceptionResponse {
	private String vehicleId;


	public VehicleNotFoundExceptionResponse(String vehicleId) {
		
		super();
		this.vehicleId = vehicleId;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

}
