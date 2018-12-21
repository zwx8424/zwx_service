package com.zwx.thriftdemo.consul;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.zwx.Application;

@Component
public class ApplicationShutdownListener implements ApplicationListener<ApplicationFailedEvent> {

	@Autowired
	private ConsulService consulService;

	@Override
	public void onApplicationEvent(ApplicationFailedEvent applicationFailedEvent) {
		Throwable throwable = applicationFailedEvent.getException();
		System.err.println(throwable.getMessage());
		Application.preDestroy();
	}
}
