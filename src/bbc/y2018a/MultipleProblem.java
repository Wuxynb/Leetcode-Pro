package bbc.y2018a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 题目描述
 * 众所周知，小葱同学擅长计算，尤其擅长计算一个数是否是另外一个数的倍数。
 * 但小葱只擅长两个数的情况，当有很多个数之后就会比较苦恼。
 * 现在小葱给了你 n 个数，希望你从这 n 个数中找到三个数
 * 使得这三个数的和是 K 的倍数，且这个和最大。数据保证一定有解。
 * 输入格式
 * 第一行包括 2 个正整数 n, K。
 * 第二行 n 个正整数，代表给定的 n 个数。
 * 1 <= n <= 10^5, 1 <= K <= 10^3，给定的 n 个数均不超过 10^8。
 */
public class MultipleProblem { // 70 %
    static long K;
    static long[] array;
    static int n;

    public static void main(String[] args) {
        InputReader input = new InputReader(System.in);
        n = input.nextInt();
        K = input.nextLong();
        array = new long[n];
        for (int i = 0; i < n; i++) array[i] = input.nextLong();
        Arrays.sort(array);
        long max = 0;
        for (int i = n - 1; i >= 2; i--) {
            for (int j = i - 1; j >= 1; j--) {
                for (int k = j - 1; k >= 0; k--) {
                    long sum = (array[i] + array[j] + array[k]);
                    if (sum <= max) break;
                    if (sum % K == 0) max = sum;
                }
            }
        }
        System.out.println(max);
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
