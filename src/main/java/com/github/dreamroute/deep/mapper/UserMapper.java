package com.github.dreamroute.deep.mapper;

import com.github.dreamroute.deep.domain.User;

import java.util.List;

public interface UserMapper {

    int insert(User user);

    User findByNameAndPassword(String name, String password);

    List<User> findByName(String name);

    User selectById(Long id);

}
