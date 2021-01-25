package com.example.kafkaspring.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@Configuration
public class MyConsumerConfig {
	
	public Map<String, Object> consumerProps() {
		Map<String, Object>props=new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG," localhost:9092");
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "my-grlistenerContainerFactoryoup");
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		return props;
	}
	
	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(this.consumerProps());
	}
	
	@Bean(name = "listenerContainerFactory")
	public ConcurrentKafkaListenerContainerFactory<String, String> listenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> listenerContainerFactory =
				new ConcurrentKafkaListenerContainerFactory<>();
		listenerContainerFactory.setConsumerFactory(this.consumerFactory());
		listenerContainerFactory.setBatchListener(true);
		return listenerContainerFactory;
	}

}
