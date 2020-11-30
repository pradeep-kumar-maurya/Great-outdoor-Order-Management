package com.capgemini.go.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "OrderProductMap")
public class OrderProductMap {

	@Id
	@SequenceGenerator(name="orderProductMapSeq", initialValue=30000, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="orderProductMapSeq")
	private long id;
	private String orderId;
	private String productId;
	private int productStatus;
	private int giftStatus;
	@Transient
	private Product product;
	
	public OrderProductMap() {
		super();
	}

	public OrderProductMap(long id, String orderId, String productId, int productStatus,
			int giftStatus, Product product) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.productId = productId;
		this.productStatus = productStatus;
		this.giftStatus = giftStatus;
		this.product = product;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(int productStatus) {
		this.productStatus = productStatus;
	}

	public int getGiftStatus() {
		return giftStatus;
	}

	public void setGiftStatus(int giftStatus) {
		this.giftStatus = giftStatus;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "OrderProductMap [id=" + id + ", orderId=" + orderId + ", productId=" + productId + ", productStatus="
				+ productStatus + ", giftStatus=" + giftStatus + ", product=" + product + "]";
	}
}