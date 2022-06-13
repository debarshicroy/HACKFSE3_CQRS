package com.query.service.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.query.service.kafka.KafkaSender;
import com.query.service.repository.ProductRepository;
import com.query.service.vo.ProductDO;

@RestController
public class ProductController {

	@Autowired
	KafkaSender kafkaSender;
	
	ProductRepository productRepo;
	
	public ProductController(ProductRepository productRepository) {
		// TODO Auto-generated constructor stub
		this.productRepo = productRepository;
	}
	
	
	@GetMapping("/save")
	public void save() {
		productRepo.save(new ProductDO(1l,"sample","short desc","detail desc","cat",100.0, new Date()));
	}
	
	@GetMapping("/send")
	public void sendkafkaTopic() {
		kafkaSender.sendObj(new ProductDO(1l,"sample","short desc","detail desc","cat",100.0, new Date()));
		//kafkaSender.send("Hello");
	}
	
}
