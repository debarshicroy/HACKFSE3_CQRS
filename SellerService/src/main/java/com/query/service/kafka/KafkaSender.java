package com.query.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.query.service.vo.ProductDO;

@Service
public class KafkaSender {
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;
	
	String kafkaTopic = "my_topic";
	
	/*
	 * public void send(String message) {
	 * 
	 * kafkaTemplate.send(kafkaTopic, message); }
	 */
	
	public void sendObj(ProductDO productDO) {
		kafkaTemplate.send(kafkaTopic, productDO);
	}
}
