package org.lanqiao.service;

import org.lanqiao.dao.UserMapper;
import org.lanqiao.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper dao;
	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return dao.selectByPrimaryKey(id);
	}
	@Override
	public User getUserByName(String name) {
		// TODO Auto-generated method stub
		return dao.getUserByName(name);
	}
	@Override
	public int insertSelective(User record) {
		// TODO Auto-generated method stub
		return dao.insertSelective(record);
	}
	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return dao.getUserByEmail(email);
	}
	@Override
	public int updateByPrimaryKeySelective(User record) {
		// TODO Auto-generated method stub
		return dao.updateByPrimaryKeySelective(record);
	}

}
