package com.robin.algorithm.sorting;

import java.util.Arrays;

public class RadixSort implements Sort{

    @Override
    public void sort(int[] values) {

    }

    public static void main(String[] args) {
        Sort sorter = new RadixSort();
        int[] array = {1,9,3,5,18,2,99};
        sorter.sort(array);
        System.out.println(Arrays.toString(array));
    }
}
