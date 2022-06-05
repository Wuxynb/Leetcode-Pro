package ojs.luogu;

import java.util.Scanner;

/**
 * P3374 【模板】树状数组 1
 * <p>
 * 题目描述
 * <p>
 * 如题，已知一个数列，你需要进行下面两种操作：
 * <p>
 * 将某一个数加上 xx
 * <p>
 * 求出某区间每一个数的和
 * <p>
 * 输入格式
 * <p>
 * 第一行包含两个正整数 n,mn,m，分别表示该数列数字的个数和操作的总个数。
 * <p>
 * 第二行包含 nn 个用空格分隔的整数，其中第 ii 个数字表示数列第 ii 项的初始值。
 * <p>
 * 接下来 mm 行每行包含 33 个整数，表示一个操作，具体如下：
 * <p>
 * 1 x k 含义：将第 xx 个数加上 kk
 * <p>
 * 2 x y 含义：输出区间 [x,y][x,y] 内每个数的和
 * <p>
 * 输出格式
 * <p>
 * 输出包含若干行整数，即为所有操作 22 的结果。
 * <p>
 * 输入输出样例
 * <p>
 * 输入 #1复制
 * <p>
 * 5 5
 * <p>
 * 1 5 4 2 3
 * <p>
 * 1 1 3
 * <p>
 * 2 2 5
 * <p>
 * 1 3 -1
 * <p>
 * 1 4 2
 * <p>
 * 2 1 4
 * <p>
 * 输出 #1复制
 * <p>
 * 14
 * <p>
 * 16
 * <p>
 * 说明/提示
 * <p>
 * 【数据范围】
 * <p>
 * 对于 30\%30% 的数据，1 \le n \le 81≤n≤8，1\le m \le 101≤m≤10；
 * <p>
 * 对于 70\%70% 的数据，1\le n,m \le 10^41≤n,m≤10
 * <p>
 * 4
 * ；
 * <p>
 * 对于 100\%100% 的数据，1\le n,m \le 5\times 10^51≤n,m≤5×10
 * 5
 * 。
 * <p>
 * 样例说明：
 * <p>
 * <p>
 * <p>
 * 故输出结果14、16
 */
public class P3374 {
    static int n;
    static int[] A;
    static long[] C;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt(); int m = scan.nextInt();
        A = new int[n]; C = new long[n + 1];
        for (int i = 0; i < n; i++) {
            add(i + 1, scan.nextLong());
        }
        while (m-- > 0) {
            int o = scan.nextInt(), x = scan.nextInt(), y = scan.nextInt();
            if (o == 1) {
                add(x, y);
            } else {
                System.out.println(getSum(y) - getSum(x - 1));
            }
        }
    }

    static int lowbit(int x) { return x & -x; }

    static void add(int x, long d) {
        for (int i = x; i <= n; i += lowbit(i)) {
            C[i] += d;
        }
    }

    static long getSum(int x) {
        long ans = 0;
        for (int i = x; i > 0; i -= lowbit(i)) {
            ans += C[i];
        }
        return ans;
    }
}
