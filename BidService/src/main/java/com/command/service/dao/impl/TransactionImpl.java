package com.command.service.dao.impl;

import java.util.List;
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
	public ProductVO saveProduct(ProductVO pdt) {
		// TODO Auto-generated method stub
		
		  List<ProductVO> list = transactionalRepo.findByName(pdt.getName());
		  if(list.size()>0) { pdt.setId(list.get(0).getId()); }
		 		
		System.out.println("Save product:"+pdt.toString());
		ProductVO vo = transactionalRepo.save(pdt);
		
		return vo;
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
