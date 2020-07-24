package com.github.dreamroute.deep.mapper;

import com.github.dreamroute.deep.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    int insert(@Param("user") User user);

}
