package com.command.service.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.command.service.dao.TransactionalInterface;
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
		return vo.toString();
	}
}
