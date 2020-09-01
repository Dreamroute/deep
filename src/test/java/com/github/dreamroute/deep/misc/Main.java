package com.github.dreamroute.deep.misc;

/**
 * @author w.dehai
 */

import java.util.Arrays;

/**
 * 求和
 *
 * @author w.dehai
 */
public class Main {
    public static void main(String[] args) {
        System.err.println(new Main().sum(3, new int[][] {{1, 2, 3}, {2, 1, 3}, {3, 2, 1}}));
    }

    public int sum(int n, int[][] data) {
        int result = 0;
        if (n > 1000) {
            throw new IllegalArgumentException("arg 'n' must greater than equal 1000.");
        }
        for (int i=0; i<n; i++) {
            result += Arrays.stream(data[i]).sum();
        }
        return result;
    }

}
