package bbc.y2018a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 题目描述
 * 你有一张某海域NxN像素的照片，"."表示海洋、"#"表示陆地，如下所示：
 * .......
 * .##....
 * .##....
 * ....##.
 * ..####.
 * ...###.
 * .......
 * 其中"上下左右"四个方向上连在一起的一片陆地组成一座岛屿。例如上图就有2座岛屿。
 * 由于全球变暖导致了海面上升，科学家预测未来几十年，岛屿边缘一个像素的范围会被海水淹没。
 * 具体来说如果一块陆地像素与海洋相邻(上下左右四个相邻像素中有海洋)，它就会被淹没。
 * 例如上图中的海域未来会变成如下样子：
 * .......
 * .......
 * .......
 * .......
 * ....#..
 * .......
 * .......
 * 请你计算：依照科学家的预测，照片中有多少岛屿会被完全淹没。
 * 输入格式
 * 第一行包含一个整数N。  (1 <= N <= 1000)
 * 以下N行N列代表一张海域照片。
 * 照片保证第1行、第1列、第N行、第N列的像素都是海洋。
 * 输出格式
 * 一个整数表示答案。
 * 输入样例 复制
 * 7
 * .......
 * .##....
 * .##....
 * ....##.
 * ..####.
 * ...###.
 * .......
 * 输出样例 复制
 * 1
 */
public class GlobalWarming {
    static int n, ans = 0;
    static boolean[][] visited;
    static boolean flag = true;

    public static void main(String[] args) {
        InputReader input = new InputReader(System.in);
        n = input.nextInt();
        visited = new boolean[n][n];
        char[][] grid = new char[n][n];
        for (int i = 0; i < n; i++) {
            grid[i] = input.next().toCharArray();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid[i][j] == '#') {
                    flag = true;
                    dfs(grid, i, j);
                    if (flag) ans++;
                }
            }
        }
        System.out.println(ans);
    }

    public static void dfs(char[][] grid, int i, int j) {
        if (visited[i][j] || grid[i][j] == '.') return;
        visited[i][j] = true;
        if (grid[i + 1][j] == '#' &&
                grid[i - 1][j] == '#' &&
                grid[i][j + 1] == '#' &&
                grid[i][j - 1] == '#') flag = false;

        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
    }

    static class InputReader {
        private final static int BUF_SZ = 65536;
        BufferedReader in;
        StringTokenizer tokenizer;

        public InputReader(InputStream in) {
            this.in = new BufferedReader(new InputStreamReader(in), BUF_SZ);
            tokenizer = new StringTokenizer("");
        }

        public String next() {
            while (!tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(in.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}
