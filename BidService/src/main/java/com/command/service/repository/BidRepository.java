package com.command.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.command.service.vo.BidingDO;
import com.command.service.vo.ProductVO;

public interface BidRepository extends CrudRepository<BidingDO, Long>, JpaRepository<BidingDO, Long> {
	
	List<BidingDO> findByName(String name);
	
}
