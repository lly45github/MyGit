package com.ly.test;

import java.util.Map;
import java.util.stream.IntStream;

public class LambdaTest {

    public static void  main(String[] args ){
        int n = 10000;
        double fraction = 1.0 / n;
        Map<Integer,Double> result = IntStream.range(1,n).parallel().mapToObj(twoDiceThrows())
                .collect(groupingBy(side -> side,summingDouble(n -> fraction)));

    }
}
