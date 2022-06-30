package com.command.service.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.command.service.dao.DynamoDBInterface;
import com.command.service.repository.DynamodbRepository;
import com.command.service.repository.ProductInfoRepo;
import com.command.service.vo.ProductInfo;

@Service
public class DynamoDBImpl implements DynamoDBInterface{

	private DynamodbRepository dynamoDBRepo;
	
	@Override
	public boolean save(ProductInfo productInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * private ProductInfoRepo productInfoRepo;
	 * 
	 * 
	 * 
	 * public void createProduct(final ProductInfo product) {
	 * productInfoRepo.save(product); }
	 */
	
	public void createProduct(final ProductInfo product) {
		dynamoDBRepo.save(product);
	}

}
