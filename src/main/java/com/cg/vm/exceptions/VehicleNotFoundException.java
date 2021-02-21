package com.cg.vm.exceptions;

public class VehicleNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public VehicleNotFoundException()
	{
		super();
	}
	
	public  VehicleNotFoundException(String msg)
	{
		super(msg);
	}

}
