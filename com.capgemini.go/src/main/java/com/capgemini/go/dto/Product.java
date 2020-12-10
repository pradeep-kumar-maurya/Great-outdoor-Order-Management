<<<<<<< HEAD
package com.capgemini.go.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(length = 15)
	private String productId;
	@Column(length = 20)
	private String productName;
	@Column(length = 10)
	private String productPrice;
	@Column(length = 2)
	private int quantity;
	@Column(length = 40)
	private String orderId;

	public Product() {
		super();
	}

	public Product(int id, String productId, String productName, String productPrice, int quantity, String orderId) {
		super();
		this.id = id;
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.quantity = quantity;
		this.orderId = orderId;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
=======
package com.capgemini.go.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(length = 15)
	private String productId;
	@Column(length = 20)
	private String productName;
	@Column(length = 10)
	private String productPrice;
	@Column(length = 2)
	private int quantity;
	@Column(length = 40)
	private String orderId;

	public Product() {
		super();
	}

	public Product(int id, String productId, String productName, String productPrice, int quantity, String orderId) {
		super();
		this.id = id;
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.quantity = quantity;
		this.orderId = orderId;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
>>>>>>> branch 'master' of https://github.com/pradeep-kumar-maurya/Great-outdoor-Order-Management.git
}