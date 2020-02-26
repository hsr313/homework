package com.lagou.dao;

import com.lagou.io.Resources;
import com.lagou.pojo.User;
import com.lagou.sqlSession.DefaultSqlSession;
import com.lagou.sqlSession.SqlSession;
import com.lagou.sqlSession.SqlSessionFactory;
import com.lagou.sqlSession.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.lang.reflect.Proxy;
import java.util.List;

public class UserDaoImpl implements IUserDao{

    @Override
    public List<User> findAll() throws Exception{
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> users = sqlSession.selectList("user.selectList");
        for (User user1 : users) {
            System.out.println(user1.getUsername());
        }
        return users;
    }

    @Override
    public User findByCondition(User user) throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user1 = sqlSession.selectOne("user.selectOne",user);
        System.out.println(user1.getUsername());
        return user1;
    }

    @Override
    public int update(User user) throws Exception {
        return 0;
    }

    @Override
    public int delete(User user) throws Exception {
        return 0;
    }




}
