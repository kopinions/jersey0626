package org.thoughtworks.com.provider;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatis {
    SqlSessionFactory sqlSessionFactory;

    public MyBatis() throws IOException {

        InputStream resourceAsStream = Resources.getResourceAsStream("org/thoughtworks/com/provider/conig.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
    }

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}


