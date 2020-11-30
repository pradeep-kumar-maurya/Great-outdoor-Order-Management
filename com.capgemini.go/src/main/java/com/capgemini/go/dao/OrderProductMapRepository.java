package com.capgemini.go.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.go.dto.OrderProductMap;

@Repository
public interface OrderProductMapRepository extends JpaRepository<OrderProductMap, Integer>{

	@Query("select orderProductMap from OrderProductMap orderProductMap where orderProductMap.orderId=?1")
	public List<OrderProductMap> getOrderProductMapByOrderId(String orderId);

	@Transactional
	@Modifying
	@Query("delete from OrderProductMap orderProductMap where orderProductMap.orderId=?1")
	public void deleteByOrderId(String orderId);

	@Query("select orderProductMap from OrderProductMap orderProductMap where orderProductMap.productId=?1")
	public OrderProductMap getProductByProductId(String productId);

	@Transactional
	@Modifying
	@Query("delete from OrderProductMap orderProductMap where orderProductMap.productId=?1")
	public void deleteProductByProductId(String productId);

}
