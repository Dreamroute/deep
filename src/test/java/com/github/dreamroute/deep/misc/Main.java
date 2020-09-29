package com.github.dreamroute.deep.misc;

/**
 * @author w.dehai
 */

import org.junit.jupiter.api.Test;

import java.util.Properties;

import static java.lang.String.format;

public class Main {
    @Test
    void mmmm() {
        Properties properties = System.getProperties();
        properties.forEach((k, v) -> System.err.println(k + ": " + v));

        String result = format("bdfint is a %s and %d time.", "big", 5);
        System.err.println(result);
    }
}
