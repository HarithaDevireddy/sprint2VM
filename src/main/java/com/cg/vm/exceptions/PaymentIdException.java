package com.cg.vm.exceptions;


public class PaymentIdException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PaymentIdException() {
		super();
	}
	
	public PaymentIdException(String msg) {
		super(msg);
		
	}

}