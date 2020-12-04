package com.capgemini.go.service;

import java.util.List;

import com.capgemini.go.dto.Cart;
import com.capgemini.go.dto.CartDto;
import com.capgemini.go.dto.Customer;
import com.capgemini.go.dto.Orders;
import com.capgemini.go.exceptions.CustomerException;
import com.capgemini.go.exceptions.OrderException;
import com.capgemini.go.exceptions.ProductException;

public interface IOrderService {

	String createNewOrder(CartDto cartDto) throws OrderException;

	List<Orders> findOrdersByUserId(String userId) throws OrderException;

	String cancelOrder(String orderId) throws OrderException;

	List<Cart> addItemToCart(Cart cart);

	String removeProduct(String productId) throws ProductException;

	Orders findOrdersByOrderId(String orderId) throws OrderException;

	String createNewOrderFromCart(List<CartDto> cartDtoList) throws OrderException, CustomerException;

	Customer getCustomer(String username, String password) throws CustomerException;

	List<CartDto> getCartItems();

}