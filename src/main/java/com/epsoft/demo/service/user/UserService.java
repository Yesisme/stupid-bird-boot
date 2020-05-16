package com.epsoft.demo.service.user;

import java.text.ParseException;
import java.util.List;

import com.epsoft.demo.bean.entity.User;
import com.epsoft.demo.common.ServerResponse;
import com.github.pagehelper.Page;

public interface UserService {

	Page<User> getUser(String name, String currentPage, String pageSize);

	void addUser(User user);

	void addUser(String userName,Integer age, String sex,String brith,Integer relationId,String email,String phone) throws InterruptedException, ParseException;
	
	ServerResponse<String> updateById(Long id, String sex, Integer age);

	void deleteByIds(String id);

	ServerResponse<String> selectUserName(String userName);
	
	User selectName(String userName);
	
	Long selectById(Long id);

	void mockTcp();
	
	List<User> queryUserList();
}
