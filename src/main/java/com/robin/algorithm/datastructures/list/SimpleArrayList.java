package com.robin.algorithm.datastructures.list;

import java.util.Iterator;

/**
 * @author robin
 */
public class SimpleArrayList<T> implements SimpleList<T> ,Iterable<T> {
    private T[] array;
    private int length   = 0;
    private int capacity = 0;


    public SimpleArrayList() {
        this(16);
    }

    public SimpleArrayList(int capacity) {
        if (capacity < 0){
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
        this.capacity = capacity;
        array = (T[]) new Object[capacity];
    }

    @Override
    public void set(int index, T element){
        if (index >= length || index < 0){
            throw new IndexOutOfBoundsException();
        }
        array[index] = element;
    }

    @Override
    public T get(int index){
        if (index >= length || index < 0){
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    @Override
    public void add(T element) {
        if (length + 1 >= capacity) {
            if (capacity == 0) {
                capacity = 1;
            }
            else {
                capacity *= 2;
            }
            T[] newArray = (T[]) new Object[capacity];
            for (int i = 0; i < length; i++){
                newArray[i] = array[i];
            }
            array = newArray;
        }
        array[length++] = element;
    }

    @Override
    public T removeAt(int removeIndex) {
        if (removeIndex >= length || removeIndex < 0) {
            throw new IndexOutOfBoundsException();
        }
        T data = array[removeIndex];
        T[] newArray = (T[]) new Object[length - 1];
        for (int i = 0, j = 0; i < length; i++, j++){
            if (i == removeIndex){
                j--;
            } else {
                newArray[j] = array[i];
            }
        }

        array = newArray;
        capacity = --length;
        return data;
    }

    @Override
    public boolean remove(Object object) {
        int index = indexOf(object);
        if (index == -1) {
            return false;
        }
        removeAt(index);
        return true;
    }

    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < length; i++) {
            if (object == null) {
                if (array[i] == null) {
                    return i;
                }
            } else {
                if (object.equals(array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public boolean contains(Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public void clear() {
        for (int i = 0; i < length; i++){
            array[i] = null;
        }
        length = 0;
    }
    @Override
    public int size(){
        return length;
    }

    @Override
    public boolean isEmpty(){
        return size() == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < length;
            }

            @Override
            public T next() {
                return array[index++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public String toString() {
        if (length == 0) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder(length).append("[");
            for (int i = 0; i < length - 1; i++) {
                sb.append(array[i] + ", ");
            }
            return sb.append(array[length - 1] + "]").toString();
        }
    }


}
