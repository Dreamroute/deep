package com.github.dreamroute.deep.misc;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.supplyAsync;

/**
 * 求和
 *
 * @author w.dehai
 */
public class TopicTest {

    @Test
    void cpTest() throws Exception {
        CompletableFuture<String> future = supplyAsync(() -> "w.dehai")
                .thenApply(name -> name + "-bdfint")
                .whenComplete((result, e) -> {
                    System.err.println(result);
                    System.err.println(e);
                });
        String res = future.get();
    }

}
