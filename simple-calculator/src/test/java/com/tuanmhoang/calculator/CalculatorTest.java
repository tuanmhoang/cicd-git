package com.tuanmhoang.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

   @Test
   public void givenTwoNumber_whenSumIsCalled_thenReturnResult(){
      assertEquals(5, Calculator.sum(2,3));
   }

}
