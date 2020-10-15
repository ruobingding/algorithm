package com.robin.algorithm.datastructures.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopologicalSortAdjacencyList {
    static class Edge {
        int from, to, weight;

        public Edge(int f, int t, int w) {
            from = f;
            to = t;
            weight = w;
        }
    }

    private static int dfs(int i, int at, boolean[] visited, int[] ordering, Map<Integer, List<Edge>> graph) {

        visited[at] = true;
        List<Edge> edges = graph.get(at);
        if (edges != null) {
            for (Edge edge : edges) {
                if (!visited[edge.to]) {
                    i = dfs(i, edge.to, visited, ordering, graph);
                }
            }
        }

        ordering[i] = at;
        return i - 1;
    }

    public static int[] topologicalSort(Map<Integer, List<Edge>> graph, int numNodes) {

        int[] ordering = new int[numNodes];
        boolean[] visited = new boolean[numNodes];

        int i = numNodes - 1;
        for (int at = 0; at < numNodes; at++) {
            if (!visited[at]) {
                i = dfs(i, at, visited, ordering, graph);
            }
        }

        return ordering;
    }

    public static Integer[] dagShortestPath(Map<Integer, List<Edge>> graph, int start, int numNodes) {

        int[] topsort = topologicalSort(graph, numNodes);
        Integer[] dist = new Integer[numNodes];
        dist[start] = 0;

        for (int i = 0; i < numNodes; i++) {

            int nodeIndex = topsort[i];
            if (dist[nodeIndex] != null) {
                List<Edge> adjacentEdges = graph.get(nodeIndex);
                if (adjacentEdges != null) {
                    for (Edge edge : adjacentEdges) {

                        int newDist = dist[nodeIndex] + edge.weight;
                        if (dist[edge.to] == null) {
                            dist[edge.to] = newDist;
                        } else {
                            dist[edge.to] = Math.min(dist[edge.to], newDist);
                        }
                    }
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        final int N = 7;
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 0; i < N; i++) {
            graph.put(i, new ArrayList<>());
        }
        graph.get(0).add(new Edge(0, 1, 3));
        graph.get(0).add(new Edge(0, 2, 2));
        graph.get(0).add(new Edge(0, 5, 3));
        graph.get(1).add(new Edge(1, 3, 1));
        graph.get(1).add(new Edge(1, 2, 6));
        graph.get(2).add(new Edge(2, 3, 1));
        graph.get(2).add(new Edge(2, 4, 10));
        graph.get(3).add(new Edge(3, 4, 5));
        graph.get(5).add(new Edge(5, 4, 7));

        int[] ordering = topologicalSort(graph, N);

        // // Prints: [6, 0, 5, 1, 2, 3, 4]
        System.out.println(java.util.Arrays.toString(ordering));

        // Finds all the shortest paths starting at node 0
        Integer[] dists = dagShortestPath(graph, 0, N);

        // Find the shortest path from 0 to 4 which is 8.0
        System.out.println(dists[4]);

        // Find the shortest path from 0 to 6 which
        // is null since 6 is not reachable!
        System.out.println(dists[6]);
    }
}
