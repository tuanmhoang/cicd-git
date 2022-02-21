package com.tuanmhoang.calculator;

import java.math.BigInteger;
import java.util.function.BiFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Calculator {

    private static Logger logger = LoggerFactory.getLogger(Calculator.class);

    public static BigInteger sum(Integer a, Integer b) {
        BiFunction<Integer, Integer, BigInteger> func = (x, y) -> BigInteger.valueOf(x + y);
        BigInteger result = func.apply(a, b);
        logger.info("Result = {}", result);
        return result;
    }

    public static BigInteger minus(Integer a, Integer b) {
        BiFunction<Integer, Integer, BigInteger> func = (x, y) -> BigInteger.valueOf(x - y);
        BigInteger result = func.apply(a, b);
        logger.info("Result = {}", result);
        return result;
    }

    public static BigInteger multiply(Integer a, Integer b) {
        BiFunction<Integer, Integer, BigInteger> func = (x, y) -> BigInteger.valueOf(x * y);
        BigInteger result = func.apply(a, b);
        logger.info("Result = {}", result);
        return result;
    }

}
