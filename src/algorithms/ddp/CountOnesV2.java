package algorithms.ddp;

import java.util.Arrays;

/**
 * link: https://blog.csdn.net/geraltofrivia123/article/details/123180845?spm=1001.2101.3001.6650.1&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-1.pc_relevant_default&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-1.pc_relevant_default&utm_relevant_index=2
 */
public class CountOnesV2 {
    static int N = 11;
    static int[] a = new int[N];
    static int[][] dp = new int[N][N]; // dp[i][j] 表示当前枚举到第i位数时，已经统计了有j个1，此时出现的1的个数

    public static void main(String[] args) {
        System.out.println(countOnes(12));

        System.out.println(countOnes(13));
    }

    static int countOnes(int n) {
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], -1);
        int pos = 0;
        while (n > 0) {
            a[pos++] = n % 10;
            n /= 10;
        }
        return dfs(pos - 1, 0, true);
    }

    static int dfs(int pos, int sum, boolean limit) {
        if (pos == -1) return sum;
        if (!limit && dp[pos][sum] != -1) return dp[pos][sum];
        int res = 0, up = limit ? a[pos] : 9;
        for (int i = 0; i <= up; i++) {
            int t = sum;
            if (i == 1) t++;
            res += dfs(pos - 1, t, limit && i == a[pos]);
        }
        if (!limit) dp[pos][sum] = res;
        return res;
    }
}
