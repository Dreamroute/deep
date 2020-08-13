package com.github.dreamroute.deep.misc;

import com.github.dreamroute.deep.domain.User;
import com.github.dreamroute.deep.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.List;

@SpringBootTest
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insertTest() {
        int result = userMapper.insert(User.builder().name("ww22w").password("322").version(1L).build());
        System.err.println(result);
    }

    @Test
    void mapperTest() {
        Example e = new Example(User.class);
        e.orderBy("id").desc();
        Criteria criteria = e.createCriteria().andBetween("version", 2L, 4L);
        List<User> users = userMapper.selectByExample(e);
        System.err.println(users);
    }

}
