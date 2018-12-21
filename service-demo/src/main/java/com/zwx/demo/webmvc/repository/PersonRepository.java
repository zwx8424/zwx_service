package com.zwx.demo.webmvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zwx.demo.webmvc.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

	List<Person> findByName(String name);

	@Query("select p from Person p where p.name= :name")
	Person withNameQuery(@Param("name") String name);

	Person findByNameAndSex(String name, String sex);

}
