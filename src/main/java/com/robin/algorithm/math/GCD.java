package com.robin.algorithm.math;

public class GCD {

    public static long gcd1(long m, long n) {

        for (long i = Math.min(m, n); i > 1; i--) {
            if (m % i == 0 && n % i == 0) {
                return i;
            }
        }

        return 1;
    }


    public static long gcd2(long m, long n) {
        long result = 1;

        for (int i = 2; i <= Math.min(m, n) && m != 1 && n != 1; ) {
            if (m % i == 0 && n % i == 0) {
                m /= i;
                n /= i;
                result *= i;
            } else {
                i++;
            }
        }

        return result;
    }


    public static long gcd3(long m, long n) {

        if (m == n) {
            return m;
        }
        if ((m & 1) == 0 && (n & 1) == 0) {
            return gcd3(m >> 1, n >> 1) * 2;
        }
        return gcd3(Math.abs(m - n), Math.min(m, n));
    }

    public static long gcd(long a, long b) {
        return b == 0 ? (a < 0 ? -a : a) : gcd(b, a % b);
    }

    public static void main(String[] args) {
        System.out.println(gcd(12, 18)); // 6
        System.out.println(gcd(-12, 18)); // 6
        System.out.println(gcd(12, -18)); // 6
        System.out.println(gcd(-12, -18)); // 6

        System.out.println(gcd(5, 0)); // 5
        System.out.println(gcd(0, 5)); // 5
        System.out.println(gcd(-5, 0)); // 5
        System.out.println(gcd(0, -5)); // 5
        System.out.println(gcd(0, 0)); // 0
    }
}
