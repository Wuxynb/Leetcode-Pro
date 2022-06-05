package ojs.acwing;

import java.util.Scanner;

/**
 * 给定 n 组 ai,bi,pi，对于每组数据，求出 ai^bi mod pi 的值。
 * <p>
 * 输入格式
 * <p>
 * 第一行包含整数 n。
 * <p>
 * 接下来n行，每行包含三个整数 ai,bi,pi。
 * <p>
 * 输出格式
 * <p>
 * 对于每组数据，输出一个结果，表示 ai^bi mod pi 的值。
 * <p>
 * 数据范围
 * <p>
 * 1 <= n <= 100000.
 * <p>
 * 1 <= ai,bi,pi <= 2x10^9.
 * <p>
 * 输入样例：
 * <p>
 * 2
 * <p>
 * 3 2 5
 * <p>
 * 4 3 9
 * <p>
 * 输出样例：
 * <p>
 * 4
 * <p>
 * 1
 */
public class P875 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while (t-- > 0) {
            long a = scan.nextLong(), b = scan.nextLong(), c = scan.nextLong();
            System.out.println(quickMi(a, b, c));
        }
    }

    public static long quickMi(long a, long b, long c) {
        long res = 1;
        while (b != 0) {
            if ((b & 1) == 1) res = res * a % c;
            a = a * a % c;
            b >>= 1;
        }
        return res;
    }
}
