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
import com.capgemini.go.dao.ProductRepository;
import com.capgemini.go.dto.Cart;
import com.capgemini.go.dto.OrderProductMap;
import com.capgemini.go.dto.Orders;
import com.capgemini.go.dto.Product;
import com.capgemini.go.exceptions.OrderException;
import com.capgemini.go.exceptions.ProductException;

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
	public String createNewOrder(Orders orders) throws OrderException {
		System.out.println("in orders");
		if(validateOrder(orders)) {
			String orderId = UUID.randomUUID().toString();
			orders.setOrderId(orderId);
			orders.setOrderDispatchStatus("accepted");
			Calendar c = Calendar.getInstance();
			orders.setOrderInitiateTime(c.getTime());
			c.add(Calendar.DATE, 2);
			orders.setOrderDispatchTime(c.getTime());
			List<Cart> products = cartRepository.getProductsByUserId(orders.getUserId());
			if(!products.isEmpty()) {
				List<OrderProductMap> orderProductMapList = new ArrayList<>();
				for(Cart cart : products) {
					OrderProductMap map = new OrderProductMap();
					Product product = productRepository.getProductByProductId(cart.getProductId());
					map.setOrderId(orderId);
					map.setProductId(cart.getProductId());
					map.setProductStatus(0);
					map.setGiftStatus(0);
					map.setProduct(product);
					orderProductMapRepository.save(map);
					orderProductMapList.add(map);
					//cartRepository.deleteByUserIdAndProductId(orders.getUserId(),cart.getProductId());
				}
				orders.setProducts(orderProductMapList);
				ordersRepository.save(orders);
				return "order placed successfully";
				//return orderId;
			}
			else {
				throw new OrderException("valid userId needed or zero items in the cart...!!!");
			}
		}
		else {
			throw new OrderException("incorrect UserId or AddressId...!!!");
		}
	}

	/*
	 * Function Name : findOrdersByUserId 
	 * Input Parameters :  userId
	 * Return Type : List<Orders>
	 * Description : to show the list of orders according to user
	 */

	@Override
	public List<Orders> findOrdersByUserId(String userId) throws OrderException{
		List<Orders> orders = new ArrayList<>();

		//Validating userId
		if(userId == null || userId.isEmpty()) {
			throw new OrderException("valid userId needed..!!");
		}
		else {
			orders = ordersRepository.findUserById(userId);
			if(orders.isEmpty()) {
				throw new OrderException("valid userId needed..!!");
			}
			else {
				for(Orders order : orders) {
					List<OrderProductMap> products = orderProductMapRepository.getOrderProductMapByOrderId(order.getOrderId());
					for(OrderProductMap map : products) {
						Product product = productRepository.getProductByProductId(map.getProductId());
						map.setProduct(product);
					}
					order.setProducts(products);
				}
				return orders;
			}
		}
	}

	/* 
	 * Function Name : cancelOrder 
	 * Input Parameters :  orderId
	 * Return Type : String 
	 * Description : to cancel order
	 */

	@Override
	public String cancelOrder(String orderId) throws OrderException {
		if (orderId == null || orderId.isEmpty()) {
			throw new OrderException("Valid orderId needed...!!");
		}
		else {
			Orders order = ordersRepository.getOrderByOrderId(orderId);
			if(order == null) {
				throw new OrderException("valid orderId needed..!!");
			}
			else {
				ordersRepository.deleteByOrderId(orderId);
				orderProductMapRepository.deleteByOrderId(orderId);
				return "order was deleted successfully";
			} 
		}
	}

	/* 
	 * Function Name : removeProduct 
	 * Input Parameters :  productId
	 * Return Type : String 
	 * Description : to cancel product which is not yet dispatched
	 */

	@Override
	public String removeProduct(String productId) throws ProductException {
		if(productId == null || productId.isEmpty()) {
			throw new ProductException("valid productId needed...!!!");
		}
		else {
			OrderProductMap map = orderProductMapRepository.getProductByProductId(productId);
			List<OrderProductMap> mapList = orderProductMapRepository.findAll();
			if(map == null) {
				throw new ProductException("valid productId needed...!!!");
			}
			else {
				if(map.getProductStatus() == 0) {
					if(mapList.size() == 1) {
						orderProductMapRepository.deleteProductByProductId(productId);
						ordersRepository.deleteByOrderId(map.getOrderId());
						return "product was removed successfully, order cancelled..!!";
					}
					else {
						orderProductMapRepository.deleteProductByProductId(productId);
						return "product was removed successfully..!!";
					}
				}
				else {
					throw new ProductException("product has already been dispatched and cannot be cancelled...!!!");
				}
			}
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

	/* 
	 * Function Name : addItemToCart 
	 * Input Parameters :  Cart
	 * Return Type : List<Cart>
	 * Description : to validate order
	 */
	@Override
	public List<Cart> addItemToCart(Cart cart) {
		Product product = productRepository.getProductByProductId(cart.getProductId());
		cart.setProduct(product);
		cartRepository.save(cart);
		List<Cart> cartList = cartRepository.getCartListByUserId(cart.getUserId());
		for(Cart c : cartList) {
			Product p = productRepository.getProductByProductId(c.getProductId());
			c.setProduct(p);
		}
		return cartList;
	}
}