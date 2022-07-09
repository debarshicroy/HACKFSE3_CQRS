package com.command.service.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.command.service.dao.TransactionalInterface;
import com.command.service.kafka.KafkaSender;
import com.command.service.repository.BidRepository;
import com.command.service.repository.TransactionalRepository;
import com.command.service.vo.BidingDO;
import com.command.service.vo.ProductDO;
import com.command.service.vo.ProductVO;

@Service
public class TransactionImpl implements TransactionalInterface{
	
	@Autowired
	TransactionalRepository transactionalRepo;
	
	@Autowired
	BidRepository bidRepository;
	
	@Autowired
	KafkaSender kafkaSender;

	@Override
	public ProductVO saveProduct(ProductVO pdt) {
		// TODO Auto-generated method stub
		
		  List<ProductVO> list = transactionalRepo.findByName(pdt.getName());
		  if(list.size()>0) { pdt.setId(list.get(0).getId()); }
		 		
		System.out.println("Save product:"+pdt.toString());
		ProductVO vo = transactionalRepo.save(pdt);
		ProductDO productDO = new ProductDO(pdt.getId(),pdt.getName(), pdt.getShort_desc(), pdt.getDetailed_desc(), pdt.getCategory(), pdt.getPrice(), pdt.getBidEndDate());
		kafkaSender.sendObj(productDO);
		return vo;
	}

	@Override
	public ProductVO updateProduct(ProductVO pdt) {
		// TODO Auto-generated method stub
		try {
			List<ProductVO> list = transactionalRepo.findByName(pdt.getName());
			if (list.size() > 0) {
				pdt.setId(list.get(0).getId());
				//pdt.setPrice(list.get(0).getPrice());
				System.out.println("Save product:" + pdt.toString());
				ProductVO vo = transactionalRepo.save(pdt);
				System.out.println("Bid amount updated successfully!!");
				ProductDO productDO = new ProductDO(pdt.getId(),pdt.getName(), pdt.getShort_desc(), pdt.getDetailed_desc(), pdt.getCategory(), pdt.getPrice(), pdt.getBidEndDate());
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pdt;
	}

	@Override
	public boolean deleteProduct(ProductVO pdt) {
		// TODO Auto-generated method stub
		transactionalRepo.delete(pdt);
		return true;
	}

	@Override
	public BidingDO saveBid(BidingDO bidingDO) {
		// TODO Auto-generated method stub
		bidRepository.save(bidingDO);
		kafkaSender.sendObj(bidingDO);
		return bidingDO;
	}

	@Override
	public List<BidingDO> showBids(String productid) {
		// TODO Auto-generated method stub
		List<BidingDO> list = bidRepository.findByName(productid);
		return list;
	}

}
