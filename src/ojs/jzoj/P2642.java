package ojs.jzoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 时间限制：2S 空间限制：256M
 * <p>
 * Alice和Bob在玩一个游戏，游戏是在一个N*N的矩阵上进行的，每个格子上都有
 * 一个正整数。当轮到Alice/Bob时，他/她可以选择最后一列或最后一行，并将其删除，但
 * 必须保证选择的这一行或这一列所有数的和为偶数。如果他/她不能删除最后一行或最后一
 * 列，那么他/她就输了。两人都用最优策略来玩游戏，Alice先手，问Alice是否可以必胜？
 * <p>
 * 输入格式：
 * <p>
 * 第一行：T，表示数据组数
 * <p>
 * 对于每组数据的第一行：N
 * <p>
 * 接下来N行，每行N个数，描述这个矩阵
 *
 * <p>
 * 输出格式：
 * <p>
 * 如果Alice必胜输出W，否则输出L
 * <p>
 * 样例输入：
 * <p>
 * 2
 * <p>
 * 2
 * <p>
 * 2 4
 * <p>
 * 6 8
 * <p>
 * 3
 * <p>
 * 5 4 2
 * <p>
 * 1 5 9
 * <p>
 * 7 3 8
 * <p>
 * 样例输出：
 * <p>
 * L
 * <p>
 * W
 * <p>
 * 数据范围：
 * <p>
 * 100%数据满足 1<=N<=1000
 * <p>
 * 保证每一行或每一列的和不会超过2*10^9
 * <p>
 * 1<=T<=5
 * <p>
 * 30%数据满足 1<=N<=5
 * <p>
 * 50%数据满足 1<=N<=100
 * <p>
 * 70%数据满足 1<=N<=500
 */
public class P2642 {
    static BufferedReader input;
    static StringTokenizer tokenizer;
    static int maxn = 1005;
    static int[][] arr = new int[maxn][maxn];
    static int[][] dp = new int[maxn][maxn];
    static int[][] sg = new int[maxn][maxn];
    static int[][] sumx = new int[maxn][maxn];
    static int[][] sumy = new int[maxn][maxn];

    public static void main(String[] args) {
        input = new BufferedReader(new InputStreamReader(System.in));
        tokenizer = new StringTokenizer("");
        int t = nextInt();
        while (t-- > 0) {
            int n = nextInt();
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    arr[i][j] = nextInt();
                    dp[i][j] = sg[i][j] = -1;
                    sumx[i][j] = sumx[i][j - 1] + arr[i][j];
                    sumy[j][i] = sumx[j][i - 1] + arr[i][j]; // 注意：求第j列上的和
                }
            }
            System.out.println((dfs(n, n) == 0 ? "L" : "W"));
            System.out.println((getSg(n, n) == 0 ? "L" : "W"));
        }
    }

    public static int getSg(int x, int y) {
        if (sg[x][y] != -1) return sg[x][y];
        boolean[] visited = new boolean[maxn * maxn];
        if ((sumx[x][y] & 1) == 0) visited[sg[x - 1][y] = getSg(x - 1, y)] = true;
        if ((sumy[y][x] & 1) == 0) visited[sg[x][y - 1] = getSg(x, y - 1)] = true;
        int ret = 0;
        for (int i = 0; ; i++) {
            if (!visited[i]) {
                ret = i;
                break;
            }
        }
        return sg[x][y] = ret;
    }

    public static int dfs(int x, int y) { // 0-A, 1-B
        if (dp[x][y] != -1) return dp[x][y];
        if (x == 0 || y == 0) return dp[x][y] = 0; // 必败状态
        int res = 0;
        if ((sumx[x][y] % 2) == 0)
            if (dfs(x - 1, y) == 0) res = 1; // 如果能转移到必败态，将必败态留给对手，自己获胜

        if ((sumy[y][x] % 2) == 0)
            if (dfs(x, y - 1) == 0) res = 1; // 同上
        return dp[x][y] = res;
    }

    public static String next() {
        while (!tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(input.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tokenizer.nextToken();
    }

    public static int nextInt() {
        return Integer.parseInt(next());
    }

    public static long nextLong() {
        return Long.parseLong(next());
    }

    public static double nextDouble() {
        return Double.parseDouble(next());
    }
}
