package com.robin.algorithm.datastructures.tree;

public class BinarySearchTree<T extends Comparable<T>> {
    private int nodeCount;
    private Node root = null;
    private class Node {
        T data;
        Node left, right;
        public Node( Node left, Node right, T data) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public int size(){
        return nodeCount;
    }

    public boolean isEmpty() {
        return size() == 0;
    }
    
    public boolean add (T element){
        if(contains(element)){
            return false;
        }else {
            root = add(root , element);
            nodeCount++;
            return true;
        }
    }

    private Node add(Node node, T element) {
        if (node == null){
            node = new Node(null,null, element);
        }else {
            if (element.compareTo(node.data) < 0) {
                node.left = add(node.left, element);
            } else {
                node.right = add(node.right, element);
            }
        }

        return node;
    }

    public boolean remove(T element) {
        if (contains(element)) {
            root = remove(root, element);
            nodeCount--;
            return true;
        }
        return false;
    }

    private Node remove(Node node, T elem) {

        if (node == null) {
            return null;
        }
        int cmp = elem.compareTo(node.data);
        if (cmp < 0) {
            node.left = remove(node.left, elem);
        } else if (cmp > 0) {
            node.right = remove(node.right, elem);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                Node tmp = findMin(node.right);
                node.data = tmp.data;
                node.right = remove(node.right, tmp.data);
            }
        }

        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node findMax(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    private boolean contains(T element) {
        return contains(root, element);
    }

    private boolean contains(Node node, T element) {
        if (node == null) {
            return false;
        }

        int cmp = element.compareTo(node.data);
        if (cmp < 0) {
            return contains(node.left, element);
        } else if (cmp > 0) {
            return contains(node.right, element);
        } else {
            return true;
        }
    }

    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return Math.max(height(node.left), height(node.right)) + 1;
    }
    
}
