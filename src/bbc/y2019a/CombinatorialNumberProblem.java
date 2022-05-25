package bbc.y2019a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CombinatorialNumberProblem {
    static long k = 0;

    public static void main(String[] args) {
        InputReader input = new InputReader(System.in);
        long t = input.nextLong();
        k = input.nextLong();
        for (long c = 0; c < t; c++) {
            long n = input.nextLong(), m = input.nextLong();
            long ans = 0;
            for (int i = 1; i <= n; i++) {
                for (int j = 0; j <= Math.min(i, m); j++) {
                    if (combination(i, j) % k == 0) {
                        ans++;
//                        System.out.println("i: " + i + ", j: " + j + ", cij: " + combination(i, j));
                    }
                }
            }
            System.out.println(ans);
        }
    }

    public static long combination(long a, long b) {
        if (b == 0 || a == b) return 1;
        long x = factorial(b) * factorial(a - b);
        if (x == 0) return factorial(a);
        return factorial(a) / x;
    }

    public static long factorial(long n) {
        if (n == 0 || n == 1) return 1;
        return quickMulti(n, factorial(n - 1));
    }

    public static long quickMulti(long a, long b) {
        if (b == 0) return 0;
        else if ((b & 1) == 1) return (a + quickMulti(a, b - 1));
        else {
            return quickMulti(a * 2, b / 2);
        }
    }

    static class InputReader {
        private final static int BUF_SZ = 65536;
        BufferedReader in;
        StringTokenizer tokenizer;

        public InputReader(InputStream in) {
            this.in = new BufferedReader(new InputStreamReader(in), BUF_SZ);
            tokenizer = new StringTokenizer("");
        }

        private String next() {
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
