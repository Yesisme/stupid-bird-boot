package com.epsoft.demo.bean.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:test.properties")
@ConfigurationProperties(prefix = "persion")
public class Person {
	private String name;
	private Integer age;
	private boolean isBoss;
	private Date birth;
	private String lastName;
	private Map<String, Object> maps;
	private List<Object> lists;
	private String hnMock;
	private Map<String,String> tradeMap;
	
	public Map<String, String> getTradeMap() {
		return tradeMap;
	}

	public void setTradeMap(Map<String, String> tradeMap) {
		this.tradeMap = tradeMap;
	}

	public String getHnMock() {
		return hnMock;
	}

	public void setHnMock(String hnMock) {
		this.hnMock = hnMock;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public boolean isBoss() {
		return isBoss;
	}

	public void setBoss(boolean isBoss) {
		this.isBoss = isBoss;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Map<String, Object> getMaps() {
		return maps;
	}

	public void setMaps(Map<String, Object> maps) {
		this.maps = maps;
	}

	public List<Object> getLists() {
		return lists;
	}

	public void setLists(List<Object> lists) {
		this.lists = lists;
	}

}
