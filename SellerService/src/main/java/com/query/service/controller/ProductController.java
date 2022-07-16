package com.query.service.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.query.service.kafka.KafkaSender;
import com.query.service.repository.DynamoDBRepository;
import com.query.service.repository.ProductRepository;
import com.query.service.vo.ProductDO;
import com.query.service.vo.ProductInfo;

@RestController
public class ProductController {

	@Autowired
	KafkaSender kafkaSender;
	
	ProductRepository productRepo;
	
	@Autowired
	private DynamoDBRepository dynamoDBRepository;
	
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
	
	@GetMapping("/test")
    public ProductInfo test() {
		System.out.println("Test method()");
		ProductInfo pdt = new ProductInfo("Ball", "bbba", "asdsadasdsa", "Sports", 101.10, new Date());
        return dynamoDBRepository.saveProduct(pdt);
    }
	
	@GetMapping("/getData/{pName}")
    public ProductInfo getData(@PathVariable("pName") String pName) {
		System.out.println("Test method()");
		List<ProductInfo> pdt = dynamoDBRepository.getProductByName(pName);
		return pdt.get(0);
    }
	
	@GetMapping("/showProducts")
	public List<ProductInfo> showProducts() {
		List<ProductInfo> list = dynamoDBRepository.getAllProducts();
		return list;
	}
	 
}
