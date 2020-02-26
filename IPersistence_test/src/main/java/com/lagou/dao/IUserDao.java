package com.lagou.dao;

import com.lagou.pojo.User;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.util.List;

public interface IUserDao {
    //查询所有用户
    public List<User> findAll() throws Exception;

    //根据条件查询
    public User findByCondition(User user) throws Exception;

    //修改
    public int update(User user) throws Exception;

    //删除
    public int delete(User user) throws Exception;


}
