package ojs.loj;

import java.util.Arrays;
import java.util.Scanner;

/**
 * #10164. 「一本通 5.3 例 2」数字游戏
 * <p>
 * 传统1000 ms512 MiB
 * <p>
 * 题目描述
 * <p>
 * 科协里最近很流行数字游戏。某人命名了一种不降数，这种数字必须满足从左到右各位数字成小于等于的关系，如 ，。现在大家决定玩一个游戏，指定一个整数闭
 * 区间 ，问这个区间内有多少个不降数。
 * <p>
 * 输入格式
 * <p>
 * 有多组测试数据。每组只含两个数字 a,b，意义如题目描述。
 * <p>
 * 输出格式
 * <p>
 * 每行给出一个测试数据的答案，即 [a,b] 之间有多少不降数。
 * <p>
 * 样例
 * <p>
 * 输入
 * <p>
 * 1 9
 * <p>
 * 1 19
 * <p>
 * 输出
 * <p>
 * 9
 * <p>
 * 18
 * <p>
 * 数据范围与提示
 * 对于全部数据，。
 * <p>
 * link: https://loj.ac/p/10164
 */
public class P10164 {
    static int N = 15;
    static int[] arr = new int[N];
    static int[][] dp = new int[N][10]; // dp[i][j]表示上一位数为j，长度为i的情况下的不减数个数

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            int a = scan.nextInt(), b = scan.nextInt();
            System.out.println(solve(b) - solve(a - 1));
        }
    }

    public static int solve(int n) {
        for (int[] ints : dp) Arrays.fill(ints, -1);
        int pos = 0;
        while (n != 0) {
            arr[pos++] = n % 10;
            n /= 10;
        }
        return dfs(pos - 1, 0, true);
    }

    public static int dfs(int pos, int pre, boolean limit) {
        if (pos == -1) return 1; // 遍历完所有数位，表明n本身也是不降数
        if (dp[pos][pre] != -1 && !limit) return dp[pos][pre];
        int res = 0, up = limit ? arr[pos] : 9;
        for (int i = 0; i <= up; i++) {
            if (i < pre) continue;
            res += dfs(pos - 1, i, i == up && limit);
        }
        if (!limit) dp[pos][pre] = res;
        return res;
    }
}
