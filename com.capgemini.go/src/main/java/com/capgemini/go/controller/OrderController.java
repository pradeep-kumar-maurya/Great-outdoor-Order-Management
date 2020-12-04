package com.capgemini.go.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.go.dto.Cart;
import com.capgemini.go.dto.CartDto;
import com.capgemini.go.dto.Customer;
import com.capgemini.go.dto.Orders;
import com.capgemini.go.exceptions.CustomerException;
import com.capgemini.go.exceptions.OrderException;
import com.capgemini.go.exceptions.ProductException;
import com.capgemini.go.service.IOrderService;
import com.capgemini.go.utility.Result;

@RestController
@RequestMapping("/order")
@CrossOrigin("*")
public class OrderController {

	@Autowired
	private IOrderService service;

	//getting the customer details
	@GetMapping("/getCustomerDetails/{username}/{password}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("username") String username, @PathVariable("password") String password) throws CustomerException{
		Customer customer = service.getCustomer(username, password);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	//getting the items in the cart
	@GetMapping("/getCart")
	public ResponseEntity<List<CartDto>> getCart(){
		List<CartDto> cartDtoList = service.getCartItems();
		return new ResponseEntity<>(cartDtoList, HttpStatus.OK);
	}

	//creating new order by passing Orders parameters
	@PostMapping("/placeOrder")
	public ResponseEntity<Result> createNewOrder(@RequestBody CartDto cartDto) throws OrderException {
		String orderId = service.createNewOrder(cartDto);
		return new ResponseEntity<>(new Result(orderId, "accepted","not yet dispatched"), HttpStatus.OK);
	}

	//creating new order from the cart
	@PostMapping("/placeOrderFromCart")
	public ResponseEntity<Result> createNewOrderFromCart(@RequestBody List<CartDto> cartDtoList) throws OrderException, CustomerException {
		String orderId = service.createNewOrderFromCart(cartDtoList);
		return new ResponseEntity<>(new Result(orderId, "accepted","not yet dispatched"), HttpStatus.OK);
	}

	//the details of the order are fetched according to the userId
	@GetMapping("/getOrders/{userId}")
	public ResponseEntity<List<Orders>> getOrdersByUserId(@PathVariable("userId") String userId) throws OrderException {
		List<Orders> orders = service.findOrdersByUserId(userId);
		return new ResponseEntity<>(orders,HttpStatus.OK);
	}

	//the particular order details are fetched according to the orderId
	@GetMapping("/getOrders/orderId/{orderId}")
	public ResponseEntity<Orders> getOrdersByOrderId(@PathVariable("orderId") String orderId) throws OrderException {
		Orders orders = service.findOrdersByOrderId(orderId);
		return new ResponseEntity<>(orders,HttpStatus.OK);
	}

	//the order gets deleted by providing the orderId
	@DeleteMapping("/deleteOrder/{orderId}")
	public ResponseEntity<String> removeOrder(@PathVariable("orderId") String orderId) throws OrderException {
		String string = service.cancelOrder(orderId);
		return new ResponseEntity<>(string, HttpStatus.OK);
	}

	//deleting a product from order by providing the productId
	@DeleteMapping("/deleteProductFromOrder/{productId}")
	public ResponseEntity<String> removeProduct(@PathVariable("productId") String productId) throws ProductException{
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