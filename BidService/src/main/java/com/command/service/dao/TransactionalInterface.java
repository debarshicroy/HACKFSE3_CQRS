package com.command.service.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.command.service.vo.ProductVO;



public interface TransactionalInterface {

	Optional<ProductVO> saveProduct(ProductVO pdt);
	
	ProductVO updateProduct(ProductVO pdt);
	
	boolean deleteProduct(ProductVO pdt);
	
}
