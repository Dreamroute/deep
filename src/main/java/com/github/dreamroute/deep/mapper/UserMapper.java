package com.github.dreamroute.deep.mapper;

import com.github.dreamroute.deep.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    int insert(@Param("user") User user);

    User findByNameAndPassword(@Param("name") String name, @Param("password") String password);

}
