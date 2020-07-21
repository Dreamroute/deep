package com.github.dreamroute.deep.misc;

import com.github.dreamroute.deep.domain.User;
import com.github.dreamroute.deep.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class MyBatisTest {

    @Test
    public void baseTest() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("config.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        // findByNameAndPassword
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User u = userMapper.findByNameAndPassword("www", "123");
        System.err.println(u);

//        User user = User.builder().name("www").password("123456").version(1L).build();
//        int result = sqlSession.insert("com.github.dreamroute.deep.mapper.UserMapper.insert", user);
//        int result = sqlSession.getMapper(UserMapper.class).insert(user);
//        System.err.println(result);
    }

    User findByNameAndPassword(String name, String passwrod) {
        return null;
    }
}
