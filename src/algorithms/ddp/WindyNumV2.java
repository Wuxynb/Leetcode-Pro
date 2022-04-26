package algorithms.ddp;

import java.util.Arrays;

/**
 * windy定义了一种windy数。不含前导零且相邻两个数字之差至少为2的正整数被称为windy数。 windy想知道，在A和B之间，包括A和B，总共有多少个windy数？
 * <p>
 * 输入格式
 * 　　包含两个整数，A B。
 * <p>
 * 输出格式
 * 　　一个整数
 * <p>
 * 数据范围和提示
 * 【数据规模和约定】
 * <p>
 * 100%的数据，满足 1 <= A <= B <= 2000000000 。
 * <p>
 * Sample Input
 * 【输入样例一】
 * 1 10
 * <p>
 * 【输入样例二】
 * <p>
 * 25 50
 * Sample Output
 * 【输出样例一】
 * <p>
 * 9
 * <p>
 * 【输出样例二】
 * <p>
 * 20
 * <p>
 * 分析：
 * dp[i][j]:i表示数位，j表示当前位的前一位上的数字。
 * 注意要加上前导零的判断，只有当前位的前一位不是前导零且两者之差小于2的可以跳过
 * <p>
 * 原文链接：https://blog.csdn.net/qq_45928501/article/details/113784068
 */
public class WindyNumV2 {
    static int N = 11;
    static int[] a = new int[N];
    static int[][] dp = new int[N][10]; // dp[i][j]:i表示数位，j表示当前位的前一位上的数字

    public static void main(String[] args) {
        int m = 1, n = 10;
        init();
        System.out.println((solve(n) - solve(m - 1)));
        init();
        System.out.println((solve(50) - solve(25 - 1)));
    }

    static void init() {
        a = new int[N];
        dp = new int[N][10];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }
    }

    static int solve(int n) {
        int pos = 0;
        while (n > 0) {
            a[pos++] = n % 10;
            n /= 10;
        }
        return dfs(pos - 1, 0, true, true);
    }

    /**
     * @param pos   当前数位
     * @param pre   前一位数
     * @param lead  是否有前导零
     * @param limit 是否为边界
     * @return 返回 dp[pos][pre] 的方案数
     */
    static int dfs(int pos, int pre, boolean lead, boolean limit) {
        if (pos == -1) return 1;
        if (!lead && !limit && dp[pos][pre] != -1) return dp[pos][pre];
        int res = 0, up = limit ? a[pos] : 9;
        for (int i = 0; i <= up; i++) {
            if (!lead && Math.abs(pre - i) < 2) continue;
            res += dfs(pos - 1, i, lead && i == 0, limit && i == a[pos]);
        }
        if (!lead && !limit) dp[pos][pre] = res;
        return res;
    }
}
