package com.capgemini.go.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.go.dto.OrderProductMap;

@Repository
public interface OrderProductMapRepository extends JpaRepository<OrderProductMap, Integer>{

}
