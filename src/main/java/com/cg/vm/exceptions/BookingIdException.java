package com.cg.vm.exceptions;

public class BookingIdException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public BookingIdException()
	{
		super();
	}
	
	public BookingIdException(String msg)
	{
		super(msg);
	}
}

