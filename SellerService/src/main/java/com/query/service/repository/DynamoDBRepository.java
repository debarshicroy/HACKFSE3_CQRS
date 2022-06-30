package com.query.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.query.service.vo.ProductInfo;

@Repository
public class DynamoDBRepository {

	 @Autowired
	 private DynamoDBMapper dynamoDBMapper;
	 
	 public ProductInfo saveProduct(ProductInfo info) {
		 System.out.println("Save product");
	        dynamoDBMapper.save(info);
	        return info;
	 }
}
