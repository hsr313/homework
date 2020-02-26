package com.lagou.sqlSession;

import com.lagou.config.BoundSql;
import com.lagou.pojo.Configuration;
import com.lagou.pojo.MappedStatement;
import com.lagou.utils.GenericTokenParser;
import com.lagou.utils.ParameterMapping;
import com.lagou.utils.ParameterMappingTokenHandler;
import com.lagou.utils.TokenHandler;
import com.mysql.cj.protocol.Resultset;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleExecutor implements Executor {
    @Override
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception{
        //1.注册驱动，获取链接
        Connection connection = configuration.getDataSource().getConnection();

        //2.获取sql语句:select * from user where id = #{id} and username = #{username}
            //转换sql语句:select * from user where id = ? and username = ?，转换过程中还需要对#{}中的值进行解析存储
        String sql = mappedStatement.getSql();
        BoundSql boundSql = getBoundSql(sql);

        //3.获取预处理对象：prepareStatement
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());

        //4.设置参数
            //获取参数实体类全路径
        String parameterType = mappedStatement.getParamterType();
        Class<?> parameterTypeClass = getClassType(parameterType);
        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
        for(int i = 0;i<parameterMappingList.size();i++){
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            String content = parameterMapping.getContent();

            //反射
            Field declaredField = parameterTypeClass.getDeclaredField(content);
            //暴力访问
            declaredField.setAccessible(true);
            Object o = declaredField.get(params[0]);
            preparedStatement.setObject(i+1,o);

        }
        //5.执行sql
        ResultSet resultset = preparedStatement.executeQuery();
        String resultType = mappedStatement.getResultType();
        Class<?> resultTypeClass = getClassType(resultType);

        ArrayList<Object> objects = new ArrayList<>();
        //6.封装返回结果
        while(resultset.next()){
            Object o = resultTypeClass.newInstance();
            //元数据
            ResultSetMetaData resultSetMetaData = resultset.getMetaData();
            for(int i = 1;i<=resultSetMetaData.getColumnCount();i++){
                //字段名
                String columnName = resultSetMetaData.getColumnName(i);
                //字段值
                Object value = resultset.getObject(columnName);
                //使用反射或内省，根据数据库表和实体的对应关系，完成封装
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName,resultTypeClass);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(o,value);
            }
            objects.add(o);
        }


        return (List<E>) objects;
    }

    private Class<?> getClassType(String parameterType) throws ClassNotFoundException {
        if(parameterType!=null){
            Class<?> aClass = Class.forName(parameterType);
            return aClass;
        }
        return null;
    }

    /**
     * 完成对#{}的解析工作：1.将#{}使用？进行代替；2.解析出#{}里面的值进行存储
     * @param sql
     * @return
     */
    private BoundSql getBoundSql(String sql) {
        //标记处理类：配置标记解析器来完成对占位符的解析工作
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{","}",parameterMappingTokenHandler);
        //解析出来的sql
        String parseSql = genericTokenParser.parse(sql);
        //#{}解析出来的参数名称
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();
        BoundSql boundSql = new BoundSql(parseSql,parameterMappings);
        return boundSql;
    }

    private int executeSQL(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception {
        //1.注册驱动，获取链接
        Connection connection = configuration.getDataSource().getConnection();

        //2.获取sql语句:select * from user where id = #{id} and username = #{username}
        //转换sql语句:select * from user where id = ? and username = ?，转换过程中还需要对#{}中的值进行解析存储
        String sql = mappedStatement.getSql();
        BoundSql boundSql = getBoundSql(sql);

        //3.获取预处理对象：prepareStatement
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());

        //4.设置参数
        //获取参数实体类全路径
        String parameterType = mappedStatement.getParamterType();
        Class<?> parameterTypeClass = getClassType(parameterType);
        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
        for(int i = 0;i<parameterMappingList.size();i++){
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            String content = parameterMapping.getContent();

            //反射
            Field declaredField = parameterTypeClass.getDeclaredField(content);
            //暴力访问
            declaredField.setAccessible(true);
            Object o = declaredField.get(params[0]);
            preparedStatement.setObject(i+1,o);

        }
        //5.执行sql
        int result = preparedStatement.executeUpdate();
        return result;
    }

    public int update(Configuration configuration, MappedStatement mappedStatement, Object[] params) throws Exception{
        return executeSQL(configuration,mappedStatement,params);
    }
}
