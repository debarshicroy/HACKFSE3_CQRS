package com.query.service.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
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
	 
	 public BidingDO saveBid(BidingDO info) {
		 System.out.println("Save bid");
	        dynamoDBMapper.save(info);
	        return info;
	 }
	 
		/*
		 * public List<ProductInfo> getAllProducts(){ ArrayList<ProductInfo> ids = new
		 * ArrayList<ProductInfo>(); ScanResult result = null;
		 * 
		 * do{ ScanRequest req = new ScanRequest(); req.setTableName(tableName);
		 * 
		 * if(result != null){ req.setExclusiveStartKey(result.getLastEvaluatedKey()); }
		 * 
		 * result = dynamoDBMapper.scan(req);
		 * 
		 * List<Map<String, AttributeValue>> rows = result.getItems();
		 * 
		 * for(Map<String, AttributeValue> map : rows){ try{ AttributeValue v =
		 * map.get("STUDENT_ID"); String id = v.getS(); ids.add(Long.parseLong(id)); }
		 * catch (NumberFormatException e){ System.out.println(e.getMessage()); } } }
		 * while(result.getLastEvaluatedKey() != null); }
		 */
}
