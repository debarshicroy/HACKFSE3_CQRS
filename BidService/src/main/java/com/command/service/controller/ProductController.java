package com.command.service.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.command.service.dao.TransactionalInterface;
import com.command.service.vo.BidingDO;
import com.command.service.vo.ProductVO;

@RestController
public class ProductController {

	@Autowired
	TransactionalInterface interface1;
	
	@PostMapping("/add-product")
	public String registerProduct(@RequestBody ProductVO product) {
		//ProductVO pd = new ProductVO("sample123", "sample sd123456", "sample dd123000", "sample cat123", 100, new Date());
		product.setBidEndDate(new Date());
		System.out.println("Going to add product:"+product.toString());
		ProductVO vo = interface1.saveProduct(product);
		
		
		
		/*
		 * DynamoDBImpl dbImpl = new DynamoDBImpl(); ProductInfo pdt = new
		 * ProductInfo(); pdt.setName(product.getName());
		 * pdt.setCategory(product.getCategory()); dbImpl.createProduct(pdt);
		 */
		 
		 
		
		return vo.toString();
	}
	
	@PostMapping("/place-bid")
	public String placeBid(@RequestBody BidingDO bidingDO) {
		interface1.saveBid(bidingDO);
		return bidingDO.toString();
	}
	
	@GetMapping("/show-bid/{productId}")
	public String placeBid(@PathVariable("productId") String productid) {
		List<BidingDO> list = interface1.showBids(productid);
		
		return list.get(0).toString();
	}
	
	@GetMapping("/delete/{productName}")
	public String deleteProduct(@PathVariable("productName") String productName) {
		ProductVO vo = new ProductVO();
		vo.setName(productName);
		interface1.deleteProduct(vo);
		return "Product with name "+productName+" deleted";
	}
	
	@GetMapping("/showProducts")
	public List<ProductVO> showProducts() {
		List<ProductVO> list = interface1.showAllProducts();
		return list;
	}
}
