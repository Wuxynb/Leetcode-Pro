package ojs.xdoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1378 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 65536);
    static StringTokenizer tokenizer = new StringTokenizer("");
    static int N = (int) 1e3 + 5;
    static int[][] diff = new int[N][N];

    public static void main(String[] args) {
        int n = nextInt(), m = nextInt(), h = nextInt();
        while (h-- > 0) {
            int x2 = nextInt(), y2 = nextInt();
            diff[1][1]++;
            diff[1][y2 + 1]--;
            diff[x2 + 1][1]--;
            diff[x2 + 1][y2 + 1]++;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                diff[i][j] += diff[i - 1][j] + diff[i][j  -1] - diff[i - 1][j - 1];
                System.out.println(diff[i][j]);
            }
        }
    }

    public static String next() {
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
}
