package com.lagou.test;

import com.lagou.dao.IUserDao;
import com.lagou.io.Resources;
import com.lagou.pojo.User;
import com.lagou.sqlSession.SqlSession;
import com.lagou.sqlSession.SqlSessionFactory;
import com.lagou.sqlSession.SqlSessionFactoryBuilder;
import org.dom4j.DocumentException;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;

public class IPersistenceTest {

    @Test
    public void test() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //调用
        User user = new User();
        user.setId(1);
        user.setUsername("Bruce");
//        User user1 = sqlSession.selectOne("user.selectOne",user);
//        System.out.println(user1.getUsername());
//        List<User> users = sqlSession.selectList("user.selectList");
//        for (User user1 : users) {
//            System.out.println(user1.getUsername());
//          }
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        User user1 = userDao.findByCondition(user);
        System.out.println(user1);
        List<User> allUsers = userDao.findAll();
        for (User allUser : allUsers) {
            System.out.println(allUser.getUsername());
        }
    }

    @Test
    public void testUpdate() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //调用
        User user = new User();
        user.setId(1);
        user.setUsername("Lily");
//        User user1 = sqlSession.selectOne("user.selectOne",user);
//        System.out.println(user1.getUsername());
//        List<User> users = sqlSession.selectList("user.selectList");
//        for (User user1 : users) {
//            System.out.println(user1.getUsername());
//          }
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        int res = userDao.update(user);
        System.out.println(res);
    }

    @Test
    public void testDetele() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        User user = new User();
        user.setId(1);
        int res = userDao.delete(user);
        System.out.println(res);
    }

}
