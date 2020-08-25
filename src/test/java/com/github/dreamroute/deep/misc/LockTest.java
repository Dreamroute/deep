package com.github.dreamroute.deep.misc;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author w.dehai
 */
public class LockTest {

    @Test
    void reLockTest() {
        ReentrantLock lock = new ReentrantLock(true);
        lock.lock();
        try {
            System.err.println("lock.");
        } finally {
            lock.unlock();
        }
    }

    @Test
    void linkedTest() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        System.err.println(list);
    }

    @Test
    void queueTest() {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(3);
        queue.offer("A");
        queue.offer("B");
        queue.offer("C");
        queue.offer("D");
        System.err.println(queue);

        String firstName = "wang";
        String lastName = "dehai";
        String name = firstName.concat(lastName);
        System.err.println(name);

    }

    static int count = 0;

    @Test
    void tTest() {
        Thread[] threads = new Thread[1000];
        for (int i=0; i<threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j=0; j<10000; j++) count++;
            });
        }
        for (Thread thread : threads) {
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.err.println(count);
    }

    @Test
    void mmmm() {
        Integer i1 = new Integer(2);
        Integer i2 = new Integer(5);
        change(i1, i2);
        System.err.println(i1);
        System.err.println(i2);
        System.err.println("end.");
    }

    static void change(Integer i1, Integer i2) {
        Integer t = i1;
        i1 = i2;
        i2 = t;
    }

}
