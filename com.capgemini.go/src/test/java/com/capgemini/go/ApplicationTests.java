package com.capgemini.go;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.go.dto.Orders;
import com.capgemini.go.service.IOrderService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class ApplicationTests {

	@Autowired
	private IOrderService service;

	@Disabled
	@Test
	@Order(1)
	void addOrder_1() {
		Orders orders = new Orders();
		orders.setId(40001);
		orders.setUserId("999-a1");
		orders.setAddressId("1211a");
		String string1 = service.createNewOrder(orders);
		String string2 = "order placed successfully";
		Assertions.assertEquals(string1, string2);
	}

	@Disabled
	@Test
	@Order(2)
	void addOrder_2() {
		Orders orders = new Orders();
		orders.setId(40001);
		orders.setUserId("");
		orders.setAddressId("1211a");
		String string1 = service.createNewOrder(orders);
		String string2 = "order placed successfully";
		Assertions.assertEquals(string1, string2);
	}

	@Disabled
	@Test
	@Order(3)
	void addOrder_3() {
		Orders orders = new Orders();
		orders.setId(40001);
		orders.setUserId(null);
		orders.setAddressId("1211a");
		String string1 = service.createNewOrder(orders);
		String string2 = "order placed successfully";
		Assertions.assertEquals(string1, string2);
	}
	
	@Disabled
	@Test
	@Order(4)
	void findOrdersByUserId_1() {
		List<Orders> orders = service.findOrdersByUserId("999-a1");
		Assertions.assertEquals(2, orders.size());
	}
	
	@Disabled
	@Test
	@Order(5)
	void findOrdersByUserId_2() {
		List<Orders> orders = service.findOrdersByUserId("");
		Assertions.assertEquals(1, orders.size());
	}

	@Disabled
	@Test
	@Order(6)
	void findOrdersByUserId_3() {
		List<Orders> orders = service.findOrdersByUserId(null);
		Assertions.assertEquals(1, orders.size());
	}
	
	@Disabled
	@Test
	@Order(7)
	void cancelOrder_1() {
		String string1 = service.cancelOrder("a9c85db5-b055-41f3-a2f7-4b02e5053791");
		String string2 = "order was deleted successfully";
		Assertions.assertEquals(string1, string2);
	}
	
	@Disabled
	@Test
	@Order(8)
	void cancelOrder_2() {
		String string1 = service.cancelOrder("");
		String string2 = "order was deleted successfully";
		Assertions.assertEquals(string1, string2);
	}
	
	@Disabled
	@Test
	@Order(9)
	void cancelOrder_3() {
		String string1 = service.cancelOrder(null);
		String string2 = "order was deleted successfully";
		Assertions.assertEquals(string1, string2);
	}
}