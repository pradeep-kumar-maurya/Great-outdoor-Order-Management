package com.capgemini.go.tests;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.capgemini.go.controller.OrderController;
import com.capgemini.go.dto.Orders;
import com.capgemini.go.service.IOrderService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(OrderController.class)
class OrderApplicationHttpStatusCodeTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IOrderService service;

	@Test
	void addOrder() throws Exception {
		Orders orders = new Orders();
		when(service.createNewOrder(orders)).thenReturn("");
		mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:4545/order/addOrder",orders).contentType("application/json").content(new ObjectMapper().writeValueAsString(orders))).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	void cancelOrder() throws Exception {
		String string = "order was deleted successfully";
		when(service.cancelOrder("")).thenReturn(string);
		mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:4545/order/deleteOrder/444")).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	void cancelProduct() throws Exception {
		String string = "product was deleted successfully";
		when(service.removeProduct("")).thenReturn(string);
		mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:4545/order/deleteProductFromOrder/555")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void getOrders() throws Exception {
		List<Orders> ordersList = new ArrayList<>();
		when(service.findOrdersByUserId("")).thenReturn(ordersList);
		mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:4545/order/getOrders/999-a1")).andDo(print()).andExpect(status().isOk());
	}
}
