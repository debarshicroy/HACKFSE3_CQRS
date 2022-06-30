package com.command.service.dao;

import com.command.service.vo.ProductInfo;

public interface DynamoDBInterface {

	boolean save(ProductInfo productInfo);
	
}
