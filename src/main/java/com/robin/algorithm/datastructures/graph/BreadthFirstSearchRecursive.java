package com.robin.algorithm.datastructures.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearchRecursive {
    public static int DEPTH_TOKEN = -1;

    public static int bfs(List<List<Integer>> graph, int start, int n) {
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        queue.offer(DEPTH_TOKEN);
        return bfs(visited, queue, graph);
    }

    private static int bfs(boolean[] visited, Queue<Integer> queue, List<List<Integer>> graph) {

        int at = queue.poll();

        if (at == DEPTH_TOKEN) {
            queue.offer(DEPTH_TOKEN);
            return 1;
        }

        if (visited[at]) {
            return 0;
        }

        visited[at] = true;

        List<Integer> neighbors = graph.get(at);
        if (neighbors != null) {
            for (int next : neighbors) {
                if (!visited[next]) {
                    queue.add(next);
                }
            }
        }

        int depth = 0;

        while (true) {
            // Stop when the queue is empty (i.e there's only one depth token remaining)
            if (queue.size() == 1 && queue.peek() == DEPTH_TOKEN) {
                break;
            }

            depth += bfs(visited, queue, graph);
        }

        return depth;
    }

    public static void main(String[] args) {
        int n = 14;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        addUndirectedEdge(graph, 0, 1);
        addUndirectedEdge(graph, 0, 2);
        addUndirectedEdge(graph, 0, 3);
        addUndirectedEdge(graph, 2, 9);
        addUndirectedEdge(graph, 8, 2);
        addUndirectedEdge(graph, 3, 4);
        addUndirectedEdge(graph, 10, 11);
        addUndirectedEdge(graph, 12, 13);
        addUndirectedEdge(graph, 3, 5);
        addUndirectedEdge(graph, 5, 7);
        addUndirectedEdge(graph, 5, 6);
        addUndirectedEdge(graph, 0, 10);
        addUndirectedEdge(graph, 11, 12);

        System.out.printf("BFS depth: %d\n", bfs(graph, 12, n));
    }

    private static void addUndirectedEdge(List<List<Integer>> graph, int from, int to) {
        graph.get(from).add(to);
        graph.get(to).add(from);
    }
}
