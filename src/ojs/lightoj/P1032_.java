package ojs.lightoj;

import java.util.Arrays;
import java.util.Scanner;

public class P1032_ {
    static int N = 35;
    static int[] array = new int[N];
    static long[][][] dp = new long[N][N << 1][2]; // dp[N][cnt][pre]

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt(), cnt = 1;
        while (t-- > 0) {
            System.out.printf("Case %d: %d\n", cnt++, solve(scan.nextInt()));
        }
    }

    public static long solve(int n) {
        for (long[][] ints : dp)
            for (long[] ints1 : ints)
                Arrays.fill(ints1, -1);
        int pos = 0;
        while (n != 0) {
            array[++pos] = n & 1;
            n >>= 1;
        }
        return dfs(pos, 0, 0, true);
    }

    public static long dfs(int pos, int cnt, int pre, boolean limit) {
        if (pos == 0) return cnt;
        if (dp[pos][cnt][pre] != -1 && !limit) return dp[pos][cnt][pre];
        long ans = 0; int up = limit ? array[pos] : 1;
        for (int i = 0; i <= up; i++) {
            if (i == 1 && pre == 1) ans += dfs(pos - 1, cnt + 1, i, i == up && limit);
            else ans += dfs(pos - 1, cnt, i, i == up && limit);
        }
        if (!limit) dp[pos][cnt][pre] = ans;
        return ans;
    }
}
