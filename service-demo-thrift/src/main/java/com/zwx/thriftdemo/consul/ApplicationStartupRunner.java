package com.zwx.thriftdemo.consul;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartupRunner implements CommandLineRunner {

	@Autowired
	private ConsulService consulService;

	@Override
	public void run(String... strings) throws Exception {
		consulService.registerService();
	}
}
