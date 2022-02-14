package offers;

import java.util.Arrays;

/**
 * 剑指 Offer 60. n个骰子的点数
 * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
 * <p>
 * <p>
 * <p>
 * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1
 * 输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
 * 示例 2:
 * <p>
 * 输入: 2
 * 输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= n <= 11
 * <p>
 * 链接：https://leetcode-cn.com/problems/nge-tou-zi-de-dian-shu-lcof/
 */
public class Offer60 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(dicesProbabilityBF(2)));
        System.out.println(Arrays.toString(dicesProbability(2)));
        System.out.println(Arrays.toString(dicesProbabilityDp(2)));
    }

    public static double[] dicesProbability(int n) {
        double[] dp = new double[6];
        Arrays.fill(dp, 1. / 6.);
        for (int i = 2; i <= n; i++) {
            double[] temp = new double[5 * i + 1];
            for (int j = 0; j < dp.length; j++) {
                for (int k = 0; k < 6; k++) {
                    temp[j + k] += dp[j] / 6.;
                }
            }
            dp = temp;
        }
        return dp;
    }

    public static double[] dicesProbabilityDp(int n) {
        double[][] dp = new double[n + 1][n * 6 + 1];
        double[] ans = new double[5 * n + 1];
        for (int i = 1; i <= 6; i++) dp[1][i] = 1. / 6.;
        for (int i = 2; i <= n; i++) {
            for (int j = i; j <= i * 6; j++) {
                for (int k = 1; k <= 6 && j > k; k++) {
                    dp[i][j] += dp[i - 1][j - k] / 6.;
                }
            }
        }
        for (int i = 0; i < ans.length; i++) ans[i] = dp[n][n + i];
        return ans;
    }

    public static double[] dicesProbabilityBF(int n) {
        double[] dp = new double[n * 6], ans = new double[5 * n + 1];
        dfs(dp, n, 0, 0);
        for (int i = 0; i < dp.length; i++) dp[i] = dp[i] / Math.pow(6, n);
        for (int i = 0; i < ans.length; i++) ans[ans.length - 1 - i] = dp[n * 6 - 1 - i];
        return ans;
    }

    public static void dfs(double[] dp, int n, int i, int sum) {
        if (i == n) {
            dp[sum - 1]++;
            return;
        }
        for (int j = 1; j <= 6; j++) dfs(dp, n, i + 1, sum + j);
    }
}
