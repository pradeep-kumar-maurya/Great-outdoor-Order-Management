package com.capgemini.go.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.go.dto.CartDto;

@Repository
public interface CartDtoRepository extends JpaRepository<CartDto, Integer>{

	@Transactional
	@Modifying
	@Query("delete from CartDto cartDto where cartDto.userId=?1 and cartDto.productId=?2")
	public void deleteByUserIdAndProductId(String userId, String productId);

}
