package org.lanqiao.service;

import org.lanqiao.entity.User;

public interface UserService {
	public User getUserById(int id);
	
	public User getUserByName(String name);
	
	int insertSelective(User record);
}
