package com.github.dreamroute.deep.misc;

import com.github.dreamroute.deep.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;


public class SpringJM {

    @Test
    public void mm0() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinitionRegistry registry;
        new RootBeanDefinition(User.class);
        ApplicationContext container;

    }

}
