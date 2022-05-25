package ojs.codeforce;

import java.util.Scanner;

/**
 * 伊利亚厌倦了体育节目，离开了大学，在地铁找到了一份工作。他被赋予了确定自动扶梯负载系数的任务。
 * <p>
 * 让我们假设n个人排队等候自动扶梯。在每秒钟，以下两种可能性中的一种发生：要么队列中的第一个人以概率p进入自动扶梯，要么队列中的第一个人不以概率（1-p）
 * 移动 他对自动扶梯的恐惧使他瘫痪，整个队伍都在他身后等待。
 * <p>
 * 正式地说，在指数从1到i的人之前，排队的第i个人不能进入自动扶梯1包括输入。在一秒钟内，只有一个人可以进入自动扶梯。自动扶梯是无限的，所以如果一个人进
 * 入它，他永远不会离开它，也就是说，他将在接下来的任何一秒站在自动扶梯上。伊利亚需要计算t秒后站在自动扶梯上的人数的预期值。
 * <p>
 * 你的任务是帮助他解决这个复杂的任务。
 * <p>
 * 输入
 * <p>
 * 输入的第一行包含三个数字n，p，t（1 <= n, t <= 2000，0 <= p <= 1） 。数字n和t是整数，数字p是实数，在小数点后正好有两位数。
 * <p>
 * 输出
 * <p>
 * 打印一个实数-t秒后站在自动扶梯上的预期人数。绝对或相对误差不得超过10^-6.
 * <p>
 * 示例
 * <p>
 * input
 * <p>
 * 1 0.50 1
 * <p>
 * output
 * <p>
 * 0.5
 * <p>
 * input
 * <p>
 * 1 0.50 4
 * <p>
 * output
 * <p>
 * 0.9375
 * <p>
 * input
 * <p>
 * 4 0.20 2
 * <p>
 * 0.4
 */
public class P518D {
    static int N = 2005;
    static double[][] dp = new double[N][N]; // dp[i][j]表示在i时刻电梯上有j个人的概率

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(); double p = scan.nextDouble(); int t = scan.nextInt();
        dp[0][0] = 1.;
        for (int i = 0; i < t; i++) {
            dp[i + 1][n] += dp[i][n];
            for (int j = 0; j < n; j++) {
                if (dp[i][j] > 1e-10) {
                    dp[i + 1][j + 1] += dp[i][j] * p;
                    dp[i + 1][j] += dp[i][j] * (1 - p);
                }
            }
        }
        double ans = 0;
        for (int i = 0; i <= n; i++) ans += i * dp[t][i];
        System.out.println(ans);
    }
}
