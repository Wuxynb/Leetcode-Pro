package ojs.luogu;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目背景
 * <p>
 * windy 定义了一种 windy 数。
 * <p>
 * 题目描述
 * <p>
 * 不含前导零且相邻两个数字之差至少为 2 的正整数被称为 windy 数。windy 想知道，在 a 和 b 之间，包括 a 和 b ，总共有多少个 windy 数？
 * <p>
 * 输入格式
 * <p>
 * 输入只有一行两个整数，分别表示 a 和bb。
 * <p>
 * 输出格式
 * <p>
 * 输出一行一个整数表示答案。
 * <p>
 * 输入输出样例
 * <p>
 * 输入 #1复制
 * <p>
 * 1 10
 * <p>
 * 输出 #1复制
 * <p>
 * 9
 * <p>
 * 输入 #2复制
 * <p>
 * 25 50
 * <p>
 * 输出 #2复制
 * <p>
 * 20
 * <p>
 * 说明/提示
 * <p>
 * 数据规模与约定
 * <p>
 * 对于全部的测试点，保证 1≤a≤b≤2×10^9。
 */
public class P2657 {
    static int N = 11;
    static int[] array = new int[N];
    static int[][] dp = new int[N][10]; // dp[N][pre]

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt(), b = scan.nextInt();
        System.out.println(solve(b) - solve(a - 1));
    }

    public static int solve(int n) {
        for (int[] ints : dp) Arrays.fill(ints, -1);
        int pos = 0;
        while (n != 0) {
            array[++pos] = n % 10;
            n /= 10;
        }
        return dfs(pos, 0, true, true);
    }

    public static int dfs(int pos, int pre, boolean lead, boolean limit) {
        if (pos == 0) return 1;
        if (dp[pos][pre] != -1 && !lead && !limit) return dp[pos][pre];
        int res = 0, up = limit ? array[pos] : 9;
        for (int i = 0; i <= up; i++) {
            if (!lead && Math.abs(i - pre) < 2) continue;
            res += dfs(pos - 1, i, i == 0 && lead, i == up && limit);
        }
        if (!lead && !limit) dp[pos][pre] = res;
        return res;
    }
}
