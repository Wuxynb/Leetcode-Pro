package bbc.y2019a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TakeoutPriority {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(), m = input.nextInt(), t = input.nextInt();
        int[][] array = new int[n + 1][t + 1];
        for (int i = 0; i < m; i++) {
            int x = input.nextInt(), y = input.nextInt();
            array[y][x]++;
        }
        boolean[] queue = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            int priority = 0;
            for (int j = 1; j <= t; j++) {
                if (array[i][j] == 0) priority = Math.max(priority - 1, 0);
                else priority += 2 * array[i][j];

                if (priority > 5) queue[i] = true;
                else if (priority <= 3) queue[i] = false;
            }
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) if (queue[i]) ans++;
        System.out.println(ans);
        input.close();
    }
}

class InputReader {
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
