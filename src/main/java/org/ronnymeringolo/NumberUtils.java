package org.ronnymeringolo;

import org.ronnymeringolo.io.exceptions.NegativeNumbersException;

import java.math.BigInteger;

/**
 * This utility class provides methods to calculate products and factorials of positive integers using only sum operations.
 */
public class NumberUtils {

    /**
     * @param number a positive integer number
     * @return the number of digits of the input number
     */
    public static int digitCount(BigInteger number) {
        if (number == null) throw new NullPointerException();
        if (number.compareTo(BigInteger.ZERO) < 0)
            throw new NegativeNumbersException("Please only input positive integers");
        return number.toString().length();
    }

    /**
     * @param number a positive integer number
     * @return the number of digits of the input number
     */
    public static int digitCount(int number) {
        if (number < 0 )
            throw new NegativeNumbersException("Please only input positive integers");
        return String.valueOf(number).length();
    }

    /**
     * Method to calculate the factorial of a positive integer number.
     * The factorial of a number is defined as the product of all positive integers smaller or equals to the input number.
     * @param x a positive integer number
     * @return the factorial of the input number
     */
    public static BigInteger factorial(int x) {
        if (x < 0 )
            throw new NegativeNumbersException("Please only input positive integers");
        if (x <= 1) return BigInteger.ONE;
        return multiply(x, factorial(x - 1));
    }

    /**
     * Method to calculate the product of two positive integer numbers.
     * @param x a positive integer number
     * @param y another positive integer number
     * @return the product of x * y
     */
    public static BigInteger multiply(int x, BigInteger y) {
        if (y == null) throw new NullPointerException();
        if (x < 0 || y.compareTo(BigInteger.ZERO) < 0)
            throw new NegativeNumbersException("You can only multiply positive integers");
        int[] xArray = NumberUtils.toDigitArray(x);
        int[] yArray = NumberUtils.toDigitArray(y);
        int[] resArray = MultiplierUtils.multiply(xArray, yArray);

        String resToString = NumberUtils.toBigIntegerString(resArray);
        return new BigInteger(resToString);
    }

    /**
     * Explodes the input number into an array of its digits.
     * For example: the number 925 will be exploded into {9, 2, 5}
     * @param number a positive integer number
     * @return an array of digits
     */
    public static int[] toDigitArray(int number) {
        if (number < 0 )
            throw new NegativeNumbersException("Please only input positive integers");
        int digitCount = digitCount(number);
        int[] result = new int[digitCount];
        for (int i = result.length - 1; i >= 0; i--) {
            int digit = number % 10;
            number = number / 10;
            result[i] = digit;
        }
        return result;
    }

    /**
     * Explodes the input number into an array of its digits.
     * For example: the number 925 will be exploded into {9, 2, 5}
     * @param number a positive integer number
     * @return an array of digits
     */
    public static int[] toDigitArray(BigInteger number) {
        if (number == null) throw new NullPointerException();
        if (number.compareTo(BigInteger.ZERO) < 0)
            throw new NegativeNumbersException("Please only input positive integers");
        int digitCount = digitCount(number);
        int[] result = new int[digitCount];
        for (int i = result.length - 1; i >= 0; i--) {
            int digit = number.mod(BigInteger.TEN).intValue();
            number = number.divide(BigInteger.TEN);
            result[i] = digit;
        }
        return result;
    }

    private static String toBigIntegerString(int[] resArray) {
        StringBuilder sb = new StringBuilder();
        for (int i : resArray) {
            sb.append(i);
        }
        return sb.toString();
    }
}
