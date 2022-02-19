package com.tuanmhoang.calculator;

import java.util.function.BiFunction;

public class Calculator {

    public static Integer sum(Integer a, Integer b) {
        BiFunction<Integer, Integer, Integer> func = (x, y) -> x + y;
        return func.apply(a, b);
    }

    public static Integer minus(Integer a, Integer b) {
        BiFunction<Integer, Integer, Integer> func = (x, y) -> x - y;
        return func.apply(a, b);
    }

    public static Integer multiply(Integer a, Integer b) {
        BiFunction<Integer, Integer, Integer> func = (x, y) -> x * y;
        return func.apply(a, b);
    }

}
