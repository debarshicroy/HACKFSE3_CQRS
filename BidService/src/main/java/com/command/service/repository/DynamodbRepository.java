package com.command.service.repository;

import java.util.Optional;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.command.service.vo.ProductInfo;


public interface DynamodbRepository extends CrudRepository<ProductInfo, Long>{

	Optional<ProductInfo> findById(Long id);
	
}
