package java.graph;

import java.util.*;

class Path {
    private static Set<Integer> visited = new HashSet<>();
    private static HashMap<Integer, Integer> parent = new HashMap();

    public static void path(Graph graph, Integer from, Integer to) {
        bfs(graph, from);
        Integer vertex = to;
        while (vertex != null) {
            System.out.println( vertex + " ");
            vertex = parent.get(vertex);
        }
    }

    // Can be modified to quit when find the (to)
    private static void bfs(Graph graph, Integer vertex) {
        if (visited.contains(vertex)) return;
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(vertex);
        visited.add(vertex);

        while(!queue.isEmpty()){
            Integer v = queue.poll();
            // System.out.println(v);

            for (Integer ver : graph.adj(v)) {
                if (!visited.contains(ver)){
                    visited.add(ver);
                    queue.offer(ver);
                    parent.put(ver, v);
                }
            }
        }
    }

    public static Integer furthestVertix(Graph graph, Integer vertex) {
        if (graph.vertices().size() == 1) return vertex;

        Integer furthest = null;

        Queue<Integer> queue = new LinkedList<>();

        queue.offer(vertex);
        visited.add(vertex);

        while(!queue.isEmpty()){
            Integer v = queue.poll();
            // System.out.println(v);

            for (Integer ver : graph.adj(v)) {
                if (!visited.contains(ver)){
                    visited.add(ver);
                    queue.offer(ver);
                    parent.put(ver, v);
                    furthest = ver;
                }
            }
        }
        return furthest;
    }
}
