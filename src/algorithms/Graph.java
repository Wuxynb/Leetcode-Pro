package algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * An undirected Graph class.
 */
public class Graph {
    private List<Object> vertex; // 点
    private int[][] edges; // 邻接矩阵
    private int numEdges; // 边的数目

    public static void main(String[] args) {
        Graph graph = new Graph(new int[][]{
                {0, 1, 1, 0, 0, 0, 0, 0},
                {1, 0, 0, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 1, 0},
                {0, 1, 0, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 1, 0},
                {0, 0, 1, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0}
        });
        graph.dfs();
        graph.bfs();
    }

    /**
     * Construct a Graph with a specified number of nodes.
     *
     * @param n the number of nodes.
     */
    public Graph(int n) {
        edges = new int[n][n];
        vertex = new ArrayList<>(n);
        numEdges = 0;
    }

    public Graph(int[][] edges) {
        assert edges.length == edges[0].length;
        this.edges = edges;
        vertex = new ArrayList<>(edges.length);
        for (int i = 1; i <= edges.length; i++) {
            vertex.add(i);
        }
        for (int[] edge : edges) {
            for (int j = 0; j < edges[0].length; j++) {
                if (edge[j] > 0) numEdges++;
            }
        }
    }

    /**
     * Obtain the index of the first neighbor node through the index of the current node.
     *
     * @param v the index of the current node.
     * @return the index of the first neighbor node.
     */
    public int getFirstNeighbor(int v) {
        for (int j = 0; j < vertex.size(); j++) {
            if (edges[v][j] > 0) return j;
        }
        return -1;
    }

    /**
     * Obtain the index of the next node through the index of the last node.
     *
     * @param v1 the index of the current node
     * @param v2 the index of the previous node of the current node
     * @return the next neighbor index of the previous node of the current node
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertex.size(); j++) {
            if (edges[v1][j] > 0) return j;
        }
        return -1;
    }

    private void dfs(boolean[] visited, int i) {
        System.out.print(vertex.get(i) + ", ");
        visited[i] = true;
        int w = getFirstNeighbor(i); // 每到一个节点都会先找第一条以该节点为起点的边
        while (w != -1) {
            if (!visited[w]) dfs(visited, w);
            w = getNextNeighbor(i, w);
        }
    }

    public void dfs() {
        boolean[] visited = new boolean[vertex.size()];
        for (int i = 0; i < vertex.size(); i++) {
            if (!visited[i]) dfs(visited, i);
        }
        System.out.println();
    }

    private void bfs(boolean[] visited, int i) {
        int u, w;
        Queue<Object> queue = new LinkedList<>();
        // 访问节点i
        System.out.print(vertex.get(i) + ", ");
        visited[i] = true;
        queue.offer(i);
        while (!queue.isEmpty()) {
            u = (int) queue.poll();
            w = getFirstNeighbor(u);
            while (w != -1) {
                if (!visited[w]) {
                    // 访问该节点
                    System.out.print(vertex.get(w) + ", ");
                    visited[w] = true;
                    queue.offer(w);
                }
                w = getNextNeighbor(u, w);
            }
        }
    }

    public void bfs() {
        boolean[] visited = new boolean[vertex.size()];
        for (int i = 0; i < vertex.size(); i++) {
            if (!visited[i]) bfs(visited, i);
        }
    }

    public List<Object> getVertex() {
        return vertex;
    }

    public void setVertex(List<Object> vertex) {
        this.vertex = vertex;
    }

    public int[][] getEdges() {
        return edges;
    }

    public void setEdges(int[][] edges) {
        this.edges = edges;
    }

    public int getNumEdges() {
        return numEdges;
    }

    public void setNumEdges(int numEdges) {
        this.numEdges = numEdges;
    }
}
