package com.github.dreamroute.deep.misc;

import com.github.dreamroute.deep.domain.User;
import com.github.dreamroute.deep.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insertTest() {
        int result = userMapper.insert(User.builder().name("ww22w").password("322").version(1L).build());
        System.err.println(result);
    }

}
