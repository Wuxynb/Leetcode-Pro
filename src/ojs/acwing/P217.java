package ojs.acwing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 217. 绿豆蛙的归宿
 * <p>
 * 给出一个有向无环的连通图，起点为 1，终点为 N，每条边都有一个长度。
 * <p>
 * 数据保证从起点出发能够到达图中所有的点，图中所有的点也都能够到达终点。
 * <p>
 * 绿豆蛙从起点出发，走向终点。
 * <p>
 * 到达每一个顶点时，如果有 K 条离开该点的道路，绿豆蛙可以选择任意一条道路离开该点，并且走向每条路的概率为 1/K。
 * <p>
 * 现在绿豆蛙想知道，从起点走到终点所经过的路径总长度的期望是多少？
 * <p>
 * 输入格式
 * <p>
 * 第一行: 两个整数 N，M，代表图中有 N 个点、M 条边。
 * <p>
 * 第二行到第 1+M 行: 每行 3 个整数 a,b,c，代表从 a 到 b 有一条长度为 c 的有向边。
 * <p>
 * 输出格式
 * <p>
 * 输出从起点到终点路径总长度的期望值，结果四舍五入保留两位小数。
 * <p>
 * 数据范围
 * <p>
 * 1≤N≤10^5,
 * <p>
 * 1≤M≤2N
 * <p>
 * 输入样例：
 * <p>
 * 4 4
 * <p>
 * 1 2 1
 * <p>
 * 1 3 2
 * <p>
 * 2 3 3
 * <p>
 * 3 4 4
 * <p>
 * 输出样例：
 * <p>
 * 7.00
 */
public class P217 {
    static int maxn = 100005;
    static double[] dp = new double[maxn]; // dp[i]，表示从 i 到 n 所经过的路径总长度
    static int[] degrees = new int[maxn];
    static List<int[]>[] adjacency;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Arrays.fill(dp, -1);
        int n = scan.nextInt(), m = scan.nextInt();
        adjacency = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) adjacency[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int u = scan.nextInt(), v = scan.nextInt(), w = scan.nextInt();
            adjacency[u].add(new int[]{v, w});
            degrees[u]++;
        }
        System.out.printf("%.2f", dfs(1));
    }

    public static double dfs(int u) {
        if (dp[u] != -1.) return dp[u];
        dp[u] = 0;
        for (int[] vw : adjacency[u]) {
            int v = vw[0], w = vw[1];
            dp[u] +=  (w + dfs(v)) / degrees[u];
        }
        return dp[u];
    }
}
