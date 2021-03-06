package org.lanqiao.service;

import org.lanqiao.entity.User;

public interface UserService {
	public User getUserById(int id);
	
	public User getUserByEmail(String email);
	
	public User getUserByName(String name);
	
	int insertSelective(User record);
	
	int updateByPrimaryKeySelective(User record);
}
