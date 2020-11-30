package com.capgemini.go.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.go.dto.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer>{

	@Query("select orders from Orders orders where orders.userId=?1")
	public List<Orders> findUserById(String userId);

	@Transactional
	@Modifying
	@Query("delete from Orders orders where orders.orderId=?1")
	public void deleteByOrderId(String orderId);

	@Query("select orders from Orders orders where orders.orderId=?1")
	public Orders getOrderByOrderId(String orderId);

}
