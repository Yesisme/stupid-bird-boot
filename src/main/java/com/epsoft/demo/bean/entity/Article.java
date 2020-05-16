package com.epsoft.demo.bean.entity;

import java.lang.reflect.Field;
import java.util.Arrays;

public class Article extends HomeInfo{

	private Integer id;
	
	private String articleContent;
	
	private String titleName;
	
	private Integer writerId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public Integer getWriterId() {
		return writerId;
	}

	public void setWriterId(Integer writerId) {
		this.writerId = writerId;
	}

	public static void main(String[] args) {
		Field[] fields = Article.class.getFields();
		Arrays.asList(fields).stream().forEach(s-> System.out.println(s));
		System.out.println("------------------------------");
		Field[] declaredFields = Article.class.getDeclaredFields();
		Arrays.asList(declaredFields).stream().forEach(s-> System.out.println(s));

	}

}
