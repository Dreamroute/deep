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
        User user = User.builder()
                .name("wwwdd")
                .password("mm")
                .version(1L)
                .build();
        int result = userMapper.insert(user);
        System.err.println(result);
    }

    @Test
    public void findByNameAndPasswordTest() {
        User user = userMapper.findByNameAndPassword("w.dehai", "1123");
        System.err.println(user);
    }

}
