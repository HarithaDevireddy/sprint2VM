package com.cg.vm.exceptions;

public class PaymentIdExceptionResponse {
	private String Id;

	public PaymentIdExceptionResponse(String Id) {
		super();
		this.Id = Id;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		this.Id = id;
	}

	

}
