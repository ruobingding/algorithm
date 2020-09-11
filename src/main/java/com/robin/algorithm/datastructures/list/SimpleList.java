package com.robin.algorithm.datastructures.list;

public interface SimpleList<T> {

    /**
     * Set specific index element
     * @param index
     * @param element
     */
    void set(int index, T element);

    /**
     * get specific index element
     * @param index
     * @return
     */
    T get(int index);

    /**
     * add element to simple list
     * @param element
     */
    void add(T element);

    /**
     * remove specific index elment from the simple list
     * @param removeIndex
     * @return
     */
    T removeAt(int removeIndex);

    /***
     * remove specific object from the simple list
     * @param object
     * @return
     */
    boolean remove(Object object);

    /**
     * get the index in the simple list of the object
     * @param object
     * @return
     */
    int indexOf(Object object);

    /**
     * if have object in the simple list.
     * @param object
     * @return
     */
    boolean contains(Object object);

    /**
     * clear all elments.
     */
    void clear();

    /**
     * size of the list
     * @return
     */
    int size();

    /**
     * if empty of the list
     */
    boolean isEmpty();

}
