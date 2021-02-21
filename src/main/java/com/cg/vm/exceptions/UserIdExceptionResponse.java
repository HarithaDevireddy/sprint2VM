package com.cg.vm.exceptions;

public class UserIdExceptionResponse {
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public UserIdExceptionResponse(String userId) {
		super();
		this.userId = userId;
	}

}
