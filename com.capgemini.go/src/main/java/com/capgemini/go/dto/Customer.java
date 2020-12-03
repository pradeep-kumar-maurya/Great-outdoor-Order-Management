package com.capgemini.go.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(length = 10)
	private String userId;
	@Column(length = 20)
	private String username;
	@Column(length = 15)
	private String password;
	@Column(length = 10)
	private String addressId;
	
	public Customer() {
		super();
	}

	public Customer(long id, String userId, String username, String password, String addressId) {
		super();
		this.id = id;
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.addressId = addressId;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", userId=" + userId + ", username=" + username + ", password=" + password
				+ ", addressId=" + addressId + "]";
	}	
}