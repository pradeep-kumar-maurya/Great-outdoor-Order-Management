package com.capgemini.go.exceptions;


@SuppressWarnings("serial")
public class OrderException extends Exception {
	public OrderException(String errorMsg) {
		super(errorMsg);
	}
}
