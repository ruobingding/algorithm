package com.robin.algorithm.search;

import java.util.function.DoubleFunction;

public class BinarySearch {
    private static final double EPS = 0.00000001;

    public static double binarySearch(double low, double high, double target, DoubleFunction<Double> function) {

        if (high <= low) {
            throw new IllegalArgumentException("high should be greater than low");
        }

        double middle;
        do {
            middle = (high + low) / 2.0;
            double value = function.apply(middle);
            if (value > target) {
                high = middle;
            } else {
                low = middle;
            }
        } while ((high - low) > EPS);

        return middle;
    }

    public static void main(String[] args) {

        double low = 0.0;
        double high = 875.0;
        double target = 875.0;

        DoubleFunction<Double> function = (x) -> (x * x);

        double sqrtVal = binarySearch(low, high, target, function);
        System.out.printf("sqrt(%.2f) = %.5f, x^2 = %.5f\n", target, sqrtVal, (sqrtVal * sqrtVal));
    }
}
