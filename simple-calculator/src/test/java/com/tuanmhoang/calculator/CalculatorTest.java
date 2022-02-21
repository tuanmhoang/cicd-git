package com.tuanmhoang.calculator;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    @Test
    public void givenTwoNumber_whenSumIsCalled_thenReturnResult() {
        assertEquals(BigInteger.valueOf(5), Calculator.sum(2, 3));
    }

    @Test
    public void givenTwoNumber_whenMinusIsCalled_thenReturnResult() {
        assertEquals(BigInteger.valueOf(-1), Calculator.minus(2, 3));
    }

    @Test
    public void givenTwoNumber_whenMultiplyIsCalled_thenReturnResult() {
        assertEquals(BigInteger.valueOf(6), Calculator.multiply(2, 3));
    }

}
