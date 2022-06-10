package ojs.lightoj;

import java.util.Scanner;

/**
 * Fast Bit Calculations
 * <p>
 * 位是一个二进制数字，逻辑值为1或0（也分别称为“真”或“假”）。每个十进制数都有一个二进制表示，实际上是一系列位。如果一个数字的一个位是1，它的下一个
 * 位也是1，那么我们可以说这个数字有一个1相邻的位。你必须找出这种情况在N以内的所有数字中发生的次数。
 * <p>
 * 题意：定义连续的两个1为某种数对，给出数n，将0~n中所有数转换为二进制形式，求出总共有多少个上述定义的数对。
 *
 * <p>
 * Examples:
 * <p>
 * Number	Binary	Adjacent Bits
 * <p>
 * 12	1100	1
 * <p>
 * 15	1111	3
 * <p>
 * 27	11011	2
 * <p>
 * Input
 * <p>
 * Input starts with an integer T (≤ 10000), denoting the number of test cases.
 * <p>
 * Each case contains an integer N (0 ≤ N < 2^31).
 * <p>
 * Output
 * <p>
 * For each test case, print the case number and the summation of all adjacent bits from 0 to N.
 * <p>
 * Sample
 * <p>
 * Input
 * <p>
 * 7
 * <p>
 * 0
 * <p>
 * 6
 * <p>
 * 15
 * <p>
 * 20
 * <p>
 * 21
 * <p>
 * 22
 * <p>
 * 2147483647
 * <p>
 * Output
 * <p>
 * Case 1: 0
 * <p>
 * Case 2: 2
 * <p>
 * Case 3: 12
 * <p>
 * Case 4: 13
 * <p>
 * Case 5: 13
 * <p>
 * Case 6: 14
 * <p>
 * Case 7: 16106127360
 */
public class P1032 {
    static int N = 35;
    static long[][] f = new long[N][2]; // f(i,j)表示开头为j（0或1），长度为N位时拥有的 数对 数。

    // https://blog.csdn.net/qq_41661919/article/details/86738008
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt(), cnt = 1;
        init();
        while (t-- > 0) {
            System.out.printf("Case %d: %d\n", cnt++, dp(scan.nextInt()));
        }
    }

    /*
     * 1. 第i位为零时，f[i][0]=f[i-1][0]+f[i-1][1]；
     * 2. 当第i位为1的时候，要考虑第i-1位是否为1，
     *      - 如果i-1位为零，加上f[i-1][0]，
     *      - 如果为1，除了加上f[i-1][1]外，还要考虑第i位和第i-1位形成了一个数对，又因为前面的i-2个数位总共可以有（1<<（i-2）种不同形式/不同
     *      数字，所以总共多了（1<<(i-2))个数对。）
     */
    public static void init() {
        for (int i = 2; i <= 32; i++) {
            f[i][1] = f[i - 1][0] + f[i - 1][1] + (1 << (i - 2));
            f[i][0] = f[i - 1][1] + f[i - 1][0];
        }
    }

    public static long dp(int n) {
        if (n <= 0) return 0;
        int pos = 0, last = 0;
        long res = 0, cnt = 0;
        int[] bits = new int[N];
        while (n != 0) {
            bits[++pos] = n & 1;
            n >>= 1;
        }
        for (int i = pos; i > 0; i--) { // 最高位开始递推,注意这里i下界为1，solve（x/x+1）均是
            int cur = bits[i];
            if (cur == 1) {
                res += f[i][0]; // 第 i 位不取最高位
                res += cnt * (1L << (i - 1)); // 前面 1数对 的数目 乘上 后面的数有多少不同形式
            }
            if (last == 1 && cur == 1) cnt++; // 更新当前 1数对 的数目
            last = cur;
        }
        res += cnt; // 遍历完了所有数位
        return res;
    }
}
