package ojs.hdoj;

import java.util.Scanner;

/**
 * 每次向包里随机加入一枚钱币，直到包里某种钱币数量达到 100。
 */
public class P6829 {
    static int maxn = 105;
    static double[][][] dp = new double[maxn][maxn][maxn]; // dp[i][j][k] 表示有i枚金币，j枚银币，k枚铜币的期望

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt(), b = scan.nextInt(), c = scan.nextInt();
        for (int i = 99; i >= a; i--) {
            for (int j = 99; j >= b; j--) {
                for (int k = 99; k >= c; k--) {
                    double t = i + j + k;
                    dp[i][j][k] = i / t * (dp[i + 1][j][k] + 1) +
                            j / t * (dp[i][j + 1][k] + 1) +
                            k / t * (dp[i][j][k + 1] + 1);
                }
            }
        }
        System.out.printf("%.9f", dp[a][b][c]);
    }
}
