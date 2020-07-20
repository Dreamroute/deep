package com.github.dreamroute.deep.config;

import com.github.dreamroute.deep.domain.User;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class UserFactoryBean implements FactoryBean<User> {
    @Override
    public User getObject() throws Exception {
        return User.builder().id(1111L).build();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
