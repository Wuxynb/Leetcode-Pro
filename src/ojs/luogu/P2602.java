package ojs.luogu;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * <p>
 * 给定两个正整数 a 和 b，求在 [a,b 中的所有整数中，每个数码(digit)各出现了多少次。
 * <p>
 * 输入格式
 * <p>
 * 仅包含一行两个整数 a,b，含义如上所述。
 * <p>
 * 输出格式
 * <p>
 * 包含一行十个整数，分别表示 0∼9 在 [a,b] 中出现了多少次。
 * <p>
 * 输入输出样例
 * <p>
 * 输入 #1复制
 * <p>
 * 1 99
 * <p>
 * 输出 #1复制
 * <p>
 * 9 20 20 20 20 20 20 20 20 20
 * <p>
 * 说明/提示
 * <p>
 * 数据规模与约定
 * <p>
 * 对于 30% 的数据，保证 a≤b≤10^6；
 * <p>
 * 对于 100% 的数据，保证 1≤a≤b≤10^12。
 */
public class P2602 {
    static int N = 15;
    static int[] array = new int[N];
    static long[][] dp = new long[N][N];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        long a = scan.nextLong(), b = scan.nextLong();
        long[] arr1 = solve(b), arr2 = solve(a - 1);
        for (int i = 0; i < 10; i++) {
            System.out.print((arr1[i] - arr2[i]) + " ");
        }
    }

    public static long[] solve(long n) {
        long[] ans = new long[10];
        int pos = 0;
        while (n != 0) {
            array[++pos] = (int) (n % 10);
            n /= 10;
        }
        for (int i = 0; i < 10; i++) {
            for (long[] ints : dp) Arrays.fill(ints, -1);
            ans[i] = dfs(pos, 0, i, i == 0, true);
        }
        return ans;
    }

    public static long dfs(int pos, int sum, int t, boolean lead, boolean limit) {
        if (pos == 0) return sum;
        if (dp[pos][sum] != -1 && !lead && !limit) return dp[pos][sum];
        long res = 0, up = limit ? array[pos] : 9;
        for (int i = 0; i <= up; i++) {
            int s = sum;
            if (t != 0) {
                if (i == t) s++;
                res += dfs(pos - 1, s, t, lead, i == up && limit);
            } else {
                if (i == 0 && !lead) s++;
                res += dfs(pos - 1, s, t, i == 0 && lead, i == up && limit);
            }
        }
        if (!lead && !limit) dp[pos][sum] = res;
        return res;
    }
}
