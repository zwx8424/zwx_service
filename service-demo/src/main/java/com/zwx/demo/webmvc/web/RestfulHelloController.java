package com.zwx.demo.webmvc.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zwx.demo.webmvc.domain.Person;
import com.zwx.demo.webmvc.service.PersonService;
import com.zwx.framework.util.JsonUtils;

@RestController
@RequestMapping("/demo/restful")
public class RestfulHelloController {
	private static final Logger logger = LoggerFactory.getLogger(RestfulHelloController.class);

	@Autowired
	private PersonService personService;
	@Autowired
	private DiscoveryClient discoveryClient;

	@RequestMapping(value = "/hello", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object hello() {
		return discoveryClient.getServices();
		//return "你好！hello";
	}

	@RequestMapping(value = "/say/{msg}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String say(@PathVariable(value = "msg") String msg) {
		logger.info("msg:" + msg);
		return "{\"msg\":\"you say:'" + msg + "'\"}";
	}

	@RequestMapping(value = "/getPerson", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Person getPerson(@RequestBody Long id) {
		logger.info("id:" + id);
		Person person = null;
		// 增加DB测试
		person = personService.findOne(id);
		return person;
	}

	@RequestMapping(value = "/addPerson", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Person addPerson(@RequestBody String request) {
		logger.info("request:\n {}", request);
		Person person = JsonUtils.toObject(request, Person.class);
		if (person == null) {
			logger.info("person is null! now return!");
			return null;
		}
		person.setNote("addPerson");
		person.setId(null);
		logger.info(person.getName());

		// 增加DB测试
		person = personService.savePerson(person);

		return person;
	}

	@RequestMapping(value = "/deletePerson", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object deletePerson(@RequestBody Long id) {

		logger.info("id:" + id);
		// 增加DB测试
		personService.deletePerson(id);

		return "success";
	}

	@RequestMapping(value = "/updatePerson", method = RequestMethod.POST)
	public Person updatePerson(@RequestBody String request) {
		logger.info("request:\n {}", request);
		Person person = JsonUtils.toObject(request, Person.class);
		if (person == null) {
			logger.info("person is null! now return!");
			return null;
		}
		logger.info("id:" + person.getId());

		// 增加DB测试
		person = personService.updatePerson(person);

		return person;
	}

	@RequestMapping(value = "/listPerson", method = RequestMethod.POST)
	public List<Person> listPerson(@RequestBody String name) {
		StopWatch sw = new StopWatch();
		sw.start("朱卫鑫");
		List<Person> lstPersons = null;

		// 增加DB测试
		lstPersons = personService.findByName(name);
		sw.stop();
		logger.info(sw.prettyPrint());
		return lstPersons;
	}

}
