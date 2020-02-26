package com.lagou.sqlSession;

import com.lagou.config.XMLConfigBuilder;
import com.lagou.pojo.Configuration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream in) throws DocumentException, PropertyVetoException {
        //第一：解析配置文件，将解析出的内容封装到Configration中
        XMLConfigBuilder xMLConfigBuilder = new XMLConfigBuilder();
        Configuration configuration = xMLConfigBuilder.parseConfig(in);
        //第二：创建sqlSessionFactory对象:工厂类：生产sqlSession会话对象
        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(configuration);
        return defaultSqlSessionFactory;
    }
}
