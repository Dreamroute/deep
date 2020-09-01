package com.github.dreamroute.deep.misc;

import com.github.dreamroute.deep.mapper.UserMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author w.dehai
 */
public class MiscTest {

    @Test
    void mmmmmmmm() throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
//            throw new IllegalArgumentException("参数错误!");
            return "w.dehai";
        });

//        CompletableFuture<Integer> handle = future.handle((v, e) -> e == null ? 0 : 1);
//        System.err.println(handle.get());

        Integer integer = future.thenApply(v -> {
            System.err.println(v);
            return 5;
        }).get();
        System.err.println(integer);

        CompletableFuture<Void> voidCompletableFuture = future.thenAccept(e -> {
            System.err.println(e);
        });
        Void aVoid = voidCompletableFuture.get();
        System.err.println(aVoid);

        CompletableFuture<Void> voidCompletableFuture1 = future.thenAcceptBoth(CompletableFuture.supplyAsync(() -> "Dream"), (before, after) -> {
            System.err.println(before);
            System.err.println(after);
        });
        System.err.println(voidCompletableFuture.get());

        System.err.println("---");
        System.err.println(future.get());

        CompletableFuture<String> future1 = future.thenComposeAsync(e -> {
            System.err.println(e);
            return CompletableFuture.supplyAsync(() -> e);
        });
        System.err.println(future1.get());

        CompletableFuture.allOf(future, future1).get();

        System.err.println("--");
        System.err.println(future.get());
        CompletableFuture<Integer> integerCompletableFuture = future.thenApply(e -> {
            System.err.println(e);
            return 0;
        });
        System.err.println(integerCompletableFuture.get());

    }

    @Autowired
    private UserMapper userMapper;

    @Test
    @DisplayName("测试类")
    void ss() {
    }

    static void sleep(Integer seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
