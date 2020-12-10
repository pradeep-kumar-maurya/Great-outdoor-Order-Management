package com.capgemini.go.exceptions;

@SuppressWarnings("serial")
public class ProductException extends Exception {
	public ProductException(String errorMsg) {
		super(errorMsg);
	}
}
