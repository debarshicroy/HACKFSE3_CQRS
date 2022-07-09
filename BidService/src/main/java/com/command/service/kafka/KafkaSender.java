package com.command.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.command.service.vo.BidingDO;
import com.command.service.vo.ProductDO;

@Service
public class KafkaSender {
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;
	
	String kafkaTopic = "my_topic";
	String bidKafkaTopic = "my_topic1";
	
	/*
	 * public void send(String message) {
	 * 
	 * kafkaTemplate.send(kafkaTopic, message); }
	 */
	
	public void sendObj(ProductDO productDO) {
		System.out.println("Sending obj through kafka");
		kafkaTemplate.send(kafkaTopic, productDO);
	}
	
	public void sendObj(BidingDO bidingDO) {
		System.out.println("Sending obj through kafka");
		kafkaTemplate.send(bidKafkaTopic, bidingDO);
	}
}
