package algorithms;

import java.util.*;

/**
 * An undirected Graph class.
 *
 * @author Wuxy
 * @version 1.0
 * @since 1.8
 */
public class Graph {
    private List<Object> vertex; // 点
    private int[][] edges; // 邻接矩阵
    private int numEdges; // 边的数目

    public static void main(String[] args) {
        /*
         * Graph:
         *   0- 1- 3
         *   |  \  \
         *   2   4- 7
         *   | \
         *   6- 5
         */
        Graph graph = new Graph(new int[][]{
                {0, 1, 1, 0, 0, 0, 0, 0},
                {1, 0, 0, 1, 1, 0, 0, 0},
                {1, 0, 0, 0, 0, 1, 1, 0},
                {0, 1, 0, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 1, 0},
                {0, 0, 1, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0}
        });
        graph.dfs();
        graph.bfs();
        graph.Dijkstra(2);
        graph.Floyed();
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

    /**
     * Building a graph from an adjacency matrix.
     *
     * @param edges adjacency matrix.
     */
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

    /**
     * Depth-first traversal of the graph.
     *
     * @param visited The array that records whether the node is visited.
     * @param i       node i.
     */
    private void dfs(boolean[] visited, int i) {
        System.out.print(vertex.get(i) + ", ");
        visited[i] = true;
        int w = getFirstNeighbor(i); // 每到一个节点都会先找第一条以该节点为起点的边
        while (w != -1) {
            if (!visited[w]) dfs(visited, w);
            w = getNextNeighbor(i, w);
        }
    }

    /**
     * Depth-first traversal of the graph.
     */
    public void dfs() {
        boolean[] visited = new boolean[vertex.size()];
        for (int i = 0; i < vertex.size(); i++) {
            if (!visited[i]) dfs(visited, i);
        }
        System.out.println();
    }

    /**
     * Breadth-first traversal of the graph.
     *
     * @param visited The array that records whether the node is visited.
     * @param i       node i, start form 0.
     */
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

    /**
     * Breadth-first traversal of the graph.
     */
    public void bfs() {
        boolean[] visited = new boolean[vertex.size()];
        for (int i = 0; i < vertex.size(); i++) {
            if (!visited[i]) bfs(visited, i);
        }
    }

    /**
     * Dijkstra algorithm is used to find the shortest distance from point m to other vertices.
     *
     * @param m the node m, start from 0.
     */
    public void Dijkstra(int m) {
        assert m < vertex.size();
        int n = vertex.size();
        int[] dists = new int[n]; // 距离
        boolean[] visited = new boolean[n]; // 访问数组
        int[] prevs = new int[n]; // 前驱结点
        int[][] matrix = Arrays.copyOf(this.edges, n); // 邻接矩阵
        for (int i = 0; i < n; i++) {
            if (matrix[m][i] == 0) {
                dists[i] = Integer.MAX_VALUE;
                prevs[i] = -1;
            } else {
                dists[i] = matrix[m][i];
                prevs[i] = m;
            }
        }
        visited[m] = true;
        // 每次确定当前距离最短的节点
        for (int i = 0; i < n - 1; i++) {
            int min = Integer.MAX_VALUE, cur = 0;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && dists[j] < min) {
                    min = dists[j];
                    cur = j;
                }
            }
            visited[cur] = true;
            // 更新其它节点到目标节点的最短距离
            for (int j = 0; j < n; j++) {
                if (!visited[j] && matrix[cur][j] != 0 && dists[j] > dists[cur] + matrix[cur][j]) {
                    dists[j] = dists[cur] + matrix[cur][j];
                    prevs[j] = cur;
                }
            }
        }
        System.out.println("Distances: " + Arrays.toString(dists));
        System.out.println("Previous: " + Arrays.toString(prevs));
    }

    /**
     * Reverse order print the Dijkstra shortest path from i to j.<br>
     * <p>
     * example: j -> x -> x -> ... -> i
     * </p>
     *
     * @param path path array.
     * @param i    start node i
     * @param j    end node j
     */
    public void printPath(int[] path, int i, int j) {
        System.out.print(j + "<-");
        while (path[j] != -1) {
            System.out.print((j = path[j]) + (i == j ? "" : "<-"));
        }
        System.out.println();
    }

    /**
     * Floyed algorithm is used to find the shortest distance on entire graph.
     */
    public void Floyed() {
        int n = vertex.size(), max = 10000000;
        int[][] matrix = Arrays.copyOf(this.edges, n), path = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = matrix[i][j] == 0 ? i == j ? 0 : max : matrix[i][j];
                path[i][j] = -1;
            }
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][k] + matrix[k][j] < matrix[i][j]) {
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                        path[i][j] = k;
                    }
                }
            }
        }
        System.out.println("Matrix: " + Arrays.deepToString(matrix));
        System.out.println("Path: " + Arrays.deepToString(path));
    }

    /**
     * Print the shortest Floyed path from i to j.
     * <p>
     * example: i -> x -> ... -> j
     * </p>
     *
     * @param path path array.
     * @param i    start node i
     * @param j    end node j
     */
    public void printPath(int[][] path, int i, int j) {
        System.out.print(i + "->");
        findPath(path, i, j);
        System.out.println(j);
    }

    /**
     * Recursively find the Floyed path.
     *
     * @param path path array
     * @param i    start node i
     * @param j    end node j
     */
    public void findPath(int[][] path, int i, int j) {
        if (path[i][j] == -1) return;
        findPath(path, i, path[i][j]);
        System.out.print(path[i][j] + "->");
        findPath(path, path[i][j], j);
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
