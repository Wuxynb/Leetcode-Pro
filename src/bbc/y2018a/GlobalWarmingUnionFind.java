package bbc.y2018a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GlobalWarmingUnionFind {
    static int[] father;
    static int[][] directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static int n;

    public static void main(String[] args) {
        InputReader input = new InputReader(System.in);
        n = input.nextInt();
        char[][] grid = new char[n][n];
        for (int i = 0; i < n; i++) {
            String str = input.next();
            grid[i] = str.toCharArray();
        }
        int num1 = islandNums(grid);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '#') {
                    for (int[] direction : directions) {
                        int x = i + direction[0], y = j + direction[1];
                        if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == '.') {
                            grid[i][j] = 'x';
                            break;
                        }
                    }
                }
            }
        }
        int num2 = islandNums(grid);
        System.out.println(num1 - num2);
    }

    public static void init(int m, int n) {
        father = new int[m * n];
        for (int i = 0; i < m * n; i++) {
            father[i] = i;
        }
    }

    public static int find(int x) {
        if (x == father[x]) return x;
        father[x] = find(father[x]);
        return father[x];
    }

    public static void merge(int i, int j) {
        father[find(j)] = find(i);
    }

    public static int islandNums(char[][] grids) {
        int m = grids.length, n = grids[0].length;
        init(m, n);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grids[i][j] == '#') {
                    for (int[] direction : directions) {
                        int x = i + direction[0], y = j + direction[1];
                        if (x >= 0 && x < m && y >= 0 && y < n && grids[x][y] == '#') merge(i * n + j, x * n + y);
                    }
                }
            }
        }

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grids[i][j] == '#' && i * n + j == find(i * n + j)) res++;
            }
        }
        return res;
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
