package com.capgemini.go.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "Orders")
public class Orders {

	@Id
	@SequenceGenerator(name="ordersSeq", initialValue=40000, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ordersSeq")
	private long id;
	@Column(length = 40)
    private String orderId;
	@Column(length = 15)
    private String userId;
	@Column(length = 15)
    private String addressId;
	@Column(length = 20)
    private String orderDispatchStatus;
	@Column(length = 20)
    @Temporal(TemporalType.DATE)
    private Date orderInitiateTime;
	@Column(length = 20)
    @Temporal(TemporalType.DATE)
    private Date orderDispatchTime;
    @Transient
    private List<OrderProductMap> products;
    
	public Orders() {
		super();
	}

	public Orders(long id, String orderId, String userId, String addressId, String orderDispatchStatus,
			Date orderInitiateTime, Date orderDispatchTime, List<OrderProductMap> products) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.userId = userId;
		this.addressId = addressId;
		this.orderDispatchStatus = orderDispatchStatus;
		this.orderInitiateTime = orderInitiateTime;
		this.orderDispatchTime = orderDispatchTime;
		this.products = products;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getOrderDispatchStatus() {
		return orderDispatchStatus;
	}

	public void setOrderDispatchStatus(String orderDispatchStatus) {
		this.orderDispatchStatus = orderDispatchStatus;
	}

	public Date getOrderInitiateTime() {
		return orderInitiateTime;
	}

	public void setOrderInitiateTime(Date orderInitiateTime) {
		this.orderInitiateTime = orderInitiateTime;
	}

	public Date getOrderDispatchTime() {
		return orderDispatchTime;
	}

	public void setOrderDispatchTime(Date orderDispatchTime) {
		this.orderDispatchTime = orderDispatchTime;
	}

	public List<OrderProductMap> getProducts() {
		return products;
	}

	public void setProducts(List<OrderProductMap> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", orderId=" + orderId + ", userId=" + userId + ", addressId=" + addressId
				+ ", orderDispatchStatus=" + orderDispatchStatus + ", orderInitiateTime=" + orderInitiateTime
				+ ", orderDispatchTime=" + orderDispatchTime + ", products=" + products + "]";
	}
}
