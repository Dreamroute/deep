package com.github.dreamroute.deep.misc;

import com.github.dreamroute.deep.domain.User;
import com.github.dreamroute.deep.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@SpringBootTest
public class FastMapperTest {

    @Autowired
    private UserMapper userMapper;

//    @Test
//    public void insertTest() {
//        User user = User.builder()
//                .name("wwwdd")
//                .password("mm")
//                .version(1L)
//                .build();
//        int result = userMapper.insert(user);
//        System.err.println(result);
//    }

    @Test
    public void findByNameAndPasswordTest() {
        User user = userMapper.findByNameAndPassword("w.dehai", "123");
        System.err.println(user);
    }

    @Test
    void findByNameTest() {
        List<User> users = userMapper.findByName("w.dehai");
        System.err.println(users);
    }

    @Test
    void findByNameAndPasswordLikeTest() {
        List<User> users = userMapper.findByNameAndPasswordLike("w.dehai", "23");
        System.err.println(users);
    }

    @Test
    void findByVersionOrderByIdTest() {
        List<User> users = userMapper.findByVersionOrderByIdDesc(1L);
        if (users != null) {
            users.stream().map(User::getId).forEach(System.err::println);
        }
    }

    @Test
    void findByNameLikeAndVersionOrPasswordOrderByIdTest() {
        List<User> users = userMapper.findByNameLikeAndVersionOrPasswordOrderById("w.dehai", 1L, "123");
        System.err.println(users);
    }

    @Test
    void mm() {
        Example e = new Example(User.class);
        e.createCriteria()
                .andLike("name", "w.dehai");
        List<User> userList = userMapper.selectByExample(e);
        System.err.println(userList);
    }
}
