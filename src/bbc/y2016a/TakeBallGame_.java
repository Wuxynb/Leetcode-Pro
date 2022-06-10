package bbc.y2016a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TakeBallGame_ {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 65536);
    static StringTokenizer tokenizer = new StringTokenizer("");
    static int[] arr;
    static int[][][] dp; // dp[i][j][k]表示玩家1拿了i个球，玩家2拿了j个球，k表示轮到谁来拿球
    static int n;

    public static void main(String[] args) {
        arr = new int[]{nextInt(), nextInt(), nextInt()};
        Arrays.sort(arr);
        for (int i = 0; i < 5; i++) {
            n = nextInt();
            dp = new int[n + 1][n + 1][2];
            for (int[][] ints1 : dp) for (int[] ints : ints1) Arrays.fill(ints, -2);
            System.out.print(getChar(dfs(0, 0, 0)) + " ");
        }
    }

    public static int dfs(int i, int j, int turn) {
        if ((n - i - j) < arr[0]) {
            if ((i & 1) == 1 && (j & 1) == 0) return dp[i][j][turn] = 1;
            else if ((i & 1) == 0 && (j & 1) == 1) return dp[i][j][turn] = -1;
            else return dp[i][j][turn] = 0;
        }
        if (dp[i][j][turn] != -2) return dp[i][j][turn];
        int ans = turn == 0 ? -1 : 1; // 必败状态
        for (int a : arr) {
            if ((n - i - j) >= a) {
                int res;
                if (turn == 0) res = dfs(i + a, j, 1 - turn);
                else res = dfs(i, j + a, 1 - turn);
                // 也可以写成：if (res != ans) { if ((ans = res) != 0) return dp[i][j][turn] = ans; } // 当不是最坏状态时
                if (turn == 0 && res == 1) return dp[i][j][turn] = 1; // 当前是玩家1取，则玩家2的必败状态是1
                if (turn == 1 && res == -1) return dp[i][j][turn] = -1; // 当前是玩家2取，则玩家1的必败状态是-1
                if (res == 0) ans = 0;
            }
        }
        return dp[i][j][turn] = ans;
    }

    public static char getChar(int x) {
        if (x == 1) return '+';
        if (x == -1) return '-';
        return '0';
    }

    private static String next() {
        while (!tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tokenizer.nextToken();
    }

    static int nextInt() {
        return Integer.parseInt(next());
    }
}
