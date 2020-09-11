package com.robin.algorithm.sorting;

import java.util.Arrays;

public class CountingSort implements Sort{
    @Override
    public void sort(int[] values) {
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < values.length; i++) {
            if (values[i] < minValue){
                minValue = values[i];
            }
            if (values[i] > maxValue){
                maxValue = values[i];
            }
        }
        CountingSort.countingSort(values, minValue, maxValue);
    }

    private static void countingSort(int[] array, int minValue, int maxValue){
        int size = maxValue - minValue + 1;
        int[] temp = new int[size];

        for (int i = 0; i < array.length; i++){
            temp[array[i] - minValue]++;
        }

        for (int i = 0, k = 0; i < size; i++) {
            while (temp[i]-- > 0){
                array[k++] = i + minValue;
            }
        }
    }
    public static void main(String[] args) {
        Sort sorter = new CountingSort();
        int[] array = {1,9,3,5,18,2,99};
        sorter.sort(array);
        System.out.println(Arrays.toString(array));
    }
}
