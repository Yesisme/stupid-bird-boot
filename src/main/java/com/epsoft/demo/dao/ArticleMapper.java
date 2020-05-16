package com.epsoft.demo.dao;

import org.apache.ibatis.annotations.Param;

import com.epsoft.demo.bean.entity.Article;

public interface ArticleMapper {

	int insertAll(Article article);
	
	Article queryById(@Param("writerId") Integer writerId); 
}
