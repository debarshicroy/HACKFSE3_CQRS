package com.command.service.dao;

import com.command.service.vo.BidingDO;
import com.command.service.vo.ProductVO;



public interface TransactionalInterface {

	ProductVO saveProduct(ProductVO pdt);
	
	ProductVO updateProduct(ProductVO pdt);
	
	boolean deleteProduct(ProductVO pdt);
	
	BidingDO saveBid(BidingDO bidingDO);
	
	java.util.List<BidingDO> showBids(String productid);
	
}
