package ojs.acwing;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P217_ {
    static int maxn = 100005;
    static double[] dp = new double[maxn];
    static int[] inDegs = new int[maxn];
    static int[] outDegs = new int[maxn];
    static List<int[]>[] adjacency;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(), m = scan.nextInt();
        adjacency = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) adjacency[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int u = scan.nextInt() - 1, v = scan.nextInt() - 1, w = scan.nextInt();
            adjacency[u].add(new int[]{v, w});
            inDegs[v]++;
            outDegs[u]++;
        }
        solve();
    }

    public static void solve() {
        int[] q = new int[maxn]; // 存储入度为0的点（拓扑排序）-- 队列角色
        int l = 0, r = 0;
        q[r++] = 0;
        double ans = 0.;
        dp[0] = 1.; // 初始化，第0个起始点的期望经过次数为 1
        while (r - l >= 1) {
            int u = q[l++];
            for (int[] vw : adjacency[u]) {
                int v = vw[0], w = vw[1];
                dp[v] += dp[u] / outDegs[u]; // 计算点v的期望经过次数
                ans += dp[u] * w / (double) outDegs[u]; // 计算边 (u,v) 的贡献值
                if (--inDegs[v] == 0) q[r++] = v; // 当点v的入度为0时，选择从点v开始继续计算
            }
        }
        System.out.printf("%.2f", ans);
    }
}
