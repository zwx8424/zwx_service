package com.zwx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RestfulHelloController {

	@Autowired
	private DiscoveryClient discoveryClient;

	@RequestMapping(value = "/hello", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object hello() {
		return discoveryClient.getServices();
		// return "你好！hello";
	}
}
