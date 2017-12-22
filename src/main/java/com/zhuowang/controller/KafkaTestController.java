package com.zhuowang.controller;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhuowang.config.Consumer;
import com.zhuowang.config.Producer;

@Controller
@RequestMapping("/test")
public class KafkaTestController {

	private static final Logger logger = LoggerFactory.getLogger(KafkaTestController.class);

	public KafkaTestController() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private Producer sender;

	@Autowired
	private Consumer receiver;

	@RequestMapping("/ping")
	@ResponseBody
	public String ping() {
		String message = "PONG";
		return message;
	}

	@RequestMapping("/kafka/send")
	@ResponseBody
	public String testReceiver() throws Exception {
		sender.sendMessage("helloworld.t", "Hello Spring Kafka!");

		receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
		logger.info(receiver.getLatch().getCount() + "");
		return "OK";
	}
}
