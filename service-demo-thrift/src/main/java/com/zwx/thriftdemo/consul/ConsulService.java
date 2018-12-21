package com.zwx.thriftdemo.consul;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.agent.model.NewService;
import com.ecwid.consul.v1.health.model.HealthService;
import com.zwx.config.ServerConfig;

@Service
public class ConsulService {

	ConsulClient consulClient;

	@Bean
	public ConsulClient consulClient() {
		this.consulClient = new ConsulClient(host, Integer.valueOf(port));
		return this.consulClient;
	}

	@Value("${consul.host}")
	private String host;

	@Value("${consul.port}")
	private String port;

	@Value("${spring.application.name}")
	private String clientName;

	@Value("${consul.discovery.serviceName}")
	private String serviceName;

	@Value("${consul.discovery.register}")
	private String register;

	@Value("${consul.discovery.healthCheckPath}")
	private String healthCheckPath;

	@Value("${consul.discovery.healthCheckInterval}")
	private String healthCheckInterval;

	@Value("${consul.discovery.tags}")
	private String tags;

	@Value("${consul.instanceId}")
	private String instanceId;

	public String registerService() {
		// register new service with associated health check
		NewService newService = new NewService();
		newService.setId(instanceId);
		newService.setTags(Collections.singletonList(tags));
		newService.setName(clientName);
		newService.setAddress(host);
		newService.setPort(Integer.parseInt(port));

		NewService.Check serviceCheck = new NewService.Check();
		serviceCheck.setHttp(ServerConfig.getUrl() + "/actuator/health");
		//System.out.println(ServerConfig.getUrl() + "/actuator/health");
		serviceCheck.setInterval(healthCheckInterval);
		serviceCheck.setDeregisterCriticalServiceAfter("1m");
		newService.setCheck(serviceCheck);

		consulClient.agentServiceRegister(newService);
		return null;
	}

	public String deregisterService() {
		System.out.println("Deregister ------" + instanceId);
		List<HealthService> response = consulClient.getHealthServices(clientName, false, null).getValue();
		//System.out.println(response.size());
		for (HealthService service : response) {
			//创建一个用来剔除无效实例的ConsulClient，连接到无效实例注册的agent
			ConsulClient clearClient = new ConsulClient(host, 8500);
			service.getChecks().forEach(check -> {
				if (check.getServiceId().equals(instanceId)) {
					clearClient.agentServiceDeregister(check.getServiceId());
					System.out.println("Deregister success------" + instanceId);
				}
			});
		}
		System.out.println("Deregister over ------" + instanceId);
		return null;
	}

	public String fetchConsulServices() {
		List<HealthService> response = consulClient.getHealthServices(clientName, false, null).getValue();
		System.out.println(response.size());
		for (HealthService service : response) {
			//创建一个用来剔除无效实例的ConsulClient，连接到无效实例注册的agent

		}
		System.out.println("Deregister over ------" + instanceId);
		return null;
	}
}
