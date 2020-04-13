package graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class BFS {
    static Set<Integer> visited = new HashSet<>();

    static void breadthFirstSearch(Graph graph) {
        for (Integer vertex : graph.vertices()) {
            bfs(graph, vertex);
        }
    }

    private static void bfs(Graph graph, Integer vertex) {
        if (visited.contains(vertex)) return;
        Queue<Integer> queue = new LinkedList<>();
        visited.add(vertex);
        queue.offer(vertex);
        while (!queue.isEmpty()) {
            Integer v = queue.poll();
            System.out.println(v);
            for (Integer ver : graph.adj(v)) {
                if (!visited.contains(ver)) {
                    visited.add(ver);
                    queue.offer(ver);
                }
            }
        }
    }
}