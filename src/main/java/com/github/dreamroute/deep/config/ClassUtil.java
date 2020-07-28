package com.github.dreamroute.deep.config;

import com.github.dreamroute.deep.mapper.UserMapper;
import sun.reflect.generics.tree.ReturnType;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 查询接口的方法的返回值类型（包括普通类型和泛型类型），由于只需要容器初始化的时候执行，所以不需要缓存
 *
 * @author w.dehai
 */
public class ClassUtil {

    private ClassUtil() {}

    /**
     * 获取方法返回值类型
     *
     * @param method 方法
     * @return 返回值类型
     */
    public static String getReturnType(Method method) {
        Class<?> returnType = method.getReturnType();

        // 普通类型
        if (returnType != List.class)
            return returnType.getName();

        // List类型
        Type genericReturnType = method.getGenericReturnType();
        ParameterizedType pt = (ParameterizedType) genericReturnType;
        Type actualTypeArgument = pt.getActualTypeArguments()[0];
        String typeName = actualTypeArgument.getTypeName();
        return typeName;
    }

    /**
     * 返回Mapper接口的findBy开头的方法
     *
     * @param interfaceCls Mapper接口
     * @return  findBy开头的方法的方法名字
     */
    public static List<String> getSpecialMethods(Class<?> interfaceCls) {
        Method[] methods = interfaceCls.getMethods();
        return Arrays.stream(methods).map(Method::getName).filter(name -> name.startsWith("findBy")).collect(Collectors.toList());
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Method method1 = UserMapper.class.getDeclaredMethod("findByNameAndPassword", String.class, String.class);
        Method method2 = UserMapper.class.getDeclaredMethod("findByName", String.class);
        String type1 = getReturnType(method1);
        String type2 = getReturnType(method2);
        System.err.println(type1);
        System.err.println(type2);

        List<String> names = getSpecialMethods(UserMapper.class);
        System.err.println(names);
    }

}
