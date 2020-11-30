package com.capgemini.go.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Cart")
public class Cart {
	
	@Id
	@SequenceGenerator(name="cartSeq", initialValue=20000, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cartSeq")
	private long id;
	private String userId;
	private String productId;
	private int quantity;
	@Transient
	private ProductDto productDto;
	
	public Cart() {
		super();
	}

	public Cart(long id, String userId, String productId, int quantity, ProductDto productDto) {
		super();
		this.id = id;
		this.userId = userId;
		this.productId = productId;
		this.quantity = quantity;
		this.productDto = productDto;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ProductDto getProductDto() {
		return productDto;
	}

	public void setProductDto(ProductDto productDto) {
		this.productDto = productDto;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", userId=" + userId + ", productId=" + productId + ", quantity=" + quantity
				+ ", productDto=" + productDto + "]";
	}
}