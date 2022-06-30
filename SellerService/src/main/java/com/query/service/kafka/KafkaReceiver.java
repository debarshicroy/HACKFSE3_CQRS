package com.query.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.command.service.vo.ProductDO;
import com.query.service.repository.DynamoDBRepository;

import com.query.service.vo.ProductInfo;

@Service
public class KafkaReceiver {
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	static final String kafkaTopic = "my_topic";
	
	@Autowired
	private DynamoDBRepository dynamoDBRepository;

	@KafkaListener(topics = kafkaTopic, groupId = "group_id")
    public void consume(ProductDO productDO) 
    {
        System.out.println(String.format("Message recieved -> %s", productDO.toString()));
        
        ProductInfo pdt = new ProductInfo(productDO.getName(), productDO.getShort_desc(), productDO.getDetailed_desc() , productDO.getCategory(), productDO.getPrice(), productDO.getBidEndDate());
        
        dynamoDBRepository.saveProduct(pdt);
    }
}
