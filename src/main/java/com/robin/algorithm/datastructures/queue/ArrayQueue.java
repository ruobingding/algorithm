package com.robin.algorithm.datastructures.queue;

public class ArrayQueue <T> implements Queue<T> {
    private Object[] data;
    private int front;
    private int rear;

    public ArrayQueue(int capacity) {
        data = new Object[capacity + 1];
        front = 0;
        rear = 0;
    }

    @Override
    public void offer(T elem) {
        if (isFull()) {
            throw new RuntimeException("Queue is full");
        }
        data[rear++] = elem;
        rear = adjustIndex(rear, data.length);
    }

    @Override
    public T poll() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        front = adjustIndex(front, data.length);
        return (T) data[front++];
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        front = adjustIndex(front, data.length);
        return (T) data[front];
    }

    @Override
    public int size() {
        return adjustIndex(rear + data.length - front, data.length);
    }

    @Override
    public boolean isEmpty() {
        return rear == front;
    }

    public boolean isFull() {
        return (front + data.length - rear) % data.length == 1;
    }

    private int adjustIndex(int index, int size) {
        return index >= size ? index - size : index;
    }
}

