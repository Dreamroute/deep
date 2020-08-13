package com.github.dreamroute.deep.misc;

import com.github.dreamroute.deep.domain.User;
import org.junit.jupiter.api.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author w.dehai
 */
public class JucTest {

    @Test
    void unsafeTest() throws NoSuchFieldException, IllegalAccessException {
        new Tests().tests11();
    }

    @Test
    void atomicIntegerTest() {
        AtomicInteger count = new AtomicInteger();
        for (int i = 0; i < 10; i++) {
            System.err.println(count.getAndIncrement());
        }
    }

    private static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            return null;
        }
    }


}

class Tests {

    private int count = 0;

    public void tests11() throws NoSuchFieldException, IllegalAccessException {
        // 获取unsafe实例
        Class klass = Unsafe.class;
        Field field = klass.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);

        // 获取count域的Field
        Class unsafeTestClass = Tests.class;
        Field fieldCount = unsafeTestClass.getDeclaredField("count");
        fieldCount.setAccessible(true);

        // 计算count的内存偏移量
        long countOffset = (int) unsafe.objectFieldOffset(fieldCount);
        System.out.println(countOffset);

        // 原子性的更新指定偏移量的值（将count的值修改为3）
        unsafe.compareAndSwapInt(this, countOffset, count, 3);

        // 获取指定偏移量的int值
        System.out.println(unsafe.getInt(this, countOffset));
    }
}