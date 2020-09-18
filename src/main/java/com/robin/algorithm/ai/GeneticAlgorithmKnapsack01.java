package com.robin.algorithm.ai;

import com.robin.algorithm.dp.Knapsack01;

import java.util.BitSet;
import java.util.Random;

public class GeneticAlgorithmKnapsack01 {
    static final Random RANDOM = new Random();

    static final int P = 5000;  // Genetic algorithm parameters (P = Population)
    static final int MAX_EPOCH = 10000;
    static final double MUTATION_RATE = 0.0125;

    //权重变更用于
    static double power;
    static final double POWER_INC = 0.0001;

    static long run(int capacity, int[] weights, int[] values) {
        power = 1.0;
        final int N = weights.length;

        // Create initial population
        Individual[] generation = new Individual[P + 1];
        Individual[] nextGeneration = new Individual[P + 1];
        for (int i = 1; i <= P; i++){
            generation[i] = new Individual(N);
        }

        // Stores the ranges of individuals in the selection roulette
        double[] lo = new double[P + 1];
        double[] hi = new double[P + 1];

        long[] fitness = new long[P + 1];
        long bestFitness = 0;

        for (int epoch = 1; epoch <= MAX_EPOCH; epoch++, power += POWER_INC) {

            // Compute the total fitness sum across all individuals in order
            // to be able to normalize and assign importance percentages
            double fitnessSum = 0;

            for (int i = 1; i <= P; i++) {
                Individual in = generation[i];
                fitness[i] = fitness(in, weights, values, capacity, N);
                fitnessSum += fitness[i];
                lo[i] = hi[i] = 0;
            }

            // Track the fittest individual
            long bestEpochFitness = 0;

            // Setup selection roulette
            for (int i = 1; i <= P; i++) {

                double norm = fitness[i] / fitnessSum;

                lo[i] = hi[i - 1] = lo[i - 1] + norm;

                if (fitness[i] > bestEpochFitness) {
                    bestEpochFitness = fitness[i];
                    if (bestEpochFitness > bestFitness){
                        bestFitness = bestEpochFitness;
                    }
                }
            }

            if (epoch % 50 == 0){
                System.out.printf("Epoch: %d, %d$, %d$\n", epoch, bestEpochFitness, bestFitness);
            }

            // Selection process
            for (int i = 1; i <= P; i++) {
                // Perform individual selection and crossover
                Individual parent1 = selectIndividual(generation, lo, hi);
                Individual parent2 = selectIndividual(generation, lo, hi);
                Individual child = crossover(parent1, parent2, N);

                for (int j = 0; j < N; j++) {
                    if (Math.random() < MUTATION_RATE){
                        mutate(child, j);
                    }
                }
                nextGeneration[i] = child;
            }
            generation = nextGeneration;
        }

        return bestFitness;
    }


    static long fitness(Individual in, int[] weights, int[] values, int capacity, int n) {
        long value = 0, weight = 0;
        for (int i = 0; i < n; i++) {
            if (in.bits.get(i)) {
                value += values[i];
                weight += weights[i];
            }
            if (weight > capacity){
                return 0;
            }
        }
        return value;
    }


    // select
    static Individual selectIndividual(Individual[] generation, double[] lo, double[] hi) {
        double r = Math.random();
        // Binary search to find individual
        int mid, l = 0, h = P - 1;
        while (true) {
            mid = (l + h) >>> 1;
            if (lo[mid] <= r && r < hi[mid]){
                return generation[mid + 1];
            }
            if (r < lo[mid]){
                h = mid - 1;
            }
            else {
                l = mid + 1;
            }
        }
    }

    //cross over
    static Individual crossover(Individual p1, Individual p2, int n) {
        int splitPoint = RANDOM.nextInt(n);
        BitSet newBitSet = new BitSet(n);
        for (int i = 0; i < splitPoint; i++){
            if (p1.bits.get(i)){
                newBitSet.flip(i);
            }
        }
        for (int i = splitPoint; i < n; i++){
            if (p2.bits.get(i)){
                newBitSet.flip(i);
            }
        }
        return new Individual(newBitSet);
    }

    //mutation
    static void mutate(Individual in, int i) {
        in.bits.flip(i);
    }

    public static void main(String[] args) {
        int size = 50;
        int multiplier = 100000;

        int[] weights = new int[size];
        int[] values = new int[size];

        for (int i = 0; i < size; i++) {
            weights[i] = (int) (Math.random() * multiplier);
            values[i] = (int) (Math.random() * multiplier);
        }

        int capacity = (size * multiplier) / 3;
        long gaAnswer = run(capacity, weights, values);
        int dpAnswer = Knapsack01.knapsack(capacity, weights, values);

        System.out.println("\nGenetic algorithm approximation: " + gaAnswer + "$");
        System.out.println("Actual answer calculated with DP:  " + dpAnswer + "$\n");
        System.out.printf("Genetic algorithm was %.5f%% off from true answer\n\n",
                (1.0 - ((double) gaAnswer) / dpAnswer) * 100);
    }

    static class Individual {
        BitSet bits;

        public Individual(int n) {
            bits = new BitSet(n);
            for (int i = 0; i < n; i++) {
                if (Math.random() < 0.5) {
                    bits.flip(i);
                }
            }
        }

        public Individual(BitSet bits) {
            this.bits = bits;
        }

        @Override
        public String toString() {
            return bits.toString();
        }
    }

}
