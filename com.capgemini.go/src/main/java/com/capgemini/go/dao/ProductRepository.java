package com.capgemini.go.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.go.dto.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	@Query("select product from Product product where product.productId=?1")
	public Product getProductByProductId(String productId);

	@Transactional
	@Modifying
	@Query("delete from Product product where orderId=?1")
	public void deleteByOrderId(String orderId);

	@Transactional
	@Modifying
	@Query("delete from Product product where productId=?1")
	public void deleteProductByProductId(String productId);

}
