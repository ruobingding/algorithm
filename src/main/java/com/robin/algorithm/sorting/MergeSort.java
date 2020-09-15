package com.robin.algorithm.sorting;

import java.util.Arrays;

public class MergeSort implements Sort{
    @Override
    public void sort(int[] values) {
        int[] sortedValues = MergeSort.mergesort(values);
        for (int i = 0; i < values.length; i++) {
            values[i] = sortedValues[i];
        }
    }

    public static int[] mergesort(int[] array) {
        int n = array.length;
        if (n <= 1) {
            return array;
        }

        int[] left = mergesort(Arrays.copyOfRange(array, 0, n / 2));
        int[] right = mergesort(Arrays.copyOfRange(array, n / 2, n));

        return merge(left, right);
    }

    private static int[] merge(int[] array1, int[] array2) {
        int n1 = array1.length, n2 = array2.length;
        int n = n1 + n2, i1 = 0, i2 = 0;
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            if (i1 == n1) {
                array[i] = array2[i2++];
            } else if (i2 == n2) {
                array[i] = array1[i1++];
            } else {
                if (array1[i1] < array2[i2]) {
                    array[i] = array1[i1++];
                } else {
                    array[i] = array2[i2++];
                }
            }
        }
        return array;
    }

    public static void main(String[] args) {
        Sort sorter = new MergeSort();
        int[] array = {1,9,3,5,18,2,99};
        sorter.sort(array);
        System.out.println(Arrays.toString(array));
    }
}
