package com.capgemini.go.exceptions;

@SuppressWarnings("serial")
public class CustomerException extends Exception {
	public CustomerException(String errorMsg) {
		super(errorMsg);
	}
}
