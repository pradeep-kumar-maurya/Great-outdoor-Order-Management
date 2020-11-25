package com.capgemini.go.service;

import java.util.List;

import com.capgemini.go.dto.Orders;

public interface IOrderService {

	String createNewOrder(Orders orders);

	List<Orders> findOrdersByUserId(String userId);

	String cancelOrder(String orderId);

}
