package com.command.service.repository;

import org.springframework.data.repository.CrudRepository;

import com.command.service.vo.ProductVO;

public interface TransactionalRepository extends CrudRepository<ProductVO, Long>{

}
