package bbc.y2022g;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 题目描述
 * <p>
 * 给定一个数组A和一些查询Li, Ri，求数组中第Li至第Ri个元素之和。
 * <p>
 * 小蓝觉得这个问题很无聊，于是他想重新排列一下数组，使得最终每个查询结果的和尽可能地大。
 * <p>
 * 小蓝想知道相比原数组，所有查询结果的总和最多可以增加多少?
 * <p>
 * 输入格式
 * <p>
 * 输入第一行包含一个整数n。
 * <p>
 * 第二行包含n个整数A1, A2, ..., An，相邻两个整数之间用一个空格分隔。
 * <p>
 * 第三行包含一个整数m表示查询的数目。
 * <p>
 * 接下来m行，每行包含两个整数Li、Ri ，相邻两个整数之间用一个空格分隔。
 * <p>
 * 对于30% 的评测用例，n,m ≤ 50 ；
 * <p>
 * 对于50% 的评测用例，n,m ≤ 500 ；
 * <p>
 * 对于70% 的评测用例，n,m ≤ 5000 ；
 * <p>
 * 对于所有评测用例，1 ≤ n,m ≤ 10^5，1 ≤ Ai ≤ 10^6，1 ≤ Li ≤ Ri ≤ n 。
 * <p>
 * 输出格式
 * <p>
 * 输出一行包含一个整数表示答案。
 * <p>
 * 输入样例 复制
 * <p>
 * 5
 * <p>
 * 1 2 3 4 5
 * <p>
 * 2
 * <p>
 * 1 3
 * <p>
 * 2 5
 * <p>
 * 输出样例 复制
 * <p>
 * 4
 * <p>
 * 数据范围与提示
 * <p>
 * 原来的和为6 + 14 = 20，重新排列为(1, 4, 5, 2, 3) 后和为10 + 14 = 24，增加了4。
 */
public class Reorder {
    static int maxn = 100005;
    static long[] diff = new long[maxn];
    static long[] array = new long[maxn];
    static long[] cnts = new long[maxn];
    static long[] sums = new long[maxn];

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65536);
    static StringTokenizer tokenizer = new StringTokenizer("");

    public static void main(String[] args) {
        int n = nextInt();
        array = new long[n + 1];
        cnts = new long[n + 1];
        sums = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            array[i] = nextInt();
            sums[i] = sums[i - 1] + array[i];
        }
        int t = nextInt();
        long ori = 0L;
        while (t-- > 0) {
            int l = nextInt(), r = nextInt();
            diff[l] += 1;
            diff[r + 1] -= 1;
            ori += sums[r] - sums[l - 1];
        }
        for (int i = 1; i <= n; i++) {
            cnts[i] = cnts[i - 1] + diff[i];
        }
        Arrays.sort(cnts);
        Arrays.sort(array);
        long curr = 0L;
        for (int i = n; i >= 0 && cnts[i] > 0; i--) {
            curr += cnts[i] * array[i];
        }
        System.out.println(curr - ori);
    }

    private static String next() {
        while (!tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(bf.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tokenizer.nextToken();
    }

    public static int nextInt() { return Integer.parseInt(next()); }

    public static long nextLong() { return Long.parseLong(next()); }
}
