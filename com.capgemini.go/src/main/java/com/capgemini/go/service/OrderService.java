package com.capgemini.go.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.go.dao.CartRepository;
import com.capgemini.go.dao.CustomerRepository;
import com.capgemini.go.dao.OrderProductMapRepository;
import com.capgemini.go.dao.OrdersRepository;
import com.capgemini.go.dto.Cart;
import com.capgemini.go.dto.OrderProductMap;
import com.capgemini.go.dto.Orders;

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
	

	/*
	 * Function Name : createNewOrder 
	 * Input Parameters :  Orders
	 * Return Type : String
	 * Description : to create order
	 */
	
	@Override
	public String createNewOrder(Orders orders) {
		if(validateOrder(orders)) {
			String orderId = UUID.randomUUID().toString();
			orders.setOrderId(orderId);
			orders.setOrderDispatchStatus("accepted");
			Calendar c = Calendar.getInstance();
			orders.setOrderInitiateTime(c.getTime());
			c.add(Calendar.DATE, 2);
			orders.setOrderDispatchTime(c.getTime());
			List<Cart> products = cartRepository.getProductsByUserId(orders.getUserId());
			List<OrderProductMap> orderProductList = new ArrayList<>();
			for(Cart cart : products) {
				OrderProductMap map = new OrderProductMap();
				map.setOrderId(orderId);
				map.setProductId(cart.getProductId());
				map.setProductStatus(1);
				map.setGiftStatus(1);
				orderProductMapRepository.save(map);
				orderProductList.add(map);
				//cartRepository.deleteByUserIdAndProductId(orders.getUserId(),cart.getProductId());
			}
			orders.setProducts(orderProductList);
			ordersRepository.save(orders);
			return "order placed successfully";
		}
		else {
			return "sorry, order was not placed";
		}

	}

	 /* 
	 * Function Name : validateOrder 
   	 * Input Parameters :  Orders
   	 * Return Type : Boolean 
   	 * Description : to validate order
   	 */
	private boolean validateOrder(Orders orders) {
		if (orders.getAddressId() == null || orders.getAddressId().isEmpty()) {
			return false;
		}
		else if (orders.getUserId() == null || orders.getUserId().isEmpty()) {
			return false;
		}
		else
			return true;
	}
}