package java.graph;

import java.util.*;

class Graph {
    private HashMap<Integer, List<Integer>> adj = new HashMap<>();

    void add(Integer from, Integer to) {
        List<Integer> list = adj.get(from);
        if (list == null) {
            list = new ArrayList();
            adj.put(from, list);
        }
        list.add(to);

        list = adj.get(to);
        if (list == null) {
            list = new ArrayList();
            adj.put(to, list);
        }
        list.add(from);
    }

    List<Integer> adj(Integer from) {
        return adj.get(from);
    }

    List<Integer> vertices() {
        ArrayList<Integer> list = new ArrayList();
        for (Integer vertex : adj.keySet()) {
            list.add(vertex);
        }
        return list;
    }

    int V() {
        return adj.size();
    }

    int E() {
        int sum = 0;
        for (List edges : adj.values()) {
            sum += edges.size();
        }
        return sum;
    }
}

