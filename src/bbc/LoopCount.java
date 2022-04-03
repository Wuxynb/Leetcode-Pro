package bbc;

import java.util.Scanner;

public class LoopCount {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //在此输入您的代码...
        int n = 21, m = 1 << n;
        boolean[][] edges = new boolean[n][n];
        long[][] dp = new long[m][n];
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
                            dp[i + (1 << k)][k] += dp[i][j];
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
