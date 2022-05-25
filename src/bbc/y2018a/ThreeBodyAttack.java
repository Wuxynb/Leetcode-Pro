package bbc.y2018a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 题目描述
 * 三体人将对地球发起攻击。为了抵御攻击，地球人派出了 A × B × C 艘战舰，在太空中排成一个 A 层 B 行 C 列的立方体。其中，第 i 层第 j 行第 k 列的战舰（记为战舰 (i, j, k)）的生命值为 d(i, j, k)。
 * 三体人将会对地球发起 m 轮“立方体攻击”，每次攻击会对一个小立方体中的所有战舰都造成相同的伤害。具体地，第 t 轮攻击用 7 个参数 lat, rat, lbt, rbt, lct, rct, ht 描述；
 * 所有满足 i ∈ [lat, rat],j ∈ [lbt, rbt],k ∈ [lct, rct] 的战舰 (i, j, k) 会受到 ht 的伤害。如果一个战舰累计受到的总伤害超过其防御力，那么这个战舰会爆炸。
 * 地球指挥官希望你能告诉他，第一艘爆炸的战舰是在哪一轮攻击后爆炸的。
 * 输入格式
 * 第一行包括 4 个正整数 A, B, C, m；
 * 第二行包含 A × B × C 个整数，其中第 ((i − 1)×B + (j − 1)) × C + (k − 1)+1 个数为 d(i, j, k)；
 * 第 3 到第 m + 2 行中，第 (t − 2) 行包含 7 个正整数 lat, rat, lbt, rbt, lct, rct, ht。
 * A × B × C ≤ 10^6, m ≤ 10^6, 0 ≤ d(i, j, k), ht ≤ 10^9。
 * 输出格式
 * 输出第一个爆炸的战舰是在哪一轮攻击后爆炸的。保证一定存在这样的战舰。
 * 输入样例 复制
 * 2 2 2 3
 * 1 1 1 1 1 1 1 1
 * 1 2 1 2 1 1 1
 * 1 1 1 2 1 2 1
 * 1 1 1 1 1 1 2
 * 输出样例 复制
 * 2
 * 数据范围与提示
 * 在第 2 轮攻击后，战舰 (1,1,1) 总共受到了 2 点伤害，超出其防御力导致爆炸。
 */
public class ThreeBodyAttack {
    public static void main(String[] args) {
        InputReader input = new InputReader(System.in);
        int a = input.nextInt(), b = input.nextInt(), c = input.nextInt(), m = input.nextInt();
        int[][][] matrix = new int[a + 1][b + 1][c + 1];
        for (int i = 1; i <= a; i++) {
            for (int j = 1; j <= b; j++) {
                for (int k = 1; k <= c; k++) {
                    matrix[i][j][k] = input.nextInt();
                }
            }
        }

        for (int t = 1; t <= m; t++) {
            int lat = input.nextInt(), rat = input.nextInt();
            int lbt = input.nextInt(), rbt = input.nextInt();
            int lct = input.nextInt(), rct = input.nextInt();
            int ht = input.nextInt();
            for (int i = lat; i <= rat; i++) {
                for (int j = lbt; j <= rbt; j++) {
                    for (int k = lct; k <= rct; k++) {
                        if ((matrix[i][j][k] -= ht) < 0) {
                            System.out.println(t);
                            System.exit(0);
                        }
                    }
                }
            }
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
