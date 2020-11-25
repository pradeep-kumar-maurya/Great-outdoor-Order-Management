package com.capgemini.go.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.go.dto.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>{

	@Query("select product from Product product where product.productId=?1")
	public Product getProductByProductId(String productId);

}
