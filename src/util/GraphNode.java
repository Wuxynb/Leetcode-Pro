package util;

import java.util.*;

/**
 * A utility class GraphNode.
 *
 * @author Wuxy
 * @version 1.0
 * @date 2021/10/16
 * @see GraphNode
 * @since 1.8
 */
public class GraphNode {
    public int val;
    public List<GraphNode> neighbors;

    public GraphNode() {
        this.val = 0;
        this.neighbors = new ArrayList<>();
    }

    public GraphNode(int _val) {
        this.val = _val;
        this.neighbors = new ArrayList<>();
    }

    public GraphNode(int _val, ArrayList<GraphNode> _neighbors) {
        this.val = _val;
        this.neighbors = _neighbors;
    }

    public static void bfs(GraphNode node) {
        if (node == null) return;
        Queue<GraphNode> queue = new LinkedList<>();
        Set<GraphNode> visited = new HashSet<>();
        queue.offer(node);
        visited.add(node);
        while (!queue.isEmpty()) {
            node = queue.poll();
            System.out.print(node.val + ", ");
            for (GraphNode n : node.neighbors) {
                if (!visited.contains(n)) {
                    queue.offer(n);
                    visited.add(n);
                }
            }
        }
        System.out.println();
    }

    public static void dfs(GraphNode node) {
        if (node == null) return;
        Set<GraphNode> visited = new HashSet<>();
        dfs(node, visited);
        System.out.println();
    }

    public static void dfs(GraphNode node, Set<GraphNode> visited) {
        if (node != null && !visited.contains(node)) {
            System.out.print(node.val + ", ");
            visited.add(node);
        }
        assert node != null;
        for (GraphNode n : node.neighbors) {
            if (!visited.contains(n)) dfs(n, visited);
        }
    }
}
