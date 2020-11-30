package com.capgemini.go.utility;

public class Result {
	
	private final String orderId;
	private final String status;
	private final String orderDispatchStatus;
	
	public Result(String orderId, String status, String orderDispatchStatus) {
		super();
		this.orderId = orderId;
		this.status = status;
		this.orderDispatchStatus = orderDispatchStatus;
	}

	public String getOrderId() {
		return orderId;
	}

	public String getStatus() {
		return status;
	}

	public String getOrderDispatchStatus() {
		return orderDispatchStatus;
	}
}