package com.robin.algorithm.ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithmTravelingSalesman {
    static final Random RANDOM = new Random();

    static final int P = 250;
    static final int MAX_EPOCH = 1000;
    static final double MUTATION_RATE = 0.015;

    static double power;
    static final double POWER_INC = 0.0001;

    static double tsp(double[][] adjacencyMatrix) {

        power = 1.0;
        final int N = adjacencyMatrix.length;

        double max = Double.NEGATIVE_INFINITY;
        for (double[] row : adjacencyMatrix) {
            for (double elem : row) {
                max = Math.max(max, elem);
            }
        }

        Individual[] generation = new Individual[P + 1];
        Individual[] nextGeneration = new Individual[P + 1];
        for (int i = 1; i <= P; i++){
            generation[i] = new Individual(N);
        }

        double[] lo = new double[P + 1];
        double[] hi = new double[P + 1];

        double[] fitness = new double[P + 1];

        int[] tour = null;
        Individual fittestIndv = null;
        double fittestIndvFitness = Double.NEGATIVE_INFINITY;

        for (int epoch = 1; epoch <= MAX_EPOCH; epoch++, power += POWER_INC) {
            double fitnessSum = 0;

            for (int i = 1; i <= P; i++) {
                Individual in = generation[i];
                fitness[i] = fitness(in, adjacencyMatrix, max, N);
                fitnessSum += fitness[i];
                lo[i] = hi[i] = 0;
            }

            Individual bestEpochIndv = null;
            double bestEpochFitness = Double.NEGATIVE_INFINITY;

            for (int i = 1; i <= P; i++) {

                Individual in = generation[i];
                double norm = fitness[i] / fitnessSum;

                lo[i] = hi[i - 1] = lo[i - 1] + norm;

                if (fitness[i] > bestEpochFitness) {

                    bestEpochIndv = in;
                    bestEpochFitness = fitness[i];
                    if (fittestIndv == null) {
                        fittestIndv = in;
                    }

                    double bestEpochTravelCost = trueTravelCost(bestEpochIndv, adjacencyMatrix, N);
                    double bestTravelCost = trueTravelCost(fittestIndv, adjacencyMatrix, N);

                    if (bestEpochTravelCost <= bestTravelCost) {
                        tour = in.cities.clone();
                        fittestIndv = in;
                        fittestIndvFitness = bestEpochTravelCost;
                    }
                }
            }

            double bestEpochTravelCost = trueTravelCost(bestEpochIndv, adjacencyMatrix, N);
            double bestTravelCost = trueTravelCost(fittestIndv, adjacencyMatrix, N);

            if (epoch % 100 == 0){
                System.out.printf("Epoch: %d, %.0f, %.0f\n", epoch, bestEpochTravelCost, bestTravelCost);
            }

            for (int i = 1; i <= P; i++) {

                Individual parent1 = selectIndividual(generation, lo, hi);
                Individual parent2 = selectIndividual(generation, lo, hi);
                Individual child = crossover(parent1, parent2, N);

                for (int j = 0; j < N; j++) {
                    if (Math.random() < MUTATION_RATE) {
                        mutate(child);
                    }
                }

                nextGeneration[i] = child;
            }

            generation = nextGeneration;
        }

        System.out.println(fittestIndv.toString());
        return trueTravelCost(fittestIndv, adjacencyMatrix, N);
    }


    static double fitness(Individual in, double[][] adjacencyMatrix, double max, int n) {
        double fitness = 0;

        for (int i = 1; i < n; i++) {
            int from = in.cities[i - 1];
            int to = in.cities[i];
            fitness += max - adjacencyMatrix[from][to];
        }

        int last = in.cities[n - 1];
        int first = in.cities[0];
        fitness += max - adjacencyMatrix[last][first];

        return Math.pow(fitness, power);
    }

    static double trueTravelCost(Individual in, double[][] adjacencyMatrix, int n) {
        double fitness = 0;

        for (int i = 1; i < n; i++) {
            int from = in.cities[i - 1];
            int to = in.cities[i];
            fitness += adjacencyMatrix[from][to];
        }

        int last = in.cities[n - 1];
        int first = in.cities[0];
        fitness += adjacencyMatrix[last][first];

        return fitness;
    }

    static void mutate(Individual in) {
        in.mutate();
    }

    static Individual selectIndividual(Individual[] generation, double[] lo, double[] hi) {
        double r = Math.random();

        int mid, l = 0, h = P - 1;
        while (true) {
            mid = (l + h) >>> 1;
            if (lo[mid] <= r && r < hi[mid]){
                return generation[mid + 1];
            }
            if (r < lo[mid]){
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }
    }

    static Individual crossover(Individual p1, Individual p2, int n) {

        int[] newPath = new int[n];
        int start = RANDOM.nextInt(n);
        int end = RANDOM.nextInt(n);
        int minimum = Math.min(start, end);
        int maximum = Math.max(start, end);

        int[] missing = new int[n - ((maximum - minimum) + 1)];

        int j = 0;
        for (int i = 0; i < n; i++) {
            if (i >= minimum && i <= maximum) {
                newPath[i] = p1.cities[i];
            } else {
                missing[j++] = p1.cities[i];
            }
        }

        Individual.shuffleArray(missing);

        j = 0;
        for (int i = 0; i < n; i++) {
            if (i < minimum || i > maximum) {
                newPath[i] = missing[j++];
            }
        }
        return new Individual(newPath);
    }

    public static void main(String[] args) {

        int n = 13;
        double[][] m = {{0,255,	196,115,466,174,1015,308,326,136,708,471,1310,774,2101,2239,76},
                {255,0,420,223,	721,393,955,559,99,893,630,239,1085,536,1858,2017,203},
                {196,420,0,307,339,266,958,169,506,1248,684,593,1497,954,2278,2428,271},
                {115,223,307,0,537,174,1092,395,262,1115,776,460,1218,695,2020,2143,52},
                {466,721,339,537,0,384,1237,171,789,1583,997,922,1750,1231,2557,2669,529},
                {174,393,266,174,384,0,1180,273,436,1286,879,626,1368,859,2180,2285,192},
                {1015,955,958,1092,	1237,1180,0,1098,1027,971,325,816,1722,1242,2268,2575,1042},
                {308,559,169,395,171,273,1098,0,634,1413,841,753,1612,1082,2409,2538,377},
                {326,99,506,262,789,436,1027,634,0,867,702,	53,993,449,1776,1925,260},
                {1136,893,1248,	1115,1583,1286,971,1413,867,0,821,665,879,650,1299,1628,1095},
                {708,630,684,776,997,879,325,841,702,821,0,500,1460,949,2081,2350,727},
                {471,239,593,460,922,626,816,753,253,665,500,0,	1014,470,1730,1937,434},
                {1310,1085,1497,1218,1750,1368,1722,1612,993,879,1460,1014,0,553,831,933,1236},
                {774,536,954,695,1231,859,1242,1082,449,650,949,470	,553,0,1327,1485,705},
                {2101,1858,2278,2020,2557,2180,2268,2409,1776,1299,2081,1730,831,1327,0,455,2032},
                {2239,2017,2428,2143,2669,2285,2575,2538,1925,1628,2350,1937,933,1485,455,0,2165},
                {76,203,271,52,529,192,1042,377,260,1095,727,434,1236,705,2032,	2165,0}};

        List<Integer> path = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            path.add(i);
        }

        System.out.println(tsp(m));
    }

    static class Individual {
        int[] cities;
        static Random RANDOM = new Random();

        public Individual(int n) {
            cities = new int[n];
            for (int i = 0; i < n; i++) {
                cities[i] = i;
            }
            shuffleArray(cities);
        }

        public Individual(int[] cities) {
            this.cities = cities;
        }

        public void mutate() {
            int i = RANDOM.nextInt(cities.length);
            int j = RANDOM.nextInt(cities.length);
            int tmp = cities[i];
            cities[i] = cities[j];
            cities[j] = tmp;
        }

        public static void shuffleArray(int[] array) {
            int index;
            for (int i = array.length - 1; i > 0; i--) {
                index = RANDOM.nextInt(i + 1);
                if (index != i) {
                    array[index] ^= array[i];
                    array[i] ^= array[index];
                    array[index] ^= array[i];
                }
            }
        }

        @Override
        public String toString() {
            return Arrays.toString(cities);
        }
    }
}

