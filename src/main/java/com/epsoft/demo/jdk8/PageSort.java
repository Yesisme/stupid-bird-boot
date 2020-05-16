package com.epsoft.demo.jdk8;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epsoft.demo.bean.entity.User;
import com.epsoft.demo.dao.UserMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class PageSort {

	@Autowired
	private UserMapper userMapper;
	
	public void testPage() {
		PageHelper.startPage(1, 10);
		List<User> selectAll = userMapper.selectAll();
		System.out.println(selectAll.size());
		PageInfo pageInfo = new PageInfo<>(selectAll);
		System.out.println(pageInfo.isHasNextPage());
		System.out.println(pageInfo.isHasPreviousPage());
		
}
}