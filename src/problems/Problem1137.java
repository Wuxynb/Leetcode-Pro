package problems;

/**
 * 1137. 第 N 个泰波那契数
 * 泰波那契序列 Tn 定义如下：
 *
 * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
 *
 * 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 4
 * 输出：4
 * 解释：
 * T_3 = 0 + 1 + 1 = 2
 * T_4 = 1 + 1 + 2 = 4
 * 示例 2：
 *
 * 输入：n = 25
 * 输出：1389537
 *
 * 链接：https://leetcode-cn.com/problems/n-th-tribonacci-number/
 */
public class Problem1137 {

    public static void main(String[] args) {
        System.out.println(tribonacci(15));
        System.out.println(tribonacciPlus(15));
    }

     public static int tribonacci(int n) {
         if (n < 2) return n;
         if (n == 2) return 1;
         int[] F = new int[n + 1];
         F[0] = 0; F[1] = 1; F[2] = 1;
         for (int i = 3; i < n + 1; i++) {
             F[i] = F[i - 1] + F[i - 2] + F[i - 3];
         }

         return F[n];
     }

    public static int tribonacciPlus(int n) {
        if (n < 2) return n;
        if (n == 2) return 1;
        int a = 0, b = 1, c = 1;
        for (int i = 3; i < n + 1; i++) {
            c = c + b + a;
            b = c - b - a;
            a = c - b - a;
        }
        return c;
    }
}
