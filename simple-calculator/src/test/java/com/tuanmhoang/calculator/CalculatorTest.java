package com.tuanmhoang.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    @Test
    public void givenTwoNumber_whenSumIsCalled_thenReturnResult() {
        assertEquals(5, Calculator.sum(2, 3));
    }

    @Test
    public void givenTwoNumber_whenMinusIsCalled_thenReturnResult() {
        assertEquals(-1, Calculator.minus(2, 3));
    }

    @Test
    public void givenTwoNumber_whenMultiplyIsCalled_thenReturnResult() {
        assertEquals(6, Calculator.minus(2, 3));
    }

}
