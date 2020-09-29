package com.github.dreamroute.deep.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.RetryNTimes;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

import static cn.hutool.core.util.RandomUtil.randomInt;
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

    @Test
    void leaderElectionTest() throws Exception {
        LeaderSelectorListener listener = new LeaderSelectorListenerAdapter() {
            @Override
            public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                System.err.println("I'm leader.");
            }
        };

        LeaderSelector selector = new LeaderSelector(client, "/leader", listener);
        selector.autoRequeue();
        selector.start();
        TimeUnit.SECONDS.sleep(1000);

        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        ReadLock readLock = rwLock.readLock();
        WriteLock writeLock = rwLock.writeLock();

        writeLock.lock();
        try {

        } finally {
            writeLock.unlock();
        }
    }

}













