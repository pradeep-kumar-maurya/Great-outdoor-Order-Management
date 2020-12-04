package com.capgemini.go.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.go.dao.CartDtoRepository;
import com.capgemini.go.dao.CartRepository;
import com.capgemini.go.dao.CustomerRepository;
import com.capgemini.go.dao.OrderProductMapRepository;
import com.capgemini.go.dao.OrdersRepository;
import com.capgemini.go.dao.ProductDtoRepository;
import com.capgemini.go.dao.ProductRepository;
import com.capgemini.go.dto.Cart;
import com.capgemini.go.dto.CartDto;
import com.capgemini.go.dto.Customer;
import com.capgemini.go.dto.OrderProductMap;
import com.capgemini.go.dto.Orders;
import com.capgemini.go.dto.Product;
import com.capgemini.go.dto.ProductDto;
import com.capgemini.go.exceptions.CustomerException;
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
	@Autowired
	private ProductDtoRepository productDtoRepository;
	@Autowired
	private CartDtoRepository cartDtoRepository;

	/*
	 * Function Name : getCustomer 
	 * Input Parameters :  username and password of the customer
	 * Return Type : Customer
	 * Description : to get customer information
	 */
	@Override
	public Customer getCustomer(String username, String password) throws CustomerException {

		if(username.isEmpty() || username == null)
			throw new CustomerException("invalid username");

		else if(password.isEmpty() || password == null)
			throw new CustomerException("invalid password");

		else {
			Customer customerData = customerRepository.getCustomerDetails(username, password);
			if(customerData == null)
				throw new CustomerException("invalid Customer..!!");
			else
				return customerData;
		}
	}


	/*
	 * Function Name : createNewOrder 
	 * Input Parameters :  Orders
	 * Return Type : String
	 * Description : to create order
	 */

	@Override
	public String createNewOrder(CartDto cartDto) throws OrderException {
		if(validateCart(cartDto)) {

			Cart cartItem = new Cart();
			//adding product to cart entity
			cartItem.setProductId(cartDto.getProductId());
			cartItem.setQuantity(cartDto.getQuantity());
			cartItem.setUserId(cartDto.getUserId());
			cartRepository.save(cartItem);

			Orders orders = new Orders();
			//placing order
			final String status = "not yet dispatched";
			String orderId = UUID.randomUUID().toString();
			orders.setUserId(cartDto.getUserId());
			orders.setAddressId(cartDto.getAddressId());
			orders.setOrderId(orderId);
			orders.setOrderDispatchStatus(status);
			Calendar c = Calendar.getInstance();
			orders.setOrderInitiateTime(c.getTime());
			c.add(Calendar.DATE, 2);
			orders.setOrderDispatchTime(c.getTime());
			List<Cart> products = cartRepository.getProductsByUserId(orders.getUserId());
			if(!products.isEmpty()) {
				List<OrderProductMap> orderProductMapList = new ArrayList<>();
				Product product = new Product();
				for(Cart cart : products) {
					OrderProductMap map = new OrderProductMap();
					ProductDto productDto = productDtoRepository.getProductByProductId(cart.getProductId());

					if(productDto != null) {
						//adding product details to Product table
						product.setProductId(productDto.getProductId());
						product.setProductName(productDto.getProductName());
						product.setQuantity(cart.getQuantity());
						product.setProductPrice(productDto.getPrice());
						product.setOrderId(orderId);
						productRepository.save(product);

						//setting data in the OrderProductMap table
						map.setOrderId(orderId);
						map.setProductId(cart.getProductId());
						map.setProductStatus(0);
						map.setGiftStatus(0);
						map.setProduct(product);
						orderProductMapRepository.save(map);
						orderProductMapList.add(map);
						cartRepository.deleteByUserIdAndProductId(orders.getUserId(),cart.getProductId());
					}
					else {
						throw new OrderException("no such product found with product Id : "+cart.getProductId());
					}
				}
				orders.setProducts(orderProductMapList);
				ordersRepository.save(orders);
				return orderId;
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
	 * Function Name : createNewOrderFromCart
	 * Input Parameters :  List<CartDto>
	 * Return Type : String
	 * Description : to place order from Cart
	 */

	@Override
	public String createNewOrderFromCart(List<CartDto> cartDtoList) throws OrderException, CustomerException {

		if(!cartDtoList.isEmpty()) {

			CartDto cartDto = cartDtoList.get(0);

			if(validateCart(cartDto)) {

				for(CartDto cartDtoItem : cartDtoList) {
					Cart cart = new Cart();
					cart.setUserId(cartDtoItem.getUserId());
					cart.setProductId(cartDtoItem.getProductId());
					cart.setQuantity(cartDtoItem.getQuantity());
					cartRepository.save(cart);
				}

				Orders orders = new Orders();

				String orderId = UUID.randomUUID().toString();

				Customer customer = customerRepository.getCustomerByUserId(cartDto.getUserId());

				if(customer != null) {

					final String status = "not yet dispatched";

					orders.setUserId(cartDto.getUserId());
					orders.setAddressId(cartDto.getAddressId());
					orders.setOrderId(orderId);
					orders.setOrderDispatchStatus(status);
					Calendar c = Calendar.getInstance();
					orders.setOrderInitiateTime(c.getTime());
					c.add(Calendar.DATE, 2);
					orders.setOrderDispatchTime(c.getTime());
					List<Cart> products = cartRepository.getProductsByUserId(orders.getUserId());
					if(!products.isEmpty()) {
						List<OrderProductMap> orderProductMapList = new ArrayList<>();

						for(Cart cartItem : products) {
							OrderProductMap map = new OrderProductMap();
							Product product = new Product();
							ProductDto productDto = productDtoRepository.getProductByProductId(cartItem.getProductId());

							if(productDto != null) {
								//adding product details to Product table
								product.setProductId(productDto.getProductId());
								product.setProductName(productDto.getProductName());
								product.setQuantity(cartItem.getQuantity());
								product.setProductPrice(productDto.getPrice());
								product.setOrderId(orderId);
								productRepository.save(product);

								//setting data in the OrderProductMap table
								map.setOrderId(orderId);
								map.setProductId(cartItem.getProductId());
								map.setProductStatus(0);
								map.setGiftStatus(0);
								map.setProduct(product);
								orderProductMapRepository.save(map);
								orderProductMapList.add(map);
								cartRepository.deleteByUserIdAndProductId(cartItem.getUserId(),cartItem.getProductId());
								//					cartDtoRepository.deleteByUserIdAndProductId(cartItem.getUserId(),cartItem.getProductId());
							}
							else {
								throw new OrderException("no such product found with product Id : "+cartItem.getProductId());
							}
						}
						orders.setProducts(orderProductMapList);
						ordersRepository.save(orders);
						return orderId;
					}
					else {
						throw new OrderException("valid userId needed or zero items in the cart...!!!");
					}
				}
				else {
					throw new CustomerException("Customer doen not exist..!!");
				}
			}
			else {
				throw new OrderException("invalid user id or address id");
			}
		}
		else {
			throw new OrderException("No items in the cart..!!");
		}
	}

	@Override
	public List<CartDto> getCartItems() {
		return cartDtoRepository.findAll();
	}

	/*
	 * Function Name : findOrdersByUserId 
	 * Input Parameters :  userId
	 * Return Type : List<Orders>
	 * Description : to show the list of orders
	 */

	@Override
	public List<Orders> findOrdersByUserId(String userId) throws OrderException{

		List<Orders> orders;
		Customer customer;

		//Validating userId
		if(userId == null || userId.isEmpty()) {
			throw new OrderException("valid userId needed..!!");
		}
		else {
			orders = ordersRepository.findUserById(userId);
			customer = customerRepository.getCustomerByUserId(userId);
			if(orders.isEmpty() && customer == null) {
				throw new OrderException("valid userId needed..!!");
			}

			else if(orders.isEmpty() && customer != null) {
				return null;
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
	 * Function Name : findOrdersByOrderId 
	 * Input Parameters :  orderId
	 * Return Type : Orders
	 * Description : to show particular order details
	 */

	@Override
	public Orders findOrdersByOrderId(String orderId) throws OrderException {

		Orders order;

		String error = "valid orderId needed..!!";
		//Validating orderId
		if(orderId == null || orderId.isEmpty()) {
			throw new OrderException(error);
		}
		else {
			order = ordersRepository.getOrderByOrderId(orderId);
			if(order == null) {
				throw new OrderException(orderId + " : invalid Order Id \n"+ error);
			}
			else {
				List<OrderProductMap> products = orderProductMapRepository.getOrderProductMapByOrderId(order.getOrderId());
				for(OrderProductMap map : products) {
					Product product = productRepository.getProductByProductId(map.getProductId());
					map.setProduct(product);
				}
				order.setProducts(products);
			}
			return order;
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
			else if(order.getOrderDispatchStatus().equals("not yet dispatched")) {
				ordersRepository.deleteByOrderId(orderId);
				orderProductMapRepository.deleteByOrderId(orderId);
				productRepository.deleteByOrderId(orderId);
				return "order was deleted successfully";
			}
			else {
				throw new OrderException("order has already been dispatched and cannot be cancelled...!!!");
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

			if(map == null) {
				throw new ProductException("valid productId needed...!!!");
			}
			else {
				List<OrderProductMap> list = orderProductMapRepository.getOrderProductMapByOrderId(map.getOrderId());
				if(map.getProductStatus() == 0) {
					if(list.size() == 1){
						orderProductMapRepository.deleteProductByProductId(productId);
						ordersRepository.deleteByOrderId(map.getOrderId());
						productRepository.deleteByOrderId(map.getOrderId());
						return "product was removed successfully, order cancelled..!!";
					}
					else {
						orderProductMapRepository.deleteProductByProductId(productId);
						productRepository.deleteProductByProductId(productId);
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
	 * Function Name : validateCart 
	 * Input Parameters :  CartDto
	 * Return Type : Boolean 
	 * Description : to validate Cart Items
	 */
	private boolean validateCart(CartDto cartDto) {
		if(cartDto == null) {
			return false;
		}
		if (cartDto.getAddressId() == null || cartDto.getAddressId().isEmpty()) {
			return false;
		}
		else if (cartDto.getUserId() == null || cartDto.getUserId().isEmpty()) {
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
		ProductDto productDto = productDtoRepository.getProductByProductId(cart.getProductId());
		cart.setProductDto(productDto);
		cartRepository.save(cart);
		List<Cart> cartList = cartRepository.getCartListByUserId(cart.getUserId());
		for(Cart c : cartList) {
			ProductDto pDto = productDtoRepository.getProductByProductId(c.getProductId());
			c.setProductDto(pDto);
		}
		return cartList;
	}
}