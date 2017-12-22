package com.zhuowang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhuowang.kafka.MsgProducer;

@Controller
@RequestMapping("/kafka")
public class KafkaController {
	
	@Autowired
	private MsgProducer msgProducer;

	@ResponseBody
	@RequestMapping("/test")
	public String test(){
		msgProducer.send();
		return "test";
	}
}
