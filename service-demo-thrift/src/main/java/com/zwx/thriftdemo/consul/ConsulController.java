package com.zwx.thriftdemo.consul;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsulController {

	private Logger logger = Logger.getLogger(this.getClass().toString());


	@Autowired
	private ConsulService consulService;

	@GetMapping("/health")
	public String health() {
		return "success";
	}
}
