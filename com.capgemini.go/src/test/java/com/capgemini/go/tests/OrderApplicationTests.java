package com.capgemini.go.tests;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.go.dto.CartDto;
import com.capgemini.go.dto.Orders;
import com.capgemini.go.exceptions.OrderException;
import com.capgemini.go.exceptions.ProductException;
import com.capgemini.go.service.IOrderService;


@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class OrderApplicationTests {

	@Autowired
	private IOrderService service;
	
	/*
	 * Test : placing Order
	 * Input parameters : CartDto
	 * Details : Valid 
	 */
	
	@Disabled
	@Test
	@Order(1)
	void createNewOrder_1() throws OrderException {
		CartDto cartDto = new CartDto();
		cartDto.setId(1);
		cartDto.setAddressId("1024-TS");
		cartDto.setProductId("546-edf");
		cartDto.setUserId("prad1024");
		cartDto.setQuantity(1);
		cartDto.setProductName("Apple Imac");
		cartDto.setProductPrice("200000");
		String string1 = service.createNewOrder(cartDto);
		String string2 = "order placed successfully";
		Assertions.assertEquals(string1, string2);
	}

	
	/*
	 * Test : placing Order
	 * Input parameters : CartDto
	 * Details : Invalid(userId = "") 
	 */
	
	//@Disabled
	@Test
	@Order(2)
	void createNewOrder_2() throws OrderException {
		CartDto cartDto = new CartDto();
		cartDto.setId(1);
		cartDto.setAddressId("1024-TS");
		cartDto.setProductId("546-edf");
		cartDto.setUserId("");
		cartDto.setQuantity(1);
		cartDto.setProductName("Apple Imac");
		cartDto.setProductPrice("200000");
		String string1 = service.createNewOrder(cartDto);
		String string2 = "order placed successfully";
		Assertions.assertEquals(string1, string2);
	}

	
	/*
	 * Test : placing Order
	 * Input parameters : cartDto
	 * Details : Invalid(userId = null) 
	 */
	
	//@Disabled
	@Test
	@Order(3)
	void createNewOrder_3() throws OrderException {
		CartDto cartDto = new CartDto();
		cartDto.setId(1);
		cartDto.setAddressId("1024-TS");
		cartDto.setProductId("546-edf");
		cartDto.setUserId(null);
		cartDto.setQuantity(1);
		cartDto.setProductName("Apple Imac");
		cartDto.setProductPrice("200000");
		String string1 = service.createNewOrder(cartDto);
		String string2 = "order placed successfully";
		Assertions.assertEquals(string1, string2);
	}
	
	
	/*
	 * Test : fetch Order
	 * Input parameters : userId
	 * Details : Valid
	 */
	
	//@Disabled
	@Test
	@Order(4)
	void findOrdersByUserId_1() throws OrderException {
		List<Orders> orders = service.findOrdersByUserId("prad1024");
		Assertions.assertEquals(1, orders.size());
	}
	
	
	/*
	 * Test : fetch Order
	 * Input parameters : userId
	 * Details : Invalid(userId = "999") 
	 */
	
	//@Disabled
	@Test
	@Order(5)
	void findOrdersByUserId_2() throws OrderException {
		List<Orders> orders = service.findOrdersByUserId("prd102");
		Assertions.assertEquals(1, orders.size());
	}
	
	/*
	 * Test : fetch Order
	 * Input parameters : userId
	 * Details : Invalid(userId = "") 
	 */
	
	//@Disabled
	@Test
	@Order(6)
	void findOrdersByUserId_3() throws OrderException {
		List<Orders> orders = service.findOrdersByUserId("");
		Assertions.assertEquals(1, orders.size());
	}

	
	/*
	 * Test : fetch Order
	 * Input parameters : userId
	 * Details : Invalid(userId = null) 
	 */
	
	//@Disabled
	@Test
	@Order(7)
	void findOrdersByUserId_4() throws OrderException {
		List<Orders> orders = service.findOrdersByUserId(null);
		Assertions.assertEquals(1, orders.size());
	}
	
	
	/*
	 * Test : cancel Order
	 * Input parameters : orderId
	 * Details : Valid
	 */
	
	//@Disabled
	@Test
	@Order(8)
	void cancelOrder_1() throws OrderException {
		String string1 = service.cancelOrder("a9c85db5-b055-41f3-a2f7-4b02e5053791");
		String string2 = "order was deleted successfully";
		Assertions.assertEquals(string1, string2);
	}
	
	
	/*
	 * Test : cancel Order
	 * Input parameters : orderId
	 * Details : Invalid(orderId = "a9c85db5-b05")
	 */
	
	//@Disabled
	@Test
	@Order(9)
	void cancelOrder_2() throws OrderException {
		String string1 = service.cancelOrder("a9c85db5-b05");
		String string2 = "order was deleted successfully";
		Assertions.assertEquals(string1, string2);
	}
	
	
	/*
	 * Test : cancel Order
	 * Input parameters : orderId
	 * Details : Invalid(orderId = "")
	 */
	
	//@Disabled
	@Test
	@Order(10)
	void cancelOrder_3() throws OrderException {
		String string1 = service.cancelOrder("");
		String string2 = "order was deleted successfully";
		Assertions.assertEquals(string1, string2);
	}
	
	
	/*
	 * Test : cancel Order
	 * Input parameters : orderId
	 * Details : Invalid(orderId = null)
	 */
	
	//@Disabled
	@Test
	@Order(11)
	void cancelOrder_4() throws OrderException {
		String string1 = service.cancelOrder(null);
		String string2 = "order was deleted successfully";
		Assertions.assertEquals(string1, string2);
	}
	
	
	/*
	 * Test : remove Product
	 * Input parameters : productId
	 * Details : Valid
	 */
	
	@Disabled
	@Test
	@Order(12)
	void removeProduct_1() throws ProductException {
		String string1 = service.removeProduct("555-bbb");
		String string2 = "product was removed successfully..!!";
		Assertions.assertEquals(string1, string2);
	}
	
	
	/*
	 * Test : remove Product
	 * Input parameters : productId
	 * Details : Invalid(productId = "")
	 */
	
	//@Disabled
	@Test
	@Order(13)
	void removeProduct_2() throws ProductException {
		String string1 = service.removeProduct("");
		String string2 = "product was removed successfully..!!";
		Assertions.assertEquals(string1, string2);
	}
	
	
	/*
	 * Test : remove Product
	 * Input parameters : productId
	 * Details : Invalid(productId = null)
	 */
	
	//@Disabled
	@Test
	@Order(14)
	void removeProduct_3() throws ProductException {
		String string1 = service.removeProduct(null);
		String string2 = "product was removed successfully..!!";
		Assertions.assertEquals(string1, string2);
	}
	
	
	/*
	 * Test : remove Product
	 * Input parameters : productId
	 * Details : Invalid(productId = "1234")
	 */
	
	//@Disabled
	@Test
	@Order(15)
	void removeProduct_4() throws ProductException {
		String string1 = service.removeProduct("1234");
		String string2 = "product was removed successfully..!!";
		Assertions.assertEquals(string1, string2);
	}
}