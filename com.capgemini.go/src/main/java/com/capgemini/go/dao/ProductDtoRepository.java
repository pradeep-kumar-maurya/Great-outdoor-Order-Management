package com.capgemini.go.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.go.dto.ProductDto;

@Repository
public interface ProductDtoRepository extends JpaRepository<ProductDto, String>{

	@Query("select productDto from ProductDto productDto where productId=?1")
	ProductDto getProductByProductId(String productId);

}
