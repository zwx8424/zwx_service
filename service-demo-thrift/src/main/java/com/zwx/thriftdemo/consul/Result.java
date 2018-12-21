package com.zwx.thriftdemo.consul;

public class Result {

	private String name;
	private String result;

	public Result() {
	}

	public Result(String name, String result) {
		this.name = name;
		this.result = result;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
