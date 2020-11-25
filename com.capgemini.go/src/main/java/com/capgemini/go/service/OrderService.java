package com.capgemini.go.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.go.dao.CartRepository;
import com.capgemini.go.dao.CustomerRepository;
import com.capgemini.go.dao.OrderProductMapRepository;
import com.capgemini.go.dao.OrdersRepository;
import com.capgemini.go.dao.ProductRepository;
import com.capgemini.go.dto.Cart;
import com.capgemini.go.dto.OrderProductMap;
import com.capgemini.go.dto.Orders;
import com.capgemini.go.dto.Product;

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
	@Autowired
	private ProductRepository productRepository;

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
			List<OrderProductMap> orderProductMapList = new ArrayList<>();
			for(Cart cart : products) {
				OrderProductMap map = new OrderProductMap();
				Product product = productRepository.getProductByProductId(cart.getProductId());
				map.setOrderId(orderId);
				map.setProductId(cart.getProductId());
				map.setProductStatus(1);
				map.setGiftStatus(1);
				map.setProducts(product);
				orderProductMapRepository.save(map);
				orderProductMapList.add(map);
				//cartRepository.deleteByUserIdAndProductId(orders.getUserId(),cart.getProductId());
			}
			orders.setProducts(orderProductMapList);
			System.out.println(orders);
			ordersRepository.save(orders);
			//return "order placed successfully";
			return orderId;
		}
		else {
			return "sorry, order was not placed";
		}
	}


	/*
	 * Function Name : findOrdersByUserId 
	 * Input Parameters :  userId
	 * Return Type : List<Orders>
	 * Description : to show the list of orders according to user
	 */

	@Override
	public List<Orders> findOrdersByUserId(String userId){
		System.out.println(userId);
		List<Orders> orders = new ArrayList<>();
		/*
		 * Validating userId
		 */
		if(userId == null || userId.isEmpty()) {
			return Collections.emptyList();
		}
		else {
			orders = ordersRepository.findUserById(userId);
			for(Orders order : orders) {
				List<OrderProductMap> products = orderProductMapRepository.getOrderProductMapByOrderId(order.getOrderId());
				for(OrderProductMap map : products) {
					Product product = productRepository.getProductByProductId(map.getProductId());
					map.setProducts(product);
				}
				order.setProducts(products);
			}
			return orders;
		}
	}

	/* 
	 * Function Name : cancelOrder 
	 * Input Parameters :  orderId
	 * Return Type : String 
	 * Description : to delete order
	 */

	@Override
	public String cancelOrder(String orderId) {
		if (orderId == null || orderId.isEmpty()) {
			return "sorry, order was not cancelled";
		}
		else {
			ordersRepository.deleteByOrderId(orderId);
			orderProductMapRepository.deleteByOrderId(orderId);
			return "order was deleted successfully";
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