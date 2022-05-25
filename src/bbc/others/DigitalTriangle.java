package bbc.others;

import java.util.Scanner;

/**
 * 题目描述
 * 图片描述
 *
 * 上图给出了一个数字三角形。从三角形的顶部到底部有很多条不同的路径。对于每条路径，把路径上面的数加起来可以得到一个和，你的任务就是找到最大的和。
 *
 * 路径上的每一步只能从一个数走到下一层和它最近的左边的那个数或者右 边的那个数。此外，向左下走的次数与向右下走的次数相差不能超过 1。
 *
 * 输入描述
 * 输入的第一行包含一个整数 N\ (1 \leq N \leq 100)N (1≤N≤100)，表示三角形的行数。
 *
 * 下面的 NN 行给出数字三角形。数字三角形上的数都是 0 至 100 之间的整数。
 *
 * 输出描述
 * 输出一个整数，表示答案。
 *
 * 输入输出样例
 * 示例
 * 输入
 *
 * 5
 * 7
 * 3 8
 * 8 1 0
 * 2 7 4 4
 * 4 5 2 6 5
 * copy
 * 输出
 *
 * 27
 * copy
 * 运行限制
 * 最大运行时间：1s
 * 最大运行内存: 256M
 * 总通过次数: 3132  |  总提交次数: 3923  |  通过率: 79.8%
 *
 * 难度:   标签: 动态规划, 2020, 省赛
 *
 * link: https://www.lanqiao.cn/problems/505/learning/
 */
public class DigitalTriangle {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //在此输入您的代码...
        int n = scan.nextInt();
        int[][] dp = new int[n][n], array = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                array[i][j] = scan.nextInt();
            }
        }
        dp[0][0] = array[0][0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + array[i][0];
            for (int j = 1; j < n; j++) {
                dp[i][j] = array[i][j] + Math.max(dp[i - 1][j], dp[i - 1][j - 1]);
            }
        }
        // 关于向左向右步数不能相差1的理解：说明最后在最后一层一定会落到中间 所以只需判断最后一层为奇数：中位数
        if (n % 2 == 0) System.out.println(Math.max(dp[n - 1][n / 2 - 1], dp[n - 1][n / 2]));
        else System.out.println(dp[n - 1][n / 2]);
        scan.close();
    }
}
