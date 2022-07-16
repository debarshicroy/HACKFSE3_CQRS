package com.command.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.command.service.vo.ProductDO;



@Service
public class KafkaReceiver {
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	static final String kafkaTopic = "my_topic";

	/*
	 * @KafkaListener(topics = kafkaTopic, groupId = "group_id") public void
	 * consume(ProductDO productDO) {
	 * System.out.println(String.format("Message recieved -> %s",
	 * productDO.toString())); }
	 */
}
