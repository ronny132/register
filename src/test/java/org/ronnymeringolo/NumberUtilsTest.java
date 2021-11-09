package org.ronnymeringolo;

import org.junit.Before;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import org.ronnymeringolo.io.exceptions.NegativeNumbersException;

import java.math.BigInteger;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class NumberUtilsTest {
    @Before
    public void setUp() {
    }

    private BigInteger realFactorial(int x) {
        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= x; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    @Test
    public void testToDigitArray() {
        // Given
        int[] expected = new int[]{2, 4};

        // When
        int[] result1 = NumberUtils.toDigitArray(new BigInteger("24"));
        int[] result2 = NumberUtils.toDigitArray(24);

        // Then
        assertEquals(result1.length, 2);
        assertArrayEquals(expected, result1);
        assertEquals(result2.length, 2);
        assertArrayEquals(expected, result2);
    }

    @Test
    public void testFactorialOk() {
        // Given
        BigInteger[] expected = new BigInteger[100];
        for (int i = 1; i <= 100; i++) {
            expected[i - 1] = realFactorial(i);
        }

        // When
        BigInteger[] result = new BigInteger[100];
        for (int i = 1; i <= 100; i++) {
            result[i - 1] = NumberUtils.factorial(i);
        }

        // Then
        assertArrayEquals(expected, result);
    }

    @Test
    public void testMultiply1() {
        // Given
        BigInteger expected = new BigInteger("362880").multiply(new BigInteger("11"));

        // When
        BigInteger result = NumberUtils.multiply(11, new BigInteger("362880"));

        // Then
        assertEquals(expected, result);
    }

    @Test
    public void testMultiply2() {
        // Given
        BigInteger expected = new BigInteger("362880").multiply(new BigInteger("11"));

        // When
        BigInteger result = NumberUtils.multiply(362880, new BigInteger("11"));

        // Then
        assertEquals(expected, result);
    }

    @Test
    public void testFactorialKo() {
        assertNotEquals(BigInteger.ONE, NumberUtils.factorial(3));
    }

    @Test
    public void shouldThrowNegativeNumbersException() {
        // Given
        int x = 20;
        BigInteger y = new BigInteger("-5");

        // When
        ThrowingRunnable result = () -> NumberUtils.multiply(x, y);

        // Then
        assertThrows(NegativeNumbersException.class, result);
    }

    @Test
    public void shouldThrowNullPointerException() {
        // Given
        int x = 20;
        BigInteger y = null;

        // When
        ThrowingRunnable result = () -> NumberUtils.multiply(x, y);

        // Then
        assertThrows(NullPointerException.class, result);
    }
}
