package ojs.codeforce;

import java.util.Scanner;

public class P518D_ {
    static int maxn = 2005;
    static double[][] dp = new double[maxn][maxn];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(); double p = scan.nextDouble(); int t = scan.nextInt();
        double ans = 0.;
        dp[0][0] = 1.;
        for (int i = 0; i < t; i++) {
            dp[i + 1][n] += dp[i][n];
            for (int j = 0; j < n; j++) {
                if (dp[i][j] > 1e-10) {
                    dp[i + 1][j + 1] += dp[i][j] * p;
                    dp[i + 1][j] += dp[i][j] * (1 - p);
                    ans += dp[i][j] * p;
                }
            }
        }
        System.out.println(ans);
    }
}
