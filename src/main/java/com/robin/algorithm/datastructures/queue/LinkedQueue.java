package com.robin.algorithm.datastructures.queue;

public class LinkedQueue<T> implements Queue<T> {

    private java.util.LinkedList<T> list = new java.util.LinkedList<T>();

    public LinkedQueue() {}

    public LinkedQueue(T firstElem) {
        offer(firstElem);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public T peek() {
        if (isEmpty()){
            throw new RuntimeException("Queue Empty");
        }
        return list.peekFirst();
    }


    @Override
    public T poll() {
        if (isEmpty()){
            throw new RuntimeException("Queue Empty");
        }
        return list.removeFirst();
    }

    @Override
    public void offer(T elem) {
        list.addLast(elem);
    }

}
