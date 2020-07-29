package com.github.dreamroute.deep.mapper;

import com.github.dreamroute.deep.domain.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {

    User findByNameAndPassword(String name, String password);

    List<User> findByName(String name);

    User selectById(Long id);

    List<User> findByNameAndPasswordLike(String name, String password);

    List<User> findByVersionOrderByIdDesc(Long version);

    List<User> findByNameLikeAndVersionOrPasswordOrderById(String name, Long version, String password);

}
