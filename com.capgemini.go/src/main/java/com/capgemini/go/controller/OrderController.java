package com.capgemini.go.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.go.dto.Cart;
import com.capgemini.go.dto.Orders;
import com.capgemini.go.exceptions.OrderException;
import com.capgemini.go.exceptions.ProductException;
import com.capgemini.go.service.IOrderService;
import com.capgemini.go.utility.Result;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private IOrderService service;

	//creating new order by passing Orders parameters
	@PostMapping("/addOrder")
	public ResponseEntity<Result> createNewOrder(@RequestBody Orders orders) throws OrderException {
		String orderId = service.createNewOrder(orders);
		return new ResponseEntity<>(new Result(orderId, "accepted","not yet dispatched"), HttpStatus.OK);
	}

	//the details of the order are fetched according to the userId
	@GetMapping("/getOrders")
	public ResponseEntity<List<Orders>> getOrdersByUserId(@RequestBody String userId) throws OrderException {
		List<Orders> orders = service.findOrdersByUserId(userId);
		return new ResponseEntity<>(orders,HttpStatus.OK);
	}
	
	//the particular order details are fetched according to the orderId
	@GetMapping("/getOrders/orderId")
	public ResponseEntity<Orders> getOrdersByOrderId(@RequestBody String orderId) throws OrderException {
		Orders orders = service.findOrdersByOrderId(orderId);
		return new ResponseEntity<>(orders,HttpStatus.OK);
	}
	
	//the order gets deleted by providing the orderId
	@DeleteMapping("/deleteOrder")
	public ResponseEntity<String> removeOrder(@RequestBody String orderId) throws OrderException {
		String string = service.cancelOrder(orderId);
		return new ResponseEntity<>(string, HttpStatus.OK);
	}
	
	//deleting a product from order by providing the productId
	@DeleteMapping("/deleteProductFromOrder")
	public ResponseEntity<String> removeProduct(@RequestBody String productId) throws ProductException{
		String string = service.removeProduct(productId);
		return new ResponseEntity<>(string, HttpStatus.OK);
	}
	
	//Adding items in the Cart
	@PostMapping("/addItemToCart")
	public ResponseEntity<List<Cart>> additemToCart(@RequestBody Cart cart){
		List<Cart> cartList = service.addItemToCart(cart);
		return new ResponseEntity<>(cartList, HttpStatus.OK);
	}
}