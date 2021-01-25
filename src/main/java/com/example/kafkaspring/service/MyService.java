package com.example.kafkaspring.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import com.example.kafkaspring.util.KafkaSendCallbackImpl;

@Service
public class MyService implements IMyservice {
	
	@Autowired
	private KafkaTemplate<String, String> template;
	
	private static final Logger log = LoggerFactory.getLogger(MyService.class);

	@Override
	@KafkaListener(topics = "my-topic", 
	               containerFactory = "listenerContainerFactory", 
	               groupId = "my-group",
	               properties = {"max.poll.interval.ms:60000", 
	            		         "max.poll.records:100"
	            		        }
	)
	public void listen(List<String>  messages) {
		messages.forEach(message -> {
			log.info(message);
		});
	}

	@Override
	public void send(List<String> messages) {
		messages.forEach(message -> {
			ListenableFuture<SendResult<String,String>> future = this.template.send(TOPIC, message);
			future.addCallback(new KafkaSendCallbackImpl());
			//this.template.send(TOPIC, message).get(100, TimeUnit.MILLISECONDS); sync
		});
		
	}

}
