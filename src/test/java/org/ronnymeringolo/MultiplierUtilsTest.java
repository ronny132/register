package org.ronnymeringolo;

import org.junit.Before;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import org.ronnymeringolo.io.exceptions.TooManyDigitsException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;

/**
 * Unit test for simple App.
 */
public class MultiplierUtilsTest {
    @Before
    public void setUp() {
    }

    @Test
    public void testSumArray() {
        // Given
        int[] a = new int[]{3, 6, 2, 8, 8, 0};
        int[] b = new int[]{3, 6, 2, 8, 8, 0};
        int[] expected = new int[]{3, 9, 9, 1, 6, 8, 0};

        // When
        int[] result = MultiplierUtils.sumArray(a, b, 1);

        // Then
        assertArrayEquals(expected, result);
    }

    @Test
    public void shouldThrowTooManyDigitsException() {
        // Given
        int num = 20;
        int times = 2;

        // When
        ThrowingRunnable result = () -> MultiplierUtils.sumManyTimes(num, times);

        // Then
        assertThrows(TooManyDigitsException.class, result);
    }
}
