package com.github.dreamroute.deep.misc;

/**
 * @author w.dehai
 */

import org.junit.jupiter.api.Test;

/**
 * 线程不安全
 */
public class NonThreadSafeTest {

    @Test
    public void nonSafeTest() {
        Resouce resouce = new Resouce();
        Thread add = new Thread(new Run1(resouce), "add");
        Thread minus = new Thread(new Run2(resouce), "minus");
        add.start();
        minus.start();

        try {
            Thread.sleep(10000 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    static class Run1 implements Runnable {
        private Resouce resouce;

        public Run1(Resouce resouce) {
            this.resouce = resouce;
        }

        public void run() {
            for (int i = 0; i < 10000; i++) {
                resouce.add();
                System.err.println(resouce.data);
            }

        }
    }

    static class Run2 implements Runnable {
        private Resouce resouce;

        public Run2(Resouce resouce) {
            this.resouce = resouce;
        }

        public void run() {
            for (int i = 0; i < 10000; i++) {
                resouce.minus();
                System.err.println(resouce.data);
            }
        }
    }

    static class Resouce {
        public int data = 0;

        public synchronized void add() {
            data++;
        }

        public synchronized void minus() {
            data--;
        }
    }
}