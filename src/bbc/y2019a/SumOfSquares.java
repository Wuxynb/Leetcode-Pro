package bbc.y2019a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 题目描述
 * 小明对数位中含有2、0、1、9 的数字很感兴趣，在1 到40 中这样的数包
 * 括1、2、9、10 至32、39 和40，共28 个，他们的和是574，平方和是14362。
 * 注意，平方和是指将每个数分别平方后求和。
 * 请问，在1 到2019 中，所有这样的数的平方和是多少？
 * 输入样例 复制
 *
 * 输出样例 复制
 */
public class SumOfSquares {
    public static void main(String[] args) {
        InputReader input = new InputReader(System.in);
        long sum = 0;
        for (int i = 1; i <= 2019; i++) {
            /*if (String.valueOf(i).matches(".*[0129]+.*")) {
                sum += i * i;
            }*/
            if (!String.valueOf(i).matches("[^0129]+")) {
                sum += i * i;
            }
        }
        System.out.println(sum); // 2658417853
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
