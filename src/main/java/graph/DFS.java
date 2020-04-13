package graph;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

class DFS {
    private static Set<Integer> visited = new HashSet();

    static void depthFirstSearch(Graph graph) {
        for (Integer v : graph.vertices()) {
            dfsIterative(graph, v);
        }
    }

    private static void dfs(Graph graph, Integer vertex) {
        if (visited.contains(vertex)) return;
        visited.add(vertex);
        for (Integer v : graph.adj(vertex)) {
            dfs(graph, v);
        }
        System.out.println(vertex);
    }

    private static void dfsIterative(Graph graph, Integer vertex) {
        if (visited.contains(vertex)) return;
        Stack<Integer> stack = new Stack<>();
        stack.push(vertex);
        while (!stack.isEmpty()) {
            Integer v = stack.pop();
            if (!visited.contains(v)) {
                visited.add(v);
                stack.push(v);
                System.out.println(v);
            }
            for (Integer ver : graph.adj(v)) {
                if (!visited.contains(ver)) {
                    stack.push(ver);
                }
            }
        }
    }

}