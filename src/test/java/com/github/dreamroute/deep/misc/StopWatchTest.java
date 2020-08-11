package com.github.dreamroute.deep.misc;

import com.github.dreamroute.deep.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

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

        String mm = "w.dehai";
        User user = new User();
        Long userId = Optional.ofNullable(user).map(User::getId).orElseThrow(NullPointerException::new);
        System.err.println(userId);
    }

    @Test
    public void streamTest() {
        Object result = Optional.ofNullable(null).orElseThrow(RuntimeException::new);
        System.err.println(result);
    }

    @Test
    public void regexTest() {

        String str = "PasswordNotLike";

    }


}
