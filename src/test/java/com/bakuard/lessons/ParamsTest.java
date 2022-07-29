package com.bakuard.lessons;

import com.bakuard.lessons.exception.UnknownParameterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

class ParamsTest {

    @Test
    @DisplayName("""
            get(name):
             there is not parameter with such name
             => exception
            """)
    public void get1() {
        Params params = new Params().
                put("1", 1).
                put("2", "some string").
                put("3", new Object());

        Assertions.assertThrows(UnknownParameterException.class, () -> params.get("4"));
    }

    @Test
    @DisplayName("""
            put(name, value):
             there is not parameters with such name
             => return new object with this parameter
            """)
    public void put1() {
        Params params = new Params().
                put("1", 1).
                put("2", "some string").
                put("3", new BigInteger("12"));

        Params actual = params.put("4", 4.0);

        Assertions.assertAll(
                () -> Assertions.assertNotSame(params, actual),
                () -> Assertions.assertEquals(4.0, actual.get("4")),
                () -> Assertions.assertEquals(1, (int) actual.get("1")),
                () -> Assertions.assertEquals("some string", actual.get("2")),
                () -> Assertions.assertEquals(new BigInteger("12"), actual.get("3"))
        );
    }

    @Test
    @DisplayName("""
            put(name, value):
             there is not parameters with such value
             => return new object with this parameter
            """)
    public void put2() {
        Params params = new Params().
                put("1", 1).
                put("2", "some string").
                put("3", new BigInteger("12"));

        Params actual = params.put("3", BigInteger.TWO);

        Assertions.assertAll(
                () -> Assertions.assertNotSame(params, actual),
                () -> Assertions.assertEquals(1, (int) actual.get("1")),
                () -> Assertions.assertEquals("some string", actual.get("2")),
                () -> Assertions.assertEquals(BigInteger.TWO, actual.get("3"))
        );
    }

    @Test
    @DisplayName("""
            put(name, value):
             there is parameters with such name and value
             => return the same object
            """)
    public void put3() {
        Params expected = new Params().
                put("1", 1).
                put("2", "some string").
                put("3", BigInteger.TWO);

        Params actual = expected.put("3", BigInteger.TWO);

        Assertions.assertSame(expected, actual);
    }

    @Test
    @DisplayName("""
            remove(name):
             there is parameter with such name
             => return new object without this parameter
            """)
    public void remove1() {
        Params params = new Params().
                put("1", 1).
                put("2", "some string").
                put("3", BigInteger.TWO);

        Params actual = params.remove("3");

        Assertions.assertAll(
                () -> Assertions.assertNotSame(params, actual),
                () -> Assertions.assertEquals(1, (int) actual.get("1")),
                () -> Assertions.assertEquals("some string", actual.get("2")),
                () -> Assertions.assertThrows(UnknownParameterException.class, () -> actual.get("3"))
        );
    }

    @Test
    @DisplayName("""
            remove(name):
             there is not parameter with such name
             => return same object
            """)
    public void remove2() {
        Params expected = new Params().
                put("1", 1).
                put("2", "some string").
                put("3", BigInteger.TWO);

        Params actual = expected.remove("4");

        Assertions.assertSame(expected, actual);
    }

}