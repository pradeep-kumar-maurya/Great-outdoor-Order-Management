package com.capgemini.go.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.go.dto.Orders;
import com.capgemini.go.service.IOrderService;
import com.capgemini.go.utility.Result;

@RestController
@RequestMapping("/order")
//@CrossOrigin("*")
public class OrderController {

	@Autowired
	private IOrderService service;

	// creating new order by passing Orders parameters
	@PostMapping("/addOrder")
	public ResponseEntity<Result> createNewOrder(@RequestBody Orders orders){
		String orderId = service.createNewOrder(orders);
		return new ResponseEntity<>(new Result(orderId, "accepted"), HttpStatus.OK);
	}

	//the details of the order are fetched according to the userId
	@GetMapping("/getOrders/{userId}")
	public ResponseEntity<List<Orders>> getOrders(@PathVariable("userId") String userId) {
		List<Orders> orders = service.findOrdersByUserId(userId);
		return new ResponseEntity<>(orders,HttpStatus.OK);
	}

	//The order gets deleted by providing the orderId
	@DeleteMapping("/deleteOrder/{orderId}")
	public ResponseEntity<String> removeOrder(@PathVariable("orderId") String orderId) {
		String string = service.cancelOrder(orderId);
		return new ResponseEntity<>(string, HttpStatus.OK);
	}

}