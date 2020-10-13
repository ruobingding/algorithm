package com.robin.algorithm.datastructures.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepthFirstSearchAdjacencyListRecursive {
    static class Edge {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", cost=" + cost +
                    '}';
        }
    }

    static long dfs(int at, boolean[] visited, Map<Integer, List<Edge>> graph) {

        if (visited[at]) {
            return 0L;
        }

        visited[at] = true;
        long count = 1;

        List<Edge> edges = graph.get(at);
        if (edges != null) {
            System.out.println(edges.get(0).toString());
            for (Edge edge : edges) {
                count += dfs(edge.to, visited, graph);
            }
        }

        return count;
    }

    public static void main(String[] args) {

        // Create a fully connected graph
        //           (0)
        //           / \
        //        5 /   \ 4
        //         /     \
        // 10     <   -2  >
        //   +->(2)<------(1)      (4)
        //   +--- \       /
        //         \     /
        //        1 \   / 6
        //           > <
        //           (3)
        int numNodes = 5;
        Map<Integer, List<Edge>> graph = new HashMap<>();
        addDirectedEdge(graph, 0, 1, 4);
        addDirectedEdge(graph, 0, 2, 5);
        addDirectedEdge(graph, 1, 2, -2);
        addDirectedEdge(graph, 1, 3, 6);
        addDirectedEdge(graph, 2, 3, 1);
        addDirectedEdge(graph, 2, 2, 10);

        long nodeCount = dfs(0, new boolean[numNodes], graph);
        System.out.println("DFS node count starting at node 0: " + nodeCount);
        if (nodeCount != 4) {
            System.err.println("Error with DFS");
        }

        nodeCount = dfs(4, new boolean[numNodes], graph);
        System.out.println("DFS node count starting at node 4: " + nodeCount);
        if (nodeCount != 1) {
            System.err.println("Error with DFS");
        }
    }


    private static void addDirectedEdge(Map<Integer, List<Edge>> graph, int from, int to, int cost) {
        List<Edge> list = graph.get(from);
        if (list == null) {
            list = new ArrayList<Edge>();
            graph.put(from, list);
        }
        list.add(new Edge(from, to, cost));
    }
}
