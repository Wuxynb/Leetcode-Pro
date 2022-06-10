package ojs.hdoj;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Problem Description
 * <p>
 * 杭州人称那些傻乎乎粘嗒嗒的人为62（音：laoer）。
 * <p>
 * 杭州交通管理局经常会扩充一些的士车牌照，新近出来一个好消息，以后上牌照，不再含有不吉利的数字了，这样一来，就可以消除个别的士司机和乘客的心理障碍，更安全地服务大众。
 * <p>
 * 不吉利的数字为所有含有4或62的号码。例如：
 * <p>
 * 62315 73418 88914
 * <p>
 * 都属于不吉利号码。但是，61152虽然含有6和2，但不是62连号，所以不属于不吉利数字之列。
 * <p>
 * 你的任务是，对于每次给出的一个牌照区间号，推断出交管局今次又要实际上给多少辆新的士车上牌照了。
 * <p>
 * <p>
 * Input
 * <p>
 * 输入的都是整数对n、m（0< n≤m<1000000），如果遇到都是0的整数对，则输入结束。
 * <p>
 * <p>
 * Output
 * <p>
 * 对于每个整数对，输出一个不含有不吉利数字的统计个数，该数值占一行位置。
 * <p>
 * <p>
 * Sample Input
 * <p>
 * 1 100
 * <p>
 * 0 0
 * <p>
 * <p>
 * Sample Output
 * <p>
 * 80
 * <p>
 * <p>
 * Author
 * qianneng
 */
public class P2089 {
    static int N = 8;
    static int[] array = new int[N];
    static int[][] dp = new int[N][10];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n, m;
        while ((n = scan.nextInt()) != 0 && (m = scan.nextInt()) != 0) {
            System.out.println(solve(m) - solve(n - 1));
        }
    }

    public static int solve(int n) {
        for (int[] ints : dp) Arrays.fill(ints, -1);
        int pos = 0;
        while (n != 0) {
            array[pos++] = n % 10;
            n /= 10;
        }
        return dfs(pos - 1, 0, true);
    }

    private static int dfs(int pos, int pre, boolean limit) {
        if (pos == -1) return 1;
        if (dp[pos][pre] != -1 && !limit) return dp[pos][pre];
        int res = 0, up = limit ? array[pos] : 9;
        for (int i = 0; i <= up; i++) {
            if (i == 2 && pre == 6) continue;
            if (i == 4) continue;
            res += dfs(pos - 1, i, i == up && limit); // i == array[pos]也可
        }
        if (!limit) dp[pos][pre] = res;
        return res;
    }
}
