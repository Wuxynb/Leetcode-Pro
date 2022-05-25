package bbc.y2019a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 题目描述
 * 给定一棵包含N 个节点的完全二叉树，树上每个节点都有一个权值，按从
 * 上到下、从左到右的顺序依次是A1, A2, AN，如下图所示：
 *
 *
 * 现在小明要把相同深度的节点的权值加在一起，他想知道哪个深度的节点
 * 权值之和最大？如果有多个深度的权值和同为最大，请你输出其中最小的深度。
 * 注：根的深度是1。
 * 输入格式
 * 第一行包含一个整数N。
 * 第二行包含N 个整数A1, A2, AN
 * 对于所有评测用例，1<=N<=100000, -100000<=Ai<=100000。
 * 输出格式
 * 输出一个整数代表答案。
 * 输入样例 复制
 * 7
 * 1 6 5 4 3 2 1
 * 输出样例 复制
 * 2
 */
public class CompleteBinaryTreeWeight {
    public static void main(String[] args) {
        InputReader input = new InputReader(System.in);
        int n = input.nextInt();
        int h = (int) (log2(n) + 1);
        System.out.println(h);
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
        }
        int level = 1, i = 0, max = 0, maxLevel = 1;
        while (i < n) {
            int sum = 0;
            for (int j = 0; j < Math.pow(2, level - 1) && i < n; j++, i++) {
                sum += arr[i];
            }
            if (sum > max) {
                max = sum;
                maxLevel = level;
            }
            level++;
        }
        System.out.println(maxLevel);
        System.out.println(((int) (Math.random() * 1000)));
    }

    public static double log2(double n) {
        return Math.log(n) / Math.log(2);
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
