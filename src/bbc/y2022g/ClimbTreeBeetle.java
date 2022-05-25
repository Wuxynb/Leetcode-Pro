package bbc.y2022g;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * <p>
 * 有一只甲壳虫想要爬上一颗高度为n 的树，它一开始位于树根，高度为0。
 * <p>
 * 当它尝试从高度 i-1 爬到高度为i 的位置时有 Pi 的概率会掉回树根。
 * <p>
 * 求它从树根爬到树顶时，经过的时间的期望值是多少。
 * <p>
 * 输入格式
 * <p>
 * 输入第一行包含一个整数 n 表示树的高度。
 * <p>
 * 接下来 n 行每行包含两个整数xi, yi，用一个空格分隔，表示Pi = xi/yi。
 * <p>
 * 20%的测试数据：1≤n≤2，1≤xi< yi≤20;
 * <p>
 * 50%的测试数据：1≤n≤500，1≤xi< yi<200；
 * <p>
 * 100%的测试数据：1≤n≤100000，1≤xi< yi≤10^9。
 * <p>
 * 输出格式
 * <p>
 * 输出一行包含一个整数表示答案，答案是一个有理数，请输出答案对质数 998244353 取模的结果。
 * <p>
 * 其中有理数 a/b 对质数 P 取模的结果是整数 c 满足0 ≤ c < P 且c · b ≡ a (mod P)。
 * <p>
 * 输入样例 复制
 * <p>
 * 样例1：
 * <p>
 * 1
 * <p>
 * 1 2
 * <p>
 * <p>
 * 样例2：
 * <p>
 * 3
 * <p>
 * 1 2
 * <p>
 * 3 5
 * <p>
 * 7 11
 * <p>
 * 输出样例 复制
 * <p>
 * 样例1：
 * <p>
 * 2
 * <p>
 * <p>
 * 样例2：
 * <p>
 * 623902744
 * <p>
 * 分类标签
 * <p>
 * 进阶题 动态规划 数论 期望DP
 */
public class ClimbTreeBeetle {
    static int maxn = 100005;
    static double[] dp = new double[maxn];  // dp[i] 表示从i到终点的花费的期望时间
    static long mod = 998244353L;
    static double[] p = new double[maxn]; // pi 表示在 高度p处掉下去的概率

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Arrays.fill(dp, -1);
        int n = scan.nextInt();
        dp[n] = 0;
        for (int i = 0; i <= n; i++) {
            long x = scan.nextLong(), y = scan.nextLong();
            double pi = x / (double) y;
            p[i] = pi;
        }
    }

    /*
     *   dp[i] 表示从i到终点的花费的期望时间
     *   当前在第 i-1 层，有两种情况：要么到第 i + 1 层，要么回到第 0 层
     *      1、到达第 i 层有 (1-pi)dp[i] + 1
     *      2、回到第 0 层有 pi * dp[0]
     *   故有状态转移方程：
     *      dp[i-1] = pi * dp[0] + (1-pi)dp[i] + 1
     *      dp[n] = 0
     *    递推得：
     *      dp[0] = p1 * dp[0] + (1-p1) * dp[1] + 1
     */
    public static double dfs(int n) {
        if (dp[n] != -1) return dp[n];
        return dp[n] = p[n + 1] * dfs(0) + (1 - p[n + 1]) * dfs(n + 1) + 1;
    }
}
