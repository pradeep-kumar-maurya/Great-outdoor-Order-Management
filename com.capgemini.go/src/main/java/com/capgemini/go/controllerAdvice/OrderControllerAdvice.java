package com.capgemini.go.controllerAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.capgemini.go.exceptions.OrderException;
import com.capgemini.go.exceptions.ProductException;

@RestControllerAdvice
public class OrderControllerAdvice {
	
	@ExceptionHandler()
	public ResponseEntity<String> invalidUserId(OrderException exception){
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler()
	public ResponseEntity<String> invalidProductId(ProductException exception){
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
