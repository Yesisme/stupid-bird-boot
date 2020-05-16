package com.epsoft.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.epsoft.demo.bean.entity.User;

import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {

	List<User> selectByName(@Param("name") String name);

	void addUser(User user);

	//void addUser(@Param("userName")String userName,@Param("age")Integer age,@Param("sex")String sex,@Param("brith")String brith,@Param("relationId")Integer relationId,@Param("email")String email,@Param("phone")String phone);
	
	int updateById(@Param("id") Long id, @Param("sex") String sex, @Param("age") Integer age);

	void deleteByIds(@Param("list") List<Integer> list);

	User SelectByUserName(@Param("name") String username);
	
	List<User> selectName(@Param("userName")String userName);
	
	Long selectById(Long id);
	
	List<User> queryAllUser(); 
}
