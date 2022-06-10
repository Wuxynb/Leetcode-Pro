package bbc.y2016a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 题目描述
 * <p>
 * 两个人玩取球的游戏。一共有N个球，每人轮流取球，每次可取集合{n1,n2,n3}中的任何一个数目。
 * <p>
 * 如果无法继续取球，则游戏结束。此时，持有奇数个球的一方获胜。如果两人都是奇数，则为平局。
 * <p>
 * 假设双方都采用最聪明的取法，第一个取球的人一定能赢吗？试编程解决这个问题。
 * <p>
 * 输入格式
 * <p>
 * 输入存在多组测试样例，对于每一组测试数据：
 * <p>
 * 第一行3个正整数n1 n2 n3，空格分开，表示每次可取的数目 (0< n1,n2,n3<100)
 * <p>
 * 第二行5个正整数x1 x2 ... x5，空格分开，表示5局的初始球数(0< xi<1000)
 * <p>
 * 输出格式
 * <p>
 * 一行5个字符，空格分开。分别表示每局先取球的人能否获胜。
 * <p>
 * 能获胜则输出+，次之，如有办法逼平对手，输出0，无论如何都会输，则输出-
 * <p>
 * 输入样例 复制
 * <p>
 * 1 2 3
 * <p>
 * 1 2 3 4 5
 * <p>
 * 1 4 5
 * <p>
 * 10 11 12 13 15
 * <p>
 * 2 3 5
 * <p>
 * 7 8 9 10 11
 * <p>
 * 输出样例 复制
 * <p>
 * + 0 + 0 -
 * <p>
 * 0 - 0 + +
 * <p>
 * + 0 0 0 0
 */
public class TakeBallGame {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 65536);
    static StringTokenizer tokenizer = new StringTokenizer("");
    static int[][][] dp; // 当前还剩i个球时玩家1已经拿球数奇偶性为j，玩家2拿球数奇偶性为k
    static int[] arr;
    static int n;

    public static void main(String[] args) {
        arr = new int[]{nextInt(), nextInt(), nextInt()};
        Arrays.sort(arr);
        for (int i = 0; i < 5; i++) {
            n = nextInt();
            dp = new int[n + 1][2][2]; //
            System.out.print(getChar(dfs(n, 0, 0)) + " ");
        }
    }

    public static int dfs(int i, int j, int k) {
        if (i < arr[0]) { // 已经没有球了
            if (j == 1 && k == 0) return dp[i][j][k] = 1; // 我方赢了
            else if (j == 0 && k == 1) return dp[i][j][k] = 2; // 对方赢了
            else return dp[i][j][k] = 3; // 平局
        }
        if (dp[i][j][k] != 0) return dp[i][j][k];
        int ans = 2; // 假设玩家1输了
        for (int a : arr) {
            if (i >= a) {
                // 双方互换，转换为y取球
                int nj = k, nk = a % 2 == 0 ? j : (1 - j);
                int res = dfs(i - a, nj, nk);
                if (res == 2) return dp[i][j][k] = 1;
                if (res == 3) ans = 3;
            }
        }
        return dp[i][j][k] = ans;
    }

    public static char getChar(int x) {
        if (x == 1) return '+';
        if (x == 3) return '0';
        return '-';
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

    public static int nextInt() { return Integer.parseInt(next()); }

    public static long nextLong() { return Long.parseLong(next()); }
}
