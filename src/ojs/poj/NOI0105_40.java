package ojs.poj;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 40:数1的个数
 * <p>
 * 查看提交统计提问
 * <p>
 * 总时间限制: 1000ms 内存限制: 65536kB
 * <p>
 * 描述
 * <p>
 * 给定一个十进制正整数n，写下从1到n的所有整数，然后数一下其中出现的数字“1”的个数。
 * <p>
 * 例如当n=2时，写下1,2。这样只出现了1个“1”；当n=12时，写下1，2，3，4，5，6，7，8，9，10，11，12。这样出现了5个“1”。
 * <p>
 * 输入
 * <p>
 * 正整数n。1 <= n <= 10000。
 * <p>
 * 输出
 * <p>
 * 一个正整数，即“1”的个数。
 * <p>
 * 样例输入
 * <p>
 * 12
 * <p>
 * 样例输出
 * <p>
 * 5
 */
public class NOI0105_40 {
    static int N = 6;
    static int[] array = new int[N];
    static int[][] dp = new int[N][N]; // dp[i][j] 表示当前枚举到第i位数时，已经统计了有j个1，此时出现的1的个数

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println(solve(scan.nextInt()));
    }

    public static int solve(int n) {
        for (int[] ints : dp) Arrays.fill(ints, -1);
        int pos = 0;
        while (n != 0) {
            array[pos++] = n % 10;
            n /= 10;
        }
        return dfs(pos - 1, 0, true);
    }

    public static int dfs(int pos, int sum, boolean limit) {
        if (pos == -1) return sum;
        if (dp[pos][sum] != -1 && !limit) return dp[pos][sum];
        int res = 0, up = limit ? array[pos] : 9;
        for (int i = 0; i <= up; i++) {
            int t = sum;
            if (i == 1) t++;
            res += dfs(pos - 1, t, i == up && limit);
        }
        if (!limit) dp[pos][sum] = res;
        return res;
    }
}
