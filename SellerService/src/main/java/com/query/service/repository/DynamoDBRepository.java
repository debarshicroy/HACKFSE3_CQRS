package com.query.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
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
	 
	 public java.util.List<ProductInfo> getProductByName(String prodcutName) {
		 ProductInfo info = new ProductInfo();
		 info.setName(prodcutName);
		 
		  DynamoDBQueryExpression<ProductInfo> queryExpression =
	                new DynamoDBQueryExpression<ProductInfo>()
	                .withHashKeyValues(info)
	                .withLimit(10);

		  java.util.List<ProductInfo> list = dynamoDBMapper.query(ProductInfo.class, queryExpression);		  
	        return list;
	    }
}
