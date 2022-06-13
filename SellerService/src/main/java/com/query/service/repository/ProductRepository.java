package com.query.service.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.query.service.vo.ProductDO;


@Component
public interface ProductRepository extends MongoRepository<ProductDO, String>{

	
}
