package com.command.service.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.command.service.vo.ProductInfo;

@EnableScan
@Repository
public interface ProductInfoRepo extends CrudRepository<ProductInfo, Long>{
	public boolean saveData(ProductInfo pdt);
}
