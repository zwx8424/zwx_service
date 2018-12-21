package com.zwx.demo.webmvc.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwx.demo.webmvc.dao.PersonDao;
import com.zwx.demo.webmvc.domain.Person;
import com.zwx.demo.webmvc.repository.PersonRepository;

@Service
public class PersonService {

	private static final Logger logger = LoggerFactory.getLogger(PersonService.class);
	@Autowired
	PersonRepository personRepository;
	@Autowired
	PersonDao personDao;

	public List<Person> findByName(String name) {
		return personRepository.findByName(name);
	}

	public Person findOneByName(String name) {
		List<Person> list = personRepository.findByName(name);
		return list == null || list.size() == 0 ? null : list.get(0);
	}

	public Person findOne(Long id) {
		Person person = personDao.getById(id);
		logger.info("weixin trace:" + (person == null ? "none" : person.getName()));
		return personRepository.findById(id).get();
	}

	@Transactional
	public Person savePerson(Person person) {
		person.setId(null);
		return personRepository.saveAndFlush(person);
	}

	@Transactional
	public void deletePerson(Long id) {
		if (id == null || id.intValue() < 0) {
			throw new IllegalArgumentException("when delete ,id must be not null!");
		}
		personRepository.deleteById(id);
	}

	@Transactional
	public Person updatePerson(Person person) {
		if (person == null || person.getId() == null) {
			throw new IllegalArgumentException("when update ,object must be not null!");
		}

		/*personRepository.save(person);
		logger.info("JPA save complete!");
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}*/
		//person.setNote("this is jdbcTemplate test!");
		personDao.update(person);
		logger.info("JDBC save complete!");
		/*
		 * if (1 == 1) { throw new RuntimeException("transaction test!"); }
		 */
		return person;
	}
}
