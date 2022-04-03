package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

enum Status {  // 节点对象的状态
    // 未被发现, 已被遍历
    UNDISCOVERED(0, "未被遍历"), VISITED(1, "已被遍历");
    final int key; // 键值
    final String desc; // 描述

    Status(int key, String desc) {
        this.key = key;
        this.desc = desc;
    }
}

@SuppressWarnings("Duplicates")
public class Dijkstra {
    public static void main(String[] args) {
        Graph<String> graph = new Graph<>(7);
        graph.setDatas(new String[]{"A", "B", "C", "D", "E", "F", "G"});
        int[][] matrix = {
                {0, 7, 3, 2, 2, 0, 0},
                {0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 4, 3, 0},
                {0, 0, 0, 0, 1, 10, 2},
                {0, 0, 0, 0, 0, 4, 2},
                {0, 0, 0, 0, 0, 0, 7},
                {0, 0, 0, 0, 0, 0, 0}};
        graph.setMatrix(matrix);
        graph.makeUndirected();
        for (int i = 0; i < 7; i++) {
            graph.initStatuses();
            graph.DijkstraPath(i);
        }

        graph.Dijkstra(new int[][]{
                {0, 1, 1, 0, 0, 0, 0, 0},
                {1, 0, 0, 1, 1, 0, 0, 0},
                {1, 0, 0, 0, 0, 1, 1, 0},
                {0, 1, 0, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 1, 0},
                {0, 0, 1, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0}}, 2);

        graph.Floyed(new int[][]{
                {0, 1, 1, 0, 0, 0, 0, 0},
                {1, 0, 0, 1, 1, 0, 0, 0},
                {1, 0, 0, 0, 0, 1, 1, 0},
                {0, 1, 0, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 1, 0},
                {0, 0, 1, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0}});
    }
}

class Graph<T> {
    private final int N; // N个节点
    public int[][] matrix;  // 邻接矩阵
    private final Status[] statuses;  // 保存每个节点的状态
    private T[] datas;  // 保存每个节点的数据

    @SuppressWarnings("all")
    public Graph(int N) {
        this.N = N;
        matrix = new int[N][N];
        statuses = new Status[N];
        datas = (T[]) new Object[N];  // 泛型数组实例化
        initStatuses();
    }

    /**
     * 用传进来的矩阵初始化图的邻接矩阵
     *
     * @param matrix 传进来用于初始化邻接矩阵的矩阵
     */
    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }


    /**
     * 使图变成无向图(把邻接矩阵镜像化)
     */
    public void makeUndirected() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] > 0 && matrix[i][j] != matrix[j][i]) {
                    matrix[j][i] = matrix[i][j];
                }
            }
        }
    }

    public void setDatas(T[] datas) {
        this.datas = datas;
    }

    /**
     * 初始化状态数组
     */
    public void initStatuses() {
        for (int i = 0; i < N; i++) {
            statuses[i] = Status.UNDISCOVERED;
        }
    }

    /**
     * 邻接矩阵保存的信息是从一个节点指向另一个节点的信息
     *
     * @param from   从这个节点
     * @param to     指向这个节点
     * @param weight 路径权重
     */
    public void setMatrix(int from, int to, int weight) {
        matrix[from][to] = weight;
    }

    /**
     * 最短路径-迪杰斯特拉算法(找出某个点到其他所有点的最短路径)
     *
     * @param index 指定某个点
     */
    public void DijkstraPath(int index) {
        // 每一轮选出的路径权值最小的节点, 则不可能再找出另外的路径权值更小
        // 比如从A到D是2, 则这一轮取出D节点, 假如有A能通过另外的节点到达D并且更短,
        // 比如A-1-E-1-D, 则上一轮取出的节点将是E而不是D
        // 数组存放该点到各个点的路径权值
        int[] weights = new int[N];
        // 将每个默认权值设置为整型最大值
        for (int i = 0; i < N; i++) {
            weights[i] = Integer.MAX_VALUE;
        }
        // 数组记录指定节点到每个节点的最短路径中, 终点节点的前驱节点
        // 动态规划: 找到到达某个节点的最短路径, 先找到到达他的上一个节点的最短路径
        int[] prevs = new int[N];
        prevs[index] = -1;  // 负数表示该点没有前驱
        // 循环所用的辅助索引
        int from = index;
        // 只要不是全部被遍历
        while (!isAllVisited()) {
            // 将这个节点设置为已访问
            statuses[from] = Status.VISITED;
            // 查看邻接矩阵中与指定节点邻接的节点
            for (int i = 0; i < N; i++) {
                // 可能的新路径权值: 从最开始的指定起点到本轮起点到该节点的路径权值总和
                int newWeight;
                if (weights[from] == Integer.MAX_VALUE) {
                    newWeight = matrix[from][i];
                } else {
                    newWeight = weights[from] + matrix[from][i];
                }
                // 如果节点未访问, 且是邻接节点
                if (statuses[i] == Status.UNDISCOVERED && matrix[from][i] > 0
                        // 并且如果小于weights中记录的该节点原来的路径权值
                        && newWeight < weights[i]) {
                    // 则更新该节点的最小路径值, 更新该节点的前驱为本轮起点
                    weights[i] = newWeight;
                    prevs[i] = from;
                }
            }
            // 下轮起点from设置为: weights数组中数值最小的并且未访问的节点
            from = indexOfMin(weights);
        }
        // 输出结果
        System.out.println("指定起点为:" + datas[index]);
        for (int i = 0; i < N; i++) {
            if (i != index) {  // 除去最开始指定的起点
                List<Integer> nodesInPath = allPrevs(prevs, i);
                System.out.print("起点" + datas[index] + "到" + datas[i] + "点的最短路径是: " + datas[index]);
                for (int j : nodesInPath) {
                    System.out.print("-" + matrix[prevs[j]][j] + "-" + datas[j]);
                }
                System.out.println("-" + matrix[prevs[i]][i] + "-" + datas[i] + ", 路径权值总和为: " + weights[i]);
            }
        }
    }

    /**
     * 指定节点, 按路径顺序返回该节点的所有前驱节点
     *
     * @param prevs 记录前驱节点的数组
     * @param index 指定节点
     * @return java.util.List<java.lang.Integer>
     */
    private List<Integer> allPrevs(int[] prevs, int index) {
        // 记录指定节点到达指定起点的最短路径沿途的节点
        Stack<Integer> prevStack = new Stack<>();
        int prev = prevs[index];
        // 前面设置的算法最开始指定的起点的前驱索引为-1在这里起作用
        // 只要前驱的前驱索引不为最开始指定的起点
        while (prevs[prev] != -1) {
            // 把前驱索引加入栈
            prevStack.add(prev);
            // 下次循环要检查此次循环前驱节点的前驱节点, 所以更新变量
            prev = prevs[prev];
        }

        // 方便遍历, 倒序输出
        List<Integer> result = new ArrayList<>();
        while (!prevStack.isEmpty()) {
            result.add(prevStack.pop());
        }
        return result;
    }

    /**
     * 检查是否全部被遍历(只要有一个是未被遍历返回false)
     *
     * @return boolean
     */
    private boolean isAllVisited() {
        for (Status status : statuses) {
            if (status == Status.UNDISCOVERED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 找到数组中最小的值的索引
     *
     * @return int
     */
    private int indexOfMin(int[] nums) {
        List<Integer> remain = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (statuses[i] == Status.UNDISCOVERED) {
                remain.add(i);
            }
        }
        if (remain.size() == 0) {
            return 0;  // 这里返回什么都行, 因为所有节点会在下一循环全部设置为已访问, 从而循环内无任何操作
        }
        int minIndex = remain.get(0);
        for (int j : remain) {
            if (nums[j] < nums[minIndex]) {
                minIndex = j;
            }
        }
        return minIndex;
    }

    /**
     * 迪杰斯特拉最短路径算法
     *
     * @param matrix 图的邻接矩阵
     * @param m      起始节点 m ，m 从 0 开始
     */
    @SuppressWarnings("Duplicates")
    public void Dijkstra(int[][] matrix, int m) {
        int n = matrix.length, max = (int) 1e7;
        int[] dists = new int[n], prevs = new int[n];
        boolean[] visited = new boolean[n];
        for (int[] mat : matrix) {
            for (int i = 0; i < n; i++) {
                mat[i] = mat[i] == 0 ? max : mat[i]; // 无边时邻接矩阵值置为max
            }
        }
        for (int i = 0; i < n; i++) {
            dists[i] = matrix[m][i];
            prevs[i] = matrix[m][i] == max ? -1 : m;
        }
        visited[m] = true;
        // 为每个节点寻找到m的最短路径
        for (int i = 0; i < n - 1; i++) {
            int min = max, cur = 0;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && dists[j] < min) {
                    min = dists[j];
                    cur = j;
                }
            }
            visited[cur] = true; // 找到当前距离最短的节点
            // 继续判断从该节点往后出发的最短路径
            for (int j = 0; j < n; j++) {
                // 没有拜访过节点j并且cur与j存在边且距离更小
                if (!visited[j] && dists[j] > dists[cur] + matrix[cur][j]) {
                    dists[j] = dists[cur] + matrix[cur][j];
                    prevs[j] = cur;
                }
            }
        }
        System.out.println("Distances: " + Arrays.toString(dists));
        System.out.println("Path: " + Arrays.toString(prevs));
        printPath(prevs, m, n - 1);
    }

    public void printPath(int[] path, int i, int j) {
        System.out.print(j + "<-");
        while (path[j] != -1) {
            System.out.print((j = path[j]) + (i == j ? "" : "<-"));
        }
        System.out.println();
    }

    @SuppressWarnings("Duplicates")
    public void Floyed(int[][] matrix) {
        int n = matrix.length, max = 10000000;
        int[][] path = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                path[i][j] = -1; // i,j之间没有节点则为-1（i，j无边或有边）
                // 不存在边时设置为最大值（若此时为对角线元素，则设置为0）
                matrix[i][j] = matrix[i][j] == 0 ? i == j ? 0 : max : matrix[i][j];
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

        System.out.println(Arrays.deepToString(matrix));
        System.out.println(Arrays.deepToString(path));
        printPath(path, 2, n - 1);
    }

    public void printPath(int[][] path, int i, int j) {
        System.out.print(i + "->");
        findPath(path, i, j);
        System.out.println(j);
    }

    public void findPath(int[][] path, int i, int j) {
        if (path[i][j] == -1) return;
        findPath(path, i, path[i][j]);
        System.out.print(path[i][j] + "->");
        findPath(path, path[i][j], j);
    }
}
