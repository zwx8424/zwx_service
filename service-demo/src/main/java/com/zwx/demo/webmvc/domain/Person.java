package com.zwx.demo.webmvc.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test_person")
public class Person {
	/*
	Jackson提供了一系列注解，方便对JSON序列化和反序列化进行控制，下面介绍一些常用的注解。
	@JsonIgnore 此注解用于属性上，作用是进行JSON操作时忽略该属性。
	@JsonFormat 此注解用于属性上，作用是把Date类型直接转化为想要的格式，如@JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")。
	@JsonProperty 此注解用于属性上，作用是把该属性的名称序列化为另外一个名称，如把trueName属性序列化为name，@JsonProperty("name")。
	*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String sex;
	private Integer age;
	private String note;
	@Column(name = "insert_time", insertable = false)
	//表示在ORM框架执行插入操作时，该字段是否应出现INSETRT语句中，默认为true.这样可以利用DB字段定义的默认值
	private Date insertTime;

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
