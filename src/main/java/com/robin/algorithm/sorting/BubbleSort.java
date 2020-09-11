package com.robin.algorithm.sorting;

import java.util.Arrays;

public class BubbleSort implements Sort{
    @Override
    public void sort(int[] values) {
        BubbleSort.bubbleSort(values);
    }

    private static void bubbleSort(int[] array){
        if(array == null){
            return;
        }

        for(int i = 0; i < array.length; i ++){
            for (int j = 0; j < array.length - i -1; j++) {
                if ( array[j] > array[j + 1] ){
                    swap(array,j , j + 1);
                }
            }
        }

    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        Sort sorter = new BubbleSort();
        int[] array = {1,9,3,5,18,2,99};
        sorter.sort(array);
        System.out.println(Arrays.toString(array));
    }
}
