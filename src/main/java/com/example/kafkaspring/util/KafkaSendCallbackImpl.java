package com.example.kafkaspring.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.support.SendResult;

public class KafkaSendCallbackImpl implements KafkaSendCallback<String, String> {
	
	private static final Logger log = LoggerFactory.getLogger(KafkaSendCallbackImpl.class);

	@Override
	public void onSuccess(SendResult<String, String> result) {
		log.info("Message send: {}", result);
		
	}

	@Override
	public void onFailure(Throwable ex) {
		log.error("Error message", ex);
		
	}

	@Override
	public void onFailure(KafkaProducerException ex) {
		log.error("Error message", ex);
	}

	
}
