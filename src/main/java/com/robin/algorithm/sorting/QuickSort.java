package com.robin.algorithm.sorting;

import java.util.Arrays;

public class QuickSort implements Sort{
    @Override
    public void sort(int[] values) {
        QuickSort.quicksort(values);
    }

    public static void quicksort(int[] array) {
        if (array == null){
            return;
        } 
        quicksort(array, 0, array.length - 1);
    }

    private static void quicksort(int[] array, int low, int high) {
        if (low < high) {
            int splitPoint = partition(array, low, high);
            quicksort(array, low, splitPoint);
            quicksort(array, splitPoint + 1, high);
        }
    }

    private static int partition(int[] array, int low, int high) {
        int pivot = array[low];
        int i = low - 1, j = high + 1;
        while (true) {
            do {
                i++;
            } while (array[i] < pivot);

            do {
                j--;
            } while (array[j] > pivot);

            if (i < j){
                 swap(array, i, j);
            }else {
                return j;
            }
        }
    }

    private static void swap(int[] ar, int i, int j) {
        int tmp = ar[i];
        ar[i] = ar[j];
        ar[j] = tmp;
    }

    public static void main(String[] args) {
        Sort sorter = new QuickSort();
        int[] array = {1,9,3,5,18,2,99};
        sorter.sort(array);
        System.out.println(Arrays.toString(array));
    }
}
