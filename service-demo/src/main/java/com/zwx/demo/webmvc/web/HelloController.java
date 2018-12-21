package com.zwx.demo.webmvc.web;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.zwx.demo.webmvc.domain.Person;

@Controller
@RequestMapping("/demo")
public class HelloController {

	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

	// 页面跳转demo
	@RequestMapping(value = "/hello", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String hello() {
		return "你好！hello";
	}

	@RequestMapping(value = "/say/{msg}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody String say(@PathVariable(value = "msg") String msg) {
		logger.info("msg:" + msg);
		return "{\"msg\":\"you\",\"say\":\"" + msg + "\"}";
	}

	@RequestMapping(value = "/listPerson", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<Person> listPerson(@RequestParam(value = "name", required = false, defaultValue = "") String name) {
		logger.info("name:" + name);
		List<Person> lstPersons = new ArrayList<Person>();

		Person person = new Person();
		person.setName("张三");
		person.setSex("男");
		person.setAge(25);
		person.setId(101L);
		person.setNote("listPerson");
		lstPersons.add(person);

		Person person2 = new Person();
		person2.setName("李四");
		person2.setSex("女");
		person2.setAge(23);
		person2.setId(102L);
		lstPersons.add(person2);

		Person person3 = new Person();
		person3.setName("王五");
		person3.setSex("男");
		person3.setAge(27);
		person3.setId(103L);
		lstPersons.add(person3);

		return lstPersons;
	}

	@RequestMapping(value = "/addPerson", method = RequestMethod.POST)
	public @ResponseBody Person addPerson(@RequestBody Person person) {
		logger.info(person.getName());
		person.setNote("addPerson");
		return person;
	}
}
