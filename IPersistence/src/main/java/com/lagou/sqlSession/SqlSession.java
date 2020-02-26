package com.lagou.sqlSession;

import java.util.List;

public interface SqlSession {

    //查询所有
    public <E> List<E> selectList(String statementid,Object... params) throws Exception;

    //查询单个
    public <T> T selectOne(String statementid,Object... params) throws Exception;

    //修改用户
    public int updateOne(String statementid,Object... params) throws Exception;

    //删除用户
    public int deleteOne(String statementid,Object... params) throws Exception;

    //为Dao接口生成代理实现类
    public <T> T getMapper(Class<?> mapperClass);
}
