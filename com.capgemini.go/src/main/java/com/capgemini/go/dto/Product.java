package com.capgemini.go.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Product")
public class Product {

	@Id
	private String productId;
	private String productName;
	private String productPrice;
	private int quantity;
	private String orderId;

	public Product() {
		super();
	}

	public Product(String productId, String productName, String productPrice, int quantity, String orderId) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.quantity = quantity;
		this.orderId = orderId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", quantity=" + quantity + ", orderId=" + orderId + "]";
	}	
}