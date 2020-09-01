package com.github.dreamroute.deep.misc.ss;

import com.github.dreamroute.deep.domain.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author w.dehai
 */
public class Main {
    public static void main(String[] args) {
        charUtil("1122333");
    }
    public static String charUtil(String cs) {
        if (cs == null || cs.length() == 0 || cs.length() > 100) {
            throw new IllegalArgumentException("arg error");
        }
        Map<String, Long> collect = Arrays.stream(cs.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.err.println(collect);

//        Arrays.stream(cs.split("")).collect(Collectors.collectingAndThen());
        List<Integer> list = Arrays.asList(1, 2, 2, 3, 3, 3);
        Map<Integer, Long> map = list.stream().collect(groupingBy(Function.identity(), Collectors.counting()));

        Optional<Integer> reduce = list.stream().reduce((result, element) -> {
            return Math.max(result, element);
        });

        List<User> users = new ArrayList<>(3);
        for (long i = 0; i < 3; i++) {
            users.add(User.builder().id(i).name(String.valueOf(i)).build());
        }

        Map<Long, User> userGroup = ofNullable(users).orElseGet(ArrayList::new).stream()
                .collect(groupingBy(User::getId, reducing(null, (result, element) -> element)));
        System.err.println(userGroup);

        Map<Long, User> userGroup2 = ofNullable(users).orElseGet(ArrayList::new).stream()
                .collect(toMap(User::getId, Function.identity()));
        System.err.println(userGroup2);

        System.err.println(userGroup.equals(userGroup2));

        return null;
    }


}

