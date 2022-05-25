package bbc.y2022g;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 题目描述
 * <p>
 * 小蓝对一个数的数位之和很感兴趣，今天他要按照数位之和给数排序。
 * <p>
 * 当两个数各个数位之和不同时，将数位和较小的排在前面，当数位之和相等时，将数值小的排在前面。
 * <p>
 * 例如，2022 排在 409 前面，因为2022 的数位之和是6，小于 409 的数位之和13。
 * <p>
 * 又如，6 排在 2022 前面，因为它们的数位之和相同，而 6 小于 2022。
 * <p>
 * 给定正整数n，m，请问对 1 到 n 采用这种方法排序时，排在第 m 个的元素是多少？
 * <p>
 * 输入格式
 * <p>
 * 输入第一行包含一个正整数n。
 * <p>
 * 第二行包含一个正整数m。
 * <p>
 * 30% 的评测用例，1 ≤ m ≤ n ≤ 300。
 * <p>
 * 50% 的评测用例，1 ≤ m ≤ n ≤ 1000。
 * <p>
 * 100%的评测用例，1 ≤ m ≤ n ≤ 10^6。
 * <p>
 * 输出格式
 * <p>
 * 输出一行包含一个整数，表示答案。
 * <p>
 * 输入样例 复制
 * <p>
 * 13
 * <p>
 * 5
 * <p>
 * 输出样例 复制
 * <p>
 * 3
 * <p>
 * 数据范围与提示
 * <p>
 * 1到13的排序为：1, 10, 2, 11, 3, 12, 4, 13, 5, 6, 7, 8, 9。第5个数为3。
 */
public class DigitalSort {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 65536);
    static StringTokenizer tokenizer = new StringTokenizer("");

    public static void main(String[] args) {
        int n = nextInt(), m = nextInt();
        Integer[] array = new Integer[n];
        for (int i = 0; i < n; i++) array[i] = i + 1;
        Arrays.sort(array, (x, y) -> getCnt(x) - getCnt(y));
        System.out.println(array[m - 1]);
    }

    public static int getCnt(int n) {
        int ans = 0;
        while (n > 0) {
            ans += n % 10;
            n /= 10;
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

    public static int nextInt() { return Integer.parseInt(next()); }

    public static long nextLong() { return Long.parseLong(next()); }
}
