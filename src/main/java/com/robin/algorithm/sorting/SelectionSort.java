package com.robin.algorithm.sorting;

import java.util.Arrays;

public class SelectionSort implements Sort {
    @Override
    public void sort(int[] values) {
        SelectionSort.selectSort(values);
    }

    private static void selectSort(int[] array){
        if(array == null){
            return;
        }

        for(int i = 0; i < array.length; i++){
            int swapIndex = i;
            for(int j = i + 1; j < array.length; j++){
                if (array[j] < array[swapIndex]){
                    swapIndex =  j;
                }
            }
            swap(array,i,swapIndex);
        }

    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        Sort sorter = new SelectionSort();
        int[] array = {1,9,3,5,18,2,99};
        sorter.sort(array);
        System.out.println(Arrays.toString(array));
    }
}
