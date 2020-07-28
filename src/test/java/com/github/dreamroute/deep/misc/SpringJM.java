package com.github.dreamroute.deep.misc;

import com.github.dreamroute.deep.domain.User;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SpringJM {

    @Test
    public void mm0() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinitionRegistry registry;
        new RootBeanDefinition(User.class);
        ApplicationContext container;
    }

    @Test
    public void mm1() throws NoSuchMethodException {
//        List<User> userList = new ArrayList<>();
//        Class<? extends List> aClass = userList.getClass();
//        Type genericSuperclass = aClass.getGenericSuperclass();
//        ParameterizedType pt = (ParameterizedType) genericSuperclass;
//        Type actualTypeArguments = pt.getActualTypeArguments()[0];
//        System.err.println(actualTypeArguments);
        Method getUserList = SpringJM.class.getDeclaredMethod("getUserList");
        Class<?> returnType = getUserList.getReturnType();
        System.err.println(returnType);
        Type genericReturnType = getUserList.getGenericReturnType();
        ParameterizedType pt = (ParameterizedType) genericReturnType;
        Type actualTypeArgument = pt.getActualTypeArguments()[0];
        System.err.println(actualTypeArgument);
        String typeName = actualTypeArgument.getTypeName();
        System.err.println(typeName);
        System.err.println(pt);
    }

    public List<User> getUserList() {
        return null;
    }

}
