package com.robin.algorithm.datastructures.trie;

import java.util.HashMap;
import java.util.Map;

public class Trie {
    private final char rootCharacter = '\0';
    private Node root = new Node(rootCharacter);

    private static class Node {
        char ch;
        int count = 0;
        boolean isWordEnding = false;
        Map<Character, Node> children = new HashMap<>();

        public Node(char ch){
            this.ch = ch;
        }

        public void addChild(Node node, char c){
            children.put(c, node);
        }
    }

    public boolean insert(String key, int numInserts) {
        if (key == null) {
            throw new IllegalArgumentException("Null not permitted in trie");
        }
        if (numInserts <= 0){
            throw new IllegalArgumentException("numInserts has to be greater than zero");
        }

        Node node             = root;
        boolean createNewNode = false;
        boolean isPrefix      = false;

        for (int i = 0; i < key.length(); ++i) {
            char ch = key.charAt(i);
            Node nextNode = node.children.get(ch);
            if (nextNode == null) {
                nextNode = new Node(ch);
                node.addChild(nextNode, ch);
                createNewNode = true;
            } else {
                if (nextNode.isWordEnding){
                    isPrefix = true;
                }
            }
            node = nextNode;
            node.count += numInserts;
        }
        if(node != root){
            node.isWordEnding = true;
        }

        return isPrefix || !createNewNode;

    }

    public boolean insert(String key) {
        return insert(key, 1);
    }

    public boolean delete(String key, int numDeletions) {

        if (!contains(key)) {
            return false;
        }

        if (numDeletions <= 0){
            throw new IllegalArgumentException("numDeletions has to be positive");
        }

        Node node = root;
        for (int i = 0; i < key.length(); i++) {

            char ch = key.charAt(i);
            Node curNode = node.children.get(ch);
            curNode.count -= numDeletions;
            if (curNode.count <= 0) {
                node.children.remove(ch);
                curNode.children = null;
                curNode = null;
                return true;
            }

            node = curNode;
        }
        return true;
    }

    public boolean delete(String key) {
        return delete(key, 1);
    }

    public boolean contains(String key) {
        return count(key) != 0;
    }

    public int count(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Null not permitted");
        }

        Node node = root;
        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            if (node == null){
                return 0;
            }
            node = node.children.get(ch);
        }

        if (node != null) {
            return node.count;
        }
        return 0;
    }

    private void clear(Node node) {
        if (node == null) {
            return;
        }

        for (Character ch : node.children.keySet()) {
            Node nextNode = node.children.get(ch);
            clear(nextNode);
            nextNode = null;
        }

        node.children.clear();
        node.children = null;
    }


    public void clear() {
        root.children = null;
        root = new Node(rootCharacter);
    }
}
