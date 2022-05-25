package bbc.others;

import java.util.Scanner;

/**
 * 回路计算
 * 蓝桥学院由21 栋教学楼组成，教学楼编号1 到21。
 * 对于两栋教学楼a 和b，当a 和b 互质时，a 和b 之间有一条走廊直接相连，两个方向皆可通行，否则没有直接连接的走廊。
 * 小蓝现在在第一栋教学楼，他想要访问每栋教学楼正好一次，最终回到第一栋教学楼（即走一条哈密尔顿回路），请问他有多少种不同的访问方案？
 * 两个访问方案不同是指存在某个i，小蓝在两个访问方法中访问完教学楼i 后访问了不同的教学楼。
 * 提示：建议使用计算机编程解决问题。
 * <p>
 * 状态DP求解
 */
public class LoopCount {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //在此输入您的代码...
        // 状态DP
        int n = 21, m = 1 << n;
        boolean[][] edges = new boolean[n][n];
        long[][] dp = new long[m][n]; // dp[i][j] -- 状态 i，此时在 j 点，所走的方案数
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                edges[i - 1][j - 1] = gcd(i, j) == 1;
            }
        }
        dp[1][0] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 1) { // 如果当前状态存在楼j
                    for (int k = 0; k < n; k++) { // 寻找楼j能到达的下一楼
                        if ((i >> k & 1) == 0 && edges[j][k]) { // 如果楼k未被访问，并且楼j、k有走廊
                            dp[i | (1 << k)][k] += dp[i][j]; // dp[i + (1 << k)] += dp[i][j]
                        }
                    }
                }
            }
        }
        long ans = 0;
        // 将以i为结尾点的回路求和
        for (int i = 0; i < n; i++) ans += dp[m - 1][i];
        System.out.println(ans);
        scan.close();
    }

    public static int gcd(int b, int a) {
        return a == 0 ? b : gcd(a, b % a);
    }
}
