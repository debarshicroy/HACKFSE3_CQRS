package com.query.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.command.service.vo.BidingDO;
import com.command.service.vo.ProductDO;
import com.query.service.repository.DynamoDBRepository;
import com.query.service.vo.ProductInfo;

@Service
public class KafkaReceiver {
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	static final String kafkaTopic = "my_topic";
	static final String bidKafkaTopic = "my_topic1";
	
	@Autowired
	private DynamoDBRepository dynamoDBRepository;

	@KafkaListener(topics = kafkaTopic, groupId = "group_id")
    public void consume(ProductDO productDO) 
    {
		System.out.println(String.format("Message recieved -> %s", productDO.toString()));
        
        ProductInfo pdt = new ProductInfo(productDO.getId(), productDO.getName(), productDO.getShort_desc(), productDO.getDetailed_desc() , productDO.getCategory(), productDO.getPrice(), productDO.getBidEndDate());
        
		if(productDO.getAction().equalsIgnoreCase("delete")) {
			dynamoDBRepository.deleteProduct(pdt);
		}else {
			dynamoDBRepository.saveProduct(pdt);	
		}
        
    }
	
	@KafkaListener(topics = bidKafkaTopic, groupId = "group_id")
    public void consume(BidingDO bidingDO) 
    {
        System.out.println(String.format("Message recieved -> %s", bidingDO.toString()));
        
        dynamoDBRepository.saveBid(bidingDO);
        
        System.out.println("Inserting successful");
    }
}
