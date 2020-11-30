package com.capgemini.go.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.go.dto.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{

	@Query("select cart from Cart cart where cart.userId=?1")
	public List<Cart> getProductsByUserId(String userId);

	@Transactional
	@Modifying
	@Query("delete from Cart cart where cart.userId=?1 and cart.productId=?2")
	public void deleteByUserIdAndProductId(String userId, String productId);

	@Query("select cart from Cart cart where cart.userId=?1")
	public List<Cart> getCartListByUserId(String userId);

}
