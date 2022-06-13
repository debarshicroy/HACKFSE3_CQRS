package com.command.service.dao.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.command.service.dao.TransactionalInterface;
import com.command.service.repository.TransactionalRepository;
import com.command.service.vo.ProductVO;

@Service
public class TransactionImpl implements TransactionalInterface{
	
	@Autowired
	TransactionalRepository transactionalRepo;

	@Override
	public Optional<ProductVO> saveProduct(ProductVO pdt) {
		// TODO Auto-generated method stub
		transactionalRepo.save(pdt);
		Optional<ProductVO> pdt1 = transactionalRepo.findById(pdt.getId());
		return pdt1;
	}

	@Override
	public ProductVO updateProduct(ProductVO pdt) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public boolean deleteProduct(ProductVO pdt) {
		// TODO Auto-generated method stub
		return false;
	}

}
