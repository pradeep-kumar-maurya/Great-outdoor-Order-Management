package com.capgemini.go.utility;

public class Result {
	
	private final String response;
	private final String status;
	
	public Result(String response, String status) {
		super();
		this.response = response;
		this.status = status;
	}

	public String getResponse() {
		return response;
	}

	public String getStatus() {
		return status;
	}
	
}
