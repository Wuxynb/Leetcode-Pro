package bbc.y2022g;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 题目描述
 * <p>
 * 蜂巢由大量的六边形拼接而成，定义蜂巢中的方向为：
 * <p>
 * 0表示正西方向，1表示西偏北60度，2表示东偏北60度，
 * <p>
 * 3表示正东方向，4表示东偏南60度，5表示西偏南60度。
 * <p>
 * 对于给定的一点O，我们以O为原点定义坐标系。
 * <p>
 * 如果一个点A由O点先向d方向走p步再向(d + 2) mod 6方向（d 的顺时针120度方向）走q步到达，则这个点的坐标定义为(d, p, q)。
 * <p>
 * 在蜂窝中，一个点的坐标可能有多种。
 * <p>
 * 下图给出了点B(0, 5, 3) 和点C(2, 3, 2) 的示意。
 * <p>
 * <p>
 * 给定点(d1, p1, q1)和点(d2, p2, q2)，请问他们之间最少走多少步可以到达？
 * <p>
 * 输入格式
 * <p>
 * 输入存在多组测试数据：第一行为正整数T，表示存在T组测试数据。（T不超过500）
 * <p>
 * 每组测试数据：输入一行包含6个整数d1,p1,q1,d2,p2,q2表示两个点的坐标。
 * <p>
 * 相邻两个整数之间使用一个空格分隔。
 * <p>
 * 25%的测试数据：p1,p2≤1000；
 * <p>
 * 50%的测试数据：p1,p2≤100000；
 * <p>
 * 75%的测试数据：p1,p2≤10000000；
 * <p>
 * 100%的测试数据：0≤d1,d2≤5,0≤q1＜p1≤10^9,0≤q2＜p2≤10^9。
 * <p>
 * 输出格式
 * <p>
 * 输出一行包含一个整数表示两点之间最少走多少步可以到达。
 * <p>
 * 输入样例 复制
 * <p>
 * 1
 * <p>
 * 0 5 3 2 3 2
 * <p>
 * 输出样例 复制
 * <p>
 * 7
 */
public class Hive {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 65536);
    static StringTokenizer tokenizer = new StringTokenizer("");

    public static void main(String[] args) {
        int[][] dirs = new int[][]{{-1, 0}, {-1, 1}, {0, 1}, {1, 0}, {1, -1}, {0, -1}};
        int t = nextInt();
        while (t-- > 0) {
            int d1 = nextInt(); long p1 = nextLong(), q1 = nextLong();
            int d2 = nextInt(); long p2 = nextLong(), q2 = nextLong();
            long x1 = 0, y1 = 0, x2 = 0, y2 = 0;
            x1 += p1 * dirs[d1][0] + q1 * dirs[(d1 + 2) % 6][0];
            y1 += p1 * dirs[d1][1] + q1 * dirs[(d1 + 2) % 6][1];
            x2 += p2 * dirs[d2][0] + q2 * dirs[(d2 + 2) % 6][0];
            y2 += p2 * dirs[d2][1] + q2 * dirs[(d2 + 2) % 6][1];

            System.out.println(getDistance(x1, y1, x2, y2));
        }
    }

    public static long getDistance(long x1, long y1, long x2, long y2) {
        if (x1 > x2) return getDistance(x2, y2, x1, y1); // 如果 (x1, y1)在右边，则翻转一下。
        long a = Math.abs(x1 - x2), b = Math.abs(y1 - y2);
        if (y1 >= y2) return Math.max(a, b);  // 当第一个点在左上方时
        return a + b;  // 当第一个点在左下方时
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
