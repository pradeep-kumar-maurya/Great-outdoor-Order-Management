package com.capgemini.go.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.go.dto.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer>{

}
