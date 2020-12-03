package com.capgemini.go.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.go.dto.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	@Query("select customer from Customer customer where userId=?1")
	Customer getCustomerByUserId(String userId);

	@Query("select customer from Customer customer where username=?1 and password=?2")
	Customer getCustomerDetails(String username, String password);

}
