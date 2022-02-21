package com.tuanmhoang.calculator;

import java.math.BigInteger;
import java.util.function.BiFunction;

public class Calculator {

    public static BigInteger sum(Integer a, Integer b) {
        BiFunction<Integer, Integer, BigInteger> func = (x, y) -> BigInteger.valueOf(x + y);
        return func.apply(a, b);
    }

    public static BigInteger minus(Integer a, Integer b) {
        BiFunction<Integer, Integer, BigInteger> func = (x, y) -> BigInteger.valueOf(x - y);
        return func.apply(a, b);
    }

    public static BigInteger multiply(Integer a, Integer b) {
        BiFunction<Integer, Integer, BigInteger> func = (x, y) -> BigInteger.valueOf(x * y);
        return func.apply(a, b);
    }

}
