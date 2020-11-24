package com.capgemini.go.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.go.dto.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{

}
