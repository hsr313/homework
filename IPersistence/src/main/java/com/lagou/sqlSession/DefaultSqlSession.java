package com.lagou.sqlSession;

import com.lagou.pojo.Configuration;
import com.lagou.pojo.MappedStatement;

import java.lang.reflect.*;
import java.util.List;

public class DefaultSqlSession implements SqlSession {
    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> selectList(String statementid, Object... params) throws Exception {
        //将要去完成对simpleExecutor里的query方法的调用
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementid);
        List<Object> list = simpleExecutor.query(configuration,mappedStatement,params);
        return (List<E>) list;
    }

    @Override
    public <T> T selectOne(String statementid, Object... params) throws Exception {
        List<Object> objects = selectList(statementid,params);
        if(objects.size() == 1){
            return (T) objects.get(0);
        }else{
            throw new RuntimeException("查询结果为空或者返回结果过多");
        }

    }

    @Override
    public int updateOne(String statementid, Object... params) throws Exception {
        //将要去完成对simpleExecutor里的update方法的调用
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementid);
        int res = simpleExecutor.update(configuration,mappedStatement,params);
        return res;
    }

    @Override
    public int deleteOne(String statementid, Object... params) throws Exception {
        return updateOne(statementid,params);
    }

    @Override
    public <T> T getMapper(Class<?> mapperClass) {
        //使用JDK动态代理来为Dao接口生成代理对象并返回
        Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] args) throws Throwable {
                //底层都还是执行JDBC代码；根据情况不同，采用selectOne或selectList
                //准备参数 1：statementid：sql语句的唯一标识：namespace.id = 接口全限定名.方法名
                //获取方法名
                String methodName = method.getName();
                //获取接口全限定名
                String className = method.getDeclaringClass().getName();

                String statementId = className + "." + methodName;

                //准备参数2：params:args
                //获取被调用方法的返回值类型
                Type genericReturnType = method.getGenericReturnType();

                //获取sql
                String sql = configuration.getMappedStatementMap().get(statementId).getSql();

                //判断是否进行了泛型类型参数化
                if(genericReturnType instanceof ParameterizedType){
                    List<Object> objects = selectList(statementId,args);
                    return objects;
                }
                if(sql.startsWith("update")){//判断sql是否属于改删
                    return updateOne(statementId,args);
                }
                if(sql.startsWith("delete")){
                    return deleteOne(statementId,args);
                }
                return selectOne(statementId,args);

            }
        });
        return (T) proxyInstance;
    }
}
