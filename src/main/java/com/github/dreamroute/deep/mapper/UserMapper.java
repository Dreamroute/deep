package com.github.dreamroute.deep.mapper;

import com.github.dreamroute.deep.domain.User;

public interface UserMapper {

    int insert(User user);

    User findByNameAndPassword(String name, String password);

    User selectById(Long id);

}
