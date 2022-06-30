package com.command.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.command.service.vo.ProductVO;

public interface TransactionalRepository extends CrudRepository<ProductVO, Long>, JpaRepository<ProductVO, Long> {
	List<ProductVO> findByCategory(String name);
	
	List<ProductVO> findByName(String product_name);
	
	List<ProductVO> findByPrice(Double price);
}
