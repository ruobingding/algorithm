package com.robin.algorithm.sorting;

import java.util.Arrays;

public class HeapSort implements Sort{
    @Override
    public void sort(int[] values) {
        HeapSort.heapSort(values);
    }
    private static void heapSort(int[] array) {
        if(array == null){
            return;
        }

        int size = array.length;
        for (int i = Math.max(0, (size / 2) - 1); i >= 0; i--) {
            build(array, size, i);
        }

        for (int i = size - 1; i >= 0; i--) {
            swap(array, 0, i);
            build(array, i, 0);
        }

    }

    private static void build(int[] array, int size, int i) {
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int largest = i;

            // Right child is larger than parent
            if (right < size && array[right] > array[largest]){
                largest = right;
            }

            // Left child is larger than parent
            if (left < size && array[left] > array[largest]){
                largest = left;
            }

            // Move down the tree following the largest node
            if (largest != i) {
                swap(array, largest, i);
                i = largest;
            } else{
                break;
            }
        }
    }


    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        Sort sorter = new HeapSort();
        int[] array = {1,9,3,5,18,2,99};
        sorter.sort(array);
        System.out.println(Arrays.toString(array));
    }
}
