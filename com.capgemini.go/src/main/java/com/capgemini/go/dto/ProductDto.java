package com.capgemini.go.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ProductDto")
public class ProductDto {
	
	@Id
	private String productId;
	private String price;
	private String productName;
	private String productCategory;
	
	public ProductDto() {
		super();
	}

	public ProductDto(String productId, String price, String productName, String productCategory) {
		super();
		this.productId = productId;
		this.price = price;
		this.productName = productName;
		this.productCategory = productCategory;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	@Override
	public String toString() {
		return "ProductDto [productId=" + productId + ", price=" + price + ", productName=" + productName
				+ ", productCategory=" + productCategory + "]";
	}
}
