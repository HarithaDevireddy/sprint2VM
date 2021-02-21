package com.cg.vm.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DriverNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DriverNotFoundException()
	{
		super();
	}
	public DriverNotFoundException(String msg)
	{
		super(msg);
	}
	

}