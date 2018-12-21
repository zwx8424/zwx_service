package com.zwx.demo.webmvc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.zwx.demo.webmvc.domain.Person;

@Repository
public class PersonDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Person getById(long id) {
		String sql = "select * from test_person where id=?";
		RowMapper<Person> rowMapper = new BeanPropertyRowMapper<Person>(Person.class);
		return jdbcTemplate.queryForObject(sql, rowMapper, id);

	}

	public List<Person> getByName(String name) {
		String sql = "select * from test_person where name=?";
		return jdbcTemplate.queryForList(sql, Person.class, name);

	}

	public int update(Person person) {
		String sql = "update test_person t set t.note=? where t.id=?";
		return jdbcTemplate.update(sql, person.getNote(), person.getId());

	}

	public int delete(long id) {
		String sql = "delete from test_person t where t.id=?";
		return jdbcTemplate.update(sql, id);

	}

	public int save(Person person) {
		String sql = "insert into test_person (id,name,sex,age,note)values(?,?,?,?,?) ";
		return jdbcTemplate.update(sql, person.getId(), person.getName(), person.getSex(), person.getAge(), person.getNote());

	}
}
