package com.robin.algorithm.sorting;

import java.util.Arrays;

public class InsertSort implements Sort{
    @Override
    public void sort(int[] values) {
        InsertSort.insertSort(values);
    }

    private static void insertSort(int[] array){
        if(array == null){
            return;
        }

        for(int i = 1; i < array.length; i++){
            for (int j = i; j > 0 && array[i] < array[j - 1] ; j--) {
                swap(array,  j - 1, j);
            }
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        Sort sorter = new InsertSort();
        int[] array = {1,9,3,5,18,2,99};
        sorter.sort(array);
        System.out.println(Arrays.toString(array));
    }
}
