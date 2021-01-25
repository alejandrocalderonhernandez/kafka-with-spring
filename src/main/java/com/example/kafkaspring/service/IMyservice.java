package com.example.kafkaspring.service;

import java.util.List;

public interface IMyservice {
	
	static String TOPIC = "my-topic";
	
	public void listen(List<String>  messages);
	public void send(List<String> messages);

}
