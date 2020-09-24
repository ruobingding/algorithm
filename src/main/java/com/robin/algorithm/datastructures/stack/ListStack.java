package com.robin.algorithm.datastructures.stack;

import java.util.EmptyStackException;
import java.util.LinkedList;

public class ListStack<T> implements Stack<T> {
    private LinkedList<T> list = new LinkedList<T>();

    public ListStack() {}

    public ListStack(T firstElem) {
        push(firstElem);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.size() == 0;
    }

    @Override
    public void push(T elem) {
        list.addLast(elem);
    }

    @Override
    public T pop() {
        if (isEmpty()){
            throw new EmptyStackException();
        }
        return list.removeLast();
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.peekLast();
    }
}
