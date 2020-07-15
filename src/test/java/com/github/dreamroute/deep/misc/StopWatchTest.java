package com.github.dreamroute.deep.misc;

import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

public class StopWatchTest {

    @Test
    public void swTest() throws InterruptedException {
        StopWatch sw = new StopWatch();
        sw.start();
        Thread.sleep(2 * 1000);
        sw.stop();
        System.err.println(sw.getTotalTimeSeconds());
    }

}
