package com.robin.algorithm.math;

public class SieveOfEratosthenes {
    public static int[] sieve(int limit) {

        if (limit <= 2) {
            return new int[0];
        }

        final int numPrimes = (int) (1.25506 * limit / Math.log((double) limit));
        int[] primes = new int[numPrimes];
        int index = 0;

        boolean[] isComposite = new boolean[limit];
        final int sqrtLimit = (int) Math.sqrt(limit);
        for (int i = 2; i <= sqrtLimit; i++) {
            if (!isComposite[i]) {
                primes[index++] = i;
                for (int j = i * i; j < limit; j += i) {
                    isComposite[j] = true;
                }
            }
        }
        for (int i = sqrtLimit + 1; i < limit; i++) {
            if (!isComposite[i]) {
                primes[index++] = i;
            }
        }
        return java.util.Arrays.copyOf(primes, index);
    }

    public static void main(String[] args) {

        int[] primes = sieve(29);

        System.out.println(java.util.Arrays.toString(primes));
    }
}
