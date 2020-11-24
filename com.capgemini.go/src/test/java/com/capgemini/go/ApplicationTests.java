package com.capgemini.go;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.go.dto.Orders;
import com.capgemini.go.service.IOrderService;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private IOrderService service;
	
	@Test
	public void addOrder() {
	
		Orders orders = new Orders();
		orders.setId(40001);
		orders.setUserId("999-a1");
		orders.setAddressId("1211a");
		String string1 = service.createNewOrder(orders);
		String string2 = "order placed successfully";
		Assertions.assertEquals(string1, string2);
	}

}
