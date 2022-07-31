package com.query.service.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.document.BatchGetItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.TableKeysAndAttributes;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.command.service.vo.BidingDO;
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
	 
	 public java.util.List<ProductInfo> getProductById(String productId) {
			/*
			 * ProductInfo info = new ProductInfo(); info.setId(productId);
			 * 
			 * DynamoDBQueryExpression<ProductInfo> queryExpression = new
			 * DynamoDBQueryExpression<ProductInfo>() .withHashKeyValues(info)
			 * .withLimit(10);
			 */
		  System.out.println("Product ID : "+productId);
		  DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		  scanExpression.addFilterCondition("productId", new Condition()                                           
			       .withComparisonOperator(ComparisonOperator.EQ)                                                
			       .withAttributeValueList(new AttributeValue().withN(productId)));
		  

		  java.util.List<ProductInfo> list = dynamoDBMapper.scan(ProductInfo.class, scanExpression);
		  System.out.println("Size : "+list.size());
	        return list;
	    }
	 
	 public java.util.List<ProductInfo> getProductByCategory(String prodcutCat) {
		 DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		 scanExpression.addFilterCondition("category", new Condition()                                           
			       .withComparisonOperator(ComparisonOperator.EQ)                                                
			       .withAttributeValueList(new AttributeValue().withS(prodcutCat)));
		 java.util.List<ProductInfo> list = dynamoDBMapper.scan(ProductInfo.class, scanExpression);
		 
		return list;
	 }
	 
	 public BidingDO saveBid(BidingDO info) {
		 System.out.println("Save bid");
	        dynamoDBMapper.save(info);
	        return info;
	 }
	 
		public List<ProductInfo> getAllProducts() {
			List<ProductInfo> list = dynamoDBMapper.scan(ProductInfo.class, new DynamoDBScanExpression());		  
	        return list;
			
		}
		
		public List<BidingDO> getAllBids() {
			List<BidingDO> list = dynamoDBMapper.scan(BidingDO.class, new DynamoDBScanExpression());		  
	        return list;
			
		}
		
		public boolean deleteProduct(ProductInfo productInfo) {
			System.out.println("Deleting "+productInfo.toString());
			dynamoDBMapper.delete(productInfo);
			return true;
		}
		 
}
