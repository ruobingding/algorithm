package com.robin.algorithm.math;

public class LCM {
    private static long gcd(long a, long b) {
        return b == 0 ? (a < 0 ? -a : a) : gcd(b, a % b);
    }

    public static long lcm(long a, long b) {
        long lcm = (a / gcd(a, b)) * b;
        return lcm > 0 ? lcm : -lcm;
    }

    public static void main(String[] args) {
        System.out.println(lcm(12, 18)); // 36
        System.out.println(lcm(-12, 18)); // 36
        System.out.println(lcm(12, -18)); // 36
        System.out.println(lcm(-12, -18)); // 36
    }
}
