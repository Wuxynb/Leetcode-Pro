package bbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // 此类用来复习使用
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 65536);
    static StringTokenizer tokenizer = new StringTokenizer("");
    static int n;
    static int[] C;

    public static void main(String[] args) {
        n = nextInt(); int m = nextInt();
        C = new int[n + 1];
        for (int i = 1; i <= n; i++) add(i, nextInt());
        while (m-- > 0) {
            int o = nextInt(), x = nextInt(), y = nextInt();
            if (o == 1) add(x, y);
            else System.out.println(getSum(y) - getSum(x - 1));
        }
    }

    static int lowbit(int x) { return x & -x; }

    static void add(int x, int d) {
        for (int i = x; i <= n; i += lowbit(i)) {
            C[i] += d;
        }
    }

    static int getSum(int x) {
        int ans = 0;
        for (int i = x; i > 0; i -= lowbit(i)) {
            ans += C[i];
        }
        return ans;
    }

    private static String next() {
        while (!tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tokenizer.nextToken();
    }

    static int nextInt() {
        return Integer.parseInt(next());
    }
}
