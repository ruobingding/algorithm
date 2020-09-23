package com.robin.algorithm.datastructures.unionfind;

public class UnionFind {
    private int size;
    private int[] rank;
    private int[] father;
    private int numComponents;

    public UnionFind(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size <= 0 is not allowed");
        }

        this.size  = numComponents = size;
        rank       = new int[size];
        father     = new int[size];

        for (int i = 0; i < size; i++) {
            father[i] = i;
            rank[i]   = 1;
        }
    }

    public int find(int p) {
        int root = p;
        while (root != father[root]){
            root = father[root];
        }

        while (p != root) {
            int next = father[p];
            father[p] = root;
            p = next;
        }

        return root;
    }


    public int componentSize(int p) {
        return rank[find(p)];
    }

    public int size() {
        return size;
    }

    public int components() {
        return numComponents;
    }

    public void union(int p, int q) {

        int root1 = find(p);
        int root2 = find(q);

        if (rank[root1] < rank[root2]) {
            rank[root2] += rank[root1];
            father[root1] = root2;
        } else {
            rank[root1] += rank[root2];
            father[root2] = root1;
        }
        numComponents--;
    }

    public boolean isConnected(int p, int q){
        return find(p) == find(q);
    }

    public static void main(String[] args) {
        int n = 10;
        UnionFind union = new UnionFind(n);
        System.out.println("初始：");

        System.out.println("连接了5 6");
        union.union(5, 6);


        System.out.println("连接了1 2");
        union.union(1, 2);


        System.out.println("连接了2 3");
        union.union(2, 3);


        System.out.println("连接了1 4");
        union.union(1, 4);


        System.out.println("连接了1 5");
        union.union(1, 5);

        System.out.println("1  6 是否连接：" + union.isConnected(1, 6));

        System.out.println("1  8 是否连接：" + union.isConnected(1, 8));
    }
}
