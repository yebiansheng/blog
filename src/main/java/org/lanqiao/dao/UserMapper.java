package org.lanqiao.dao;

import org.lanqiao.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);
    
    User getUserByEmail(String email);

    int updateByPrimaryKey(User record);
    
    User getUserByName(String name);
}