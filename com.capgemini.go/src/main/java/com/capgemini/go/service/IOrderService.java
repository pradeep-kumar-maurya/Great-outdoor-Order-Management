package com.capgemini.go.service;

import java.util.List;

import com.capgemini.go.dto.Cart;
import com.capgemini.go.dto.Orders;
import com.capgemini.go.exceptions.OrderException;
import com.capgemini.go.exceptions.ProductException;

public interface IOrderService {

	String createNewOrder(Orders orders) throws OrderException;

	List<Orders> findOrdersByUserId(String userId) throws OrderException;

	String cancelOrder(String orderId) throws OrderException;

	List<Cart> addItemToCart(Cart cart);

	String removeProduct(String productId) throws ProductException;

	Orders findOrdersByOrderId(String orderId) throws OrderException;

}