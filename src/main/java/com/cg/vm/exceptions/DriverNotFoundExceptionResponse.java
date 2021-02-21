package com.cg.vm.exceptions;

public final class DriverNotFoundExceptionResponse {
	private String driverId;
	public DriverNotFoundExceptionResponse(String driverId) {
		super();
		this.driverId=driverId;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	

}
