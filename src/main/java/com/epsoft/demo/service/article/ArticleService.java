package com.epsoft.demo.service.article;

import org.apache.ibatis.annotations.Param;

import com.epsoft.demo.bean.entity.Article;

public interface ArticleService {

	int insertAllArticle(Article article);
	
	Article queryById(@Param("writerId") Integer writerId); 
}
