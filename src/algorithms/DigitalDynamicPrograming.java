package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 数位DP
 * <p>
 *     技巧1：[X,Y] => f(Y) - f(X-1);
 *     <p>
 *     技巧2：树的角度去考虑；
 * </p>
 *
 * <p>
 *   【前导零】问题：
 *    - 是否需要特殊处理前导0这个需要根据前导0的存在是否会影响题目的性质。
 *    <p>
 *    - 这个题目会影响比如说如果你把 0012 和 12 看成一样的就会有影响，因为在0012 中0 + 0 == 0不是质数所以不符合，而 12 中1 + 2 == 3符合。
 *    <p>
 *    - 在AcWing - 1082.数字游戏 中是没有影响的因为 0012 和 12 都是不降数，0不会对后面有影响。（即如下题目）
 * </p>
 *
 * <p>
 *     link: https://yuqi-cheng.blog.csdn.net/article/details/106889377
 * </p>
 */
@SuppressWarnings("Duplicates")
public class DigitalDynamicPrograming {
    static final int N = 15;
    static int[][] f = new int[N][N]; // f[i,j] 表示最高位为j，长度为i的数中，不降数的个数

    /**
     * 给定一个区间 [l.r] ，找到这个区间上所有的“不减数”，不减数定义为从高位到低位数字大小是不减的，比如：223,669,456等等，本题不考虑【前导零】
     *
     */
    public static void main(String[] args) {
        init();
//        Scanner scan = new Scanner(System.in);
//        int left = scan.nextInt(), right = scan.nextInt();
        int left = 1, right = 100; // 5
        System.out.println((dp(right) - dp(left - 1)));// 54

        System.out.println((solve(right) - solve(left - 1)));
//        scan.close();
    }

    static void init() {
        // 长度为1的个数只有一个
        for (int i = 0; i <= 9; i++) f[1][i] = 1;

        for (int i = 2; i < N; i++) { // 长度大于1时
            for (int j = 0; j <= 9; j++) { // 枚举开头元素
                for (int k = j; k <= 9; k++) { // 将以k开头，长度为i-1的数放在数字j后面形成新的不降数
                    f[i][j] += f[i - 1][k];
                }
            }
        }
    }

    static int dp(int n) { // 迭代法
        if (n == 0) return 1;
        List<Integer> nums = new ArrayList<>();
        while (n > 0) { // 统计数位
            nums.add(n % 10);
            n /= 10;
        }
        int res = 0, last = 0; // 方案数、保留一些前缀信息，本题是上一个数是几
        for (int i = nums.size() - 1; i >= 0; i--) { // 从最高位开始枚举
            int cur = nums.get(i); // 当前这位数
            // 要保障比下一位>=上一位，所以从last开始枚举,最多枚举到x,last为上一位，也即最高位，对下一位的枚举是有限制的
            for (int j = last; j < cur; j++) { // 相对于二叉树的[左分支] 0~Am-1
                res += f[i + 1][j]; // 左端的节点有i+1个位数（因为第一位的下标是0)
            }
            if (cur < last) break; // 如果当前这位数比上一位小，那么后面的都不成立了，直接break退出
            last = cur; // 右分支，新一轮循环开始，即取 Am
            if (i == 0) res++; // 如果能顺利到最后一个数说明树的最右边这一段的每一个数都是小于等于前一位数的（即n本身也符合），因而++
        }
        return res;
    }

    static int[] a = new int[N];
    static int[][] dp = new int[N][10];

    static int solve(int n) {
        for (int[] ints : dp) Arrays.fill(ints, -1);
        int pos = 0;
        while (n > 0) {
            a[pos++] = n % 10;
            n /= 10;
        }
        return dfs(pos - 1, 0, true);
    }

    /**
     * 记忆化搜索 遍历 所有结果
     *
     * @param pos 当前位数
     * @param pre 前一位的数
     * @param limit 是否为最高界
     * @return 返回当前 结果数
     */
    static int dfs(int pos, int pre, boolean limit) {
        if (pos == -1) return 1;
        if (dp[pos][pre] != -1 && !limit) return dp[pos][pre];
        int res = 0, up = limit ? a[pos] : 9;
        for (int i = 0; i <= up; i++) {
            if (i < pre) continue;
            res += dfs(pos - 1, i, limit && i == up);
        }
        if (!limit) dp[pos][pre] = res;
        return res;
    }
}
