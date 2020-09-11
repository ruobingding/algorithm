package com.robin.algorithm.datastructures.list;

import java.util.Iterator;

public class SimpleDobuleLinkedList<T> implements SimpleList<T> ,Iterable<T>  {
    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    @Override
    public void set(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(T element) {
        addLast(element);
    }

    @Override
    public T removeAt(int removeIndex) {
        if (removeIndex < 0 || removeIndex >= size) {
            throw new IllegalArgumentException();
        }

        int i;
        Node<T> trav;
        if (removeIndex < size / 2) {
            for (i = 0, trav = head; i != removeIndex; i++) {
                trav = trav.next;
            }
        } else{
            for (i = size - 1, trav = tail; i != removeIndex; i--) {
                trav = trav.prev;
            }
        }
        return remove(trav);
    }

    @Override
    public boolean remove(Object obj) {
        Node<T> trav = head;

        if (obj == null) {
            for (trav = head; trav != null; trav = trav.next) {
                if (trav.data == null) {
                    remove(trav);
                    return true;
                }
            }
        } else {
            for (trav = head; trav != null; trav = trav.next) {
                if (obj.equals(trav.data)) {
                    remove(trav);
                    return true;
                }
            }
        }
        return false;
    }

    private T remove(Node<T> node) {
        if (node.prev == null){
            return removeFirst();
        }
        if (node.next == null){
            return removeLast();
        }

        node.next.prev = node.prev;
        node.prev.next = node.next;

        T data = node.data;

        node.data = null;
        node = node.prev = node.next = null;

        --size;

        return data;
    }

    @Override
    public int indexOf(Object object) {
        int index = 0;
        Node<T> trav = head;
        if (object == null) {
            for (; trav != null; trav = trav.next, index++) {
                if (trav.data == null) {
                    return index;
                }
            }
        } else{
            for (; trav != null; trav = trav.next, index++) {
                if (object.equals(trav.data)) {
                    return index;
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
        Node<T> trav = head;
        while (trav != null) {
            Node<T> next = trav.next;
            trav.prev = trav.next = null;
            trav.data = null;
            trav = next;
        }
        head = tail = trav = null;
        size = 0;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public boolean isEmpty(){
        return size() == 0;
    }

    /**
     * Add element to the first place of the list
     * @param element
     */
    public void addFirst(T element){
        if (isEmpty()) {
            head = tail = new Node<T>(element, null, null);
        } else {
            head.prev = new Node<T>(element, null, head);
            head = head.prev;
        }
        size++;
    }

    /**
     * Add element to the last place of the list
     * @param element
     */
    public void addLast(T element) {
        if (isEmpty()) {
            head = tail = new Node<T>(element, null, null);
        } else {
            tail.next = new Node<T>(element, tail, null);
            tail = tail.next;
        }
        size++;
    }

    public T removeFirst(){
        if (isEmpty()){
            throw new RuntimeException("Empty list");
        }

        T data = head.data;
        head = head.next;
        --size;

        if (isEmpty()){
            tail = null;
        } else {
            head.prev = null;
        }
        return data;
    }

    public T removeLast(){
        if (isEmpty()){
            throw new RuntimeException("Empty list");
        }

        T data = tail.data;
        tail = tail.next;
        --size;

        if (isEmpty()){
            head = null;
        } else {
            tail.next = null;
        }
        return data;
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> trav = head;
            @Override
            public boolean hasNext() {
                return trav!=null;
            }

            @Override
            public T next() {
                T data = trav.data;
                trav = trav.next;
                return data;
            }
        };
    }

    private static class Node<T>{
        private T data;
        private Node<T> prev, next;

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }
}
