package org.ronnymeringolo;

import org.ronnymeringolo.io.exceptions.NegativeNumbersException;
import org.ronnymeringolo.io.exceptions.TooManyDigitsException;

/**
 * This utility class provides supporting methods to calculate products of positive integers using only sum operations.
 */
public class MultiplierUtils {

    /**
     * Digit-to-digit sum of two arrays. It also includes a shifts params to optionally left-shift the second array.
     * For example:
     * main = {1, 9, 2}
     * toSum = {3, 4}
     * shifts = 1
     * Operations:
     * 1. Left-shift toSum by one, so it becomes {3, 4, 0}
     * 2. Digit-to-digit sum --> 2 + 0 = 2, 9 + 4 = 13, write 3 and shift 1, 1 + 3 + 1 shifted = 5
     * 3. Result: {5, 3, 2}
     * @param main an array of integers
     * @param toSum another array of integers
     * @param shifts the number of needed left-shifts
     * @return the digit-to-digit sum of main and toSum, including "shifts" number of left-shifts
     */
    public static int[] sumArray(int[] main, int[] toSum, int shifts) {
        if (shifts < 0 )
            throw new NegativeNumbersException("Please only input positive integers");
        int[] result = new int[Math.max(main.length, toSum.length) + 1];
        int[] finalArrayToSum = new int[toSum.length + shifts];
        System.arraycopy(toSum, 0, finalArrayToSum, 0, toSum.length);
        for (int i = 0; i < result.length; i++) {
            int x = 0;
            int y = 0;
            if (i < main.length) {
                x = main[main.length - i - 1];
            }
            if (i < finalArrayToSum.length) {
                y = finalArrayToSum[finalArrayToSum.length - i - 1];
            }
            int[] sumResult = sumDigits(x, y);
            sumDigitToDigit(result, sumResult, i);
        }
        return result;
    }

    private static void sumDigitToDigit(int[] main, int[] toAdd, int i) {
        for (int j = 0; j < toAdd.length; j++) {
            int n1 = main[main.length - i - j - 1];
            int n2 = toAdd[toAdd.length - 1 - j];
            int sum = n1 + n2;
            if (sum < 10) {
                main[main.length - i - j - 1] = sum;
            } else {
                int units = sum % 10;
                main[main.length - i - j - 1] = units;
                int tens = sum / 10;
                main[main.length - i - j - 2] = tens;
            }
        }
    }

    private static int[] sumDigits(int x, int y) {
        int result = x + y;
        return NumberUtils.toDigitArray(result);
    }

    /**
     * Method to calculate the product of two positive integer numbers represented as arrays of digits.
     * @param x a positive integer number represented as arrays of digits
     * @param y another positive integer number represented as arrays of digits
     * @return the product of x * y
     */
    static int[] multiply(int[] x, int[] y) {
        int[] longer;
        int[] shorter;
        if (x.length > y.length) {
            longer = x;
            shorter = y;
        } else {
            longer = y;
            shorter = x;
        }

        int[] resArray = new int[x.length + y.length];
        return multiplyRecursive(longer, shorter, resArray, shorter.length - 1, 0);
    }

    private static int[] multiplyRecursive(int[] longer, int[] shorter, int[] result, int index, int shifts) {
        if (index < 0) return result;

        // tempArray will store the partial results
        int[] tempArray = new int[longer.length + 1];

        int k = 0;

        for (int i = longer.length - 1; i >= 0; i--) {
            int[] productResult = sumManyTimes(longer[i], shorter[index]);
            sumDigitToDigit(tempArray, productResult, k++);
        }
        result = sumArray(result, tempArray, shifts);
        return multiplyRecursive(longer, shorter, result, index - 1, shifts + 1);
    }

    public static int[] sumManyTimes(int num, int times) {
        if (num > 10 || times > 10) throw new TooManyDigitsException("You can only multiply single digits");
        int result = 0;
        for (int i = 0; i < times; i++) {
            result += num;
        }
        return NumberUtils.toDigitArray(result);
    }
}
