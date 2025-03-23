package org.wadhome.leetcode.stack;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.wadhome.leetcode.stack.BasicCalculator.calculate;

public class BasicCalculatorTest {

    @Test
    public void testSimpleNumber() {
        assertEquals(2, calculate(" 2"));
        assertEquals(691, calculate("691"));
    }

    @Test
    public void testSimpleNegNumber() {
        assertEquals(-3, calculate("-3"));
        assertEquals(-321, calculate("-321"));
    }

    @Test
    public void testSimpleParens() {
        assertEquals(3, calculate("(3)"));
        assertEquals(321, calculate("(321)"));
        assertEquals(321, calculate("(((321)))"));
    }

    @Test
    public void testSimpleParensWithNeg() {
        assertEquals(-3, calculate("(-3)"));
        assertEquals(-3, calculate("-(3)"));
        assertEquals(3, calculate("-(-3)"));
        assertEquals(-321, calculate("(-321)"));
        assertEquals(-321, calculate("-(321)"));
        assertEquals(321, calculate("-(-321)"));
    }

    @Test
    public void test1() {
        assertEquals(2, calculate("1 + 1"));
    }

    @Test
    public void test2() {
        assertEquals(3, calculate(" 2-1 + 2 "));
    }

    @Test
    public void test3() {
        assertEquals(23, calculate("(1+(4+5+2)-3)+(6+8)"));
    }

    @Test
    public void scratch() {
        List<String> list = new ArrayList<>();
        list.add("abc");
        list.add("def");
        list.add("ghi");
        System.out.println(list.stream().map(String::toUpperCase).collect(Collectors.joining(",")));
    }

}
