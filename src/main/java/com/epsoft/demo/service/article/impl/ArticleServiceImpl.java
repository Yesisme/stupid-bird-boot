package com.epsoft.demo.service.article.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epsoft.demo.bean.entity.Article;
import com.epsoft.demo.dao.ArticleMapper;
import com.epsoft.demo.service.article.ArticleService;
@Service("articleService")
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	private ArticleMapper articleMapper;
	
	@Override
	public int insertAllArticle(Article article) {
		return articleMapper.insertAll(article);
	}

	@Override
	public Article queryById(Integer writerId) {
		// TODO Auto-generated method stub
		return articleMapper.queryById(writerId);
	}
}
