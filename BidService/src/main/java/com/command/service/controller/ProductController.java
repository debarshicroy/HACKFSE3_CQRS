package com.command.service.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.command.service.dao.TransactionalInterface;
import com.command.service.vo.ProductVO;

@RestController
public class ProductController {

	@Autowired
	TransactionalInterface interface1;
	
	@GetMapping("/add-product")
	public void registerProduct() {
	
		ProductVO pd = new ProductVO(1, "sample", "sample sd", "sample dd", "sample cat", 100, new Date());
		interface1.saveProduct(pd);
		
	}
}
