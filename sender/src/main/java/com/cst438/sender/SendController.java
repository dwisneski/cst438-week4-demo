package com.cst438.sender;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.amqp.core.Queue;

@RestController
public class SendController {
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Autowired
	Queue queue;
	
	/*
	 * send message using HTTP post 
	 */
	@PostMapping("/send1")
	public MessageDTO sendMessage1(@RequestBody MessageDTO smsg) {
		
		RestTemplate httpTemplate = new RestTemplate();
		
		System.out.println("Sending http message: "+smsg);
		ResponseEntity<MessageDTO> response = httpTemplate.postForEntity(
				"http://localhost:8081/message",   // URL
				smsg,                              // data to send
				MessageDTO.class);                 // return data type
		
		HttpStatus rc = response.getStatusCode();
		System.out.println("HttpStatus: "+rc);
		MessageDTO returnObject = response.getBody();
		System.out.println(returnObject);
		return returnObject;
		
	}
	
	/*
	 * send message using RabbitMQ message queue
	 */
	@PostMapping("/send2")
	public void sendMessage2(@RequestBody MessageDTO smsg) {
		
		System.out.println("Sending rabbitmq message: "+smsg);
		rabbitTemplate.convertAndSend(queue.getName(), smsg);
		System.out.println("Message sent.");
		
	}
	
}