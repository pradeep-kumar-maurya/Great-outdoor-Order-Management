package com.capgemini.go.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.go.dao.CartRepository;
import com.capgemini.go.dao.CustomerRepository;
import com.capgemini.go.dao.OrderProductMapRepository;
import com.capgemini.go.dao.OrdersRepository;

@Service
@Transactional
public class OrderService implements IOrderService{
	
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private OrderProductMapRepository orderProductMapRepository;
	@Autowired
	private OrdersRepository ordersRepository;

}
