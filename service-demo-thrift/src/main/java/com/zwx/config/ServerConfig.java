package com.zwx.config;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ServerConfig implements ApplicationListener<WebServerInitializedEvent> {
	private static String serverName;
	private static String serverHost;
	private static int serverPort;

	public static String getServerName() {
		return serverName;
	}

	public static String getServerHost() {
		return serverHost;
	}

	public static int getServerPort() {
		return serverPort;
	}

	@Override
	public void onApplicationEvent(WebServerInitializedEvent event) {
		serverPort = event.getWebServer().getPort();
		InetAddress address = null;
		try {
			address = InetAddress.getLocalHost();
			serverName = address.getHostName();
			serverHost = address.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public static String getUrl() {
		return "http://" + serverHost + ":" + serverPort;
	}

}