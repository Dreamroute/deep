package com.github.dreamroute.deep.misc;

import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;
import sun.rmi.transport.StreamRemoteCall;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class StopWatchTest {

    @Test
    public void swTest() throws InterruptedException {
        StopWatch sw = new StopWatch();
        sw.start();
        Thread.sleep(2 * 1000);
        sw.stop();
        System.err.println(sw.getTotalTimeSeconds());
    }

    @Test
    public void optionalTest() {
        String name = "w.dehai";
        Optional<String> op = Optional.ofNullable(name);
        Optional<String> s = op.filter(v -> Objects.equals("w.dehai", v));
        s.ifPresent(System.err::println);

        op.map(v -> v + "111").ifPresent(System.err::println);
    }

    @Test
    public void streamTest() {
        Stream<Integer> stream = Stream.of(1, 2, 3);
//        User : id: name
        stream.map()
    }


}
