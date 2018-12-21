package com.zwx.thriftdemo.consul;

import java.util.List;

public class Service {

	private String name;
	private String path;
	private List<String> inputParams;
	private List<String> outputParams;

	public Service(String name, String path) {
		this.name = name;
		this.path = path;
	}

	public Service(String name, String path, List<String> inputParams, List<String> outputParams) {
		this.name = name;
		this.path = path;
		this.inputParams = inputParams;
		this.outputParams = outputParams;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<String> getInputParams() {
		return inputParams;
	}

	public void setInputParams(List<String> inputParams) {
		this.inputParams = inputParams;
	}

	public List<String> getOutputParams() {
		return outputParams;
	}

	public void setOutputParams(List<String> outputParams) {
		this.outputParams = outputParams;
	}
}
