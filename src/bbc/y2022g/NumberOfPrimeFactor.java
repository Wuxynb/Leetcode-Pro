package bbc.y2022g;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.StringTokenizer;

/**
 * 题目描述
 * <p>
 * 给定正整数n，请问有多少个质数是n的约数。
 * <p>
 * 输入格式
 * <p>
 * 输入的第一行包含一个整数n。
 * <p>
 * 对于30% 的评测用例，1≤n≤10000。
 * <p>
 * 对于60% 的评测用例，1≤n≤10^9。
 * <p>
 * 对于所有评测用例，1≤n≤10^16。
 * <p>
 * 输出格式
 * <p>
 * 输出一个整数，表示n 的质数约数个数。
 * <p>
 * 输入样例 复制
 * <p>
 * 396
 * <p>
 * 输出样例 复制
 * <p>
 * 3
 * <p>
 * 数据范围与提示
 * <p>
 * 396 有2, 3, 11 三个质数约数。
 */
public class NumberOfPrimeFactor {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 65536);
    static StringTokenizer tokenizer = new StringTokenizer("");

    public static void main(String[] args) {
        long n = nextLong();
        int cnt = 0, ans = 0;
        for (int i = 2; (long) i * i <= n && n != 1; i++, cnt = 0) {
            while (n % i == 0) {
                cnt++;
                n /= i;
            }
            if (cnt > 0) ans++;
        }
        if (n != 1) ans++; // 说明存在一个大于sqrt(n)的质因数，且有且仅有一个，所以要+1
        System.out.println(ans);
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

    public static int nextInt() {
        return Integer.parseInt(next());
    }

    public static long nextLong() {
        return Long.parseLong(next());
    }
}
