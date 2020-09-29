package com.github.dreamroute.deep.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.RetryNTimes;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.apache.zookeeper.CreateMode.EPHEMERAL_SEQUENTIAL;

/**
 * @author w.dehai
 */
public class CuratorTest {

    private static CuratorFramework client;

    @BeforeAll
    static void init() {
        client = CuratorFrameworkFactory.newClient("10.82.12.63:2181", new RetryNTimes(3, 5 * 1000));
        client.start();
    }

    @AfterAll
    static void end() {
        client.close();
    }

    @Test
    void createTest() throws Exception {
        String result = client.create().withMode(EPHEMERAL_SEQUENTIAL).forPath("/fuy1m", "w.dehai".getBytes());
        System.err.println(result);
    }

    @Test
    void lockTest() throws Exception {
        InterProcessMutex lock = new InterProcessMutex(client, "/lock");
        lock.acquire();
        try {
            System.err.println("w.dehai");
        } finally {
            lock.release();
        }
    }

    static class Resoruce {
        int num = 0;
    }

}












