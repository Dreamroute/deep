package com.github.dreamroute.deep.misc;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import static cn.hutool.core.util.RandomUtil.randomInt;

/**
 * @author w.dehai
 */
public class CyclicBarrierTest {

    @Test
    void clTest() throws Exception {
        CountDownLatch cdl = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                    cdl.countDown();
                    System.err.println(cdl.getCount());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        cdl.await();
        System.err.println("end.");
    }

    @Test
    void barrierTest() throws Exception {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
            System.err.println("beer.");
        });

        for (int i = 0; i < 5; i++) {
            new Thread(new Worker(i, cyclicBarrier)).start();
        }

        TimeUnit.SECONDS.sleep(Long.MAX_VALUE);
    }

    static class Worker implements Runnable {
        final int id;
        final CyclicBarrier cyclicBarrier;

        public Worker(int id, CyclicBarrier cyclicBarrier) {
            this.id = id;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            int time = randomInt(3, 6);
            try {
                TimeUnit.SECONDS.sleep(time);
                System.err.println(this.id + " start to run!, 此线程执行了" + time + "秒.");
                cyclicBarrier.await();
                System.err.println(this.id + " 执行完成了.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    void semaphoreTest() throws Exception {
        Semaphore semaphore = new Semaphore(5);
        int ns = 100;
        List<Thread> threads = new ArrayList<>(ns);
        for (int i = 0; i < ns; i++) {
            threads.add(new Thread(new Wk(semaphore, i)));
        }
        ExecutorService pool = Executors.newFixedThreadPool(100);
        Collections.shuffle(threads);
        threads.forEach(pool::submit);
        TimeUnit.SECONDS.sleep(Long.MAX_VALUE);
    }

    @AllArgsConstructor
    static class Wk implements Runnable {
        private Semaphore semaphore;
        private int id;

        @Override
        public void run() {
            try {
                semaphore.acquire();
                int time = randomInt(0, 5);
                TimeUnit.SECONDS.sleep(time);
                System.err.println("线程id为:" + this.id + "的线程获得许可，并且运行了" + time + "秒.");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }
}
