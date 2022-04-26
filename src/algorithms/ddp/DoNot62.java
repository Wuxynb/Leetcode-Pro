package algorithms.ddp;

import java.util.Arrays;

/**
 * Problem Description
 * 杭州人称那些傻乎乎粘嗒嗒的人为62（音：laoer）。
 * 杭州交通管理局经常会扩充一些的士车牌照，新近出来一个好消息，以后上牌照，不再含有不吉利的数字了，这样一来，就可以消除个别的士司机和乘客的心理障碍，更安全地服务大众。
 * 不吉利的数字为所有含有4或62的号码。例如：
 * 62315 73418 88914
 * 都属于不吉利号码。但是，61152虽然含有6和2，但不是62连号，所以不属于不吉利数字之列。
 * 你的任务是，对于每次给出的一个牌照区间号，推断出交管局今次又要实际上给多少辆新的士车上牌照了。
 * <p>
 * <p>
 * Input
 * 输入的都是整数对n、m（0<n≤m<1000000），如果遇到都是0的整数对，则输入结束。
 * <p>
 * <p>
 * Output
 * 对于每个整数对，输出一个不含有不吉利数字的统计个数，该数值占一行位置。
 * <p>
 * <p>
 * Sample Input
 * 1 100
 * 0 0
 * <p>
 * <p>
 * Sample Output
 * 80
 *
 * link: http://acm.hdu.edu.cn/showproblem.php?pid=2089
 */
public class DoNot62 {
    static int[] a = new int[20];
    static int[][] dp = new int[20][2];

    public static void main(String[] args) {
        DoNot62 doNot62 = new DoNot62();
        for (int[] ints : dp) Arrays.fill(ints, -1);
        int m = 1, n = 100;
        System.out.println((doNot62.solve(n) - doNot62.solve(m - 1)));
    }

    int solve(int x) {
        int pos = 0;
        while (x > 0) { // 把数位都分解出来
            a[pos++] = x % 10;
            x /= 10;
        }
        return dfs(pos - 1, false, true);
    }

    /**
     * 深度优先搜索-记忆化
     *
     * @param pos   当前位数-第几位
     * @param state 上一位是否是 6
     * @param limit 判断是否为边界，例如（567，第一位为5，则其为上边界，第一位只能取0~5，当取第一位取5时，第二位只能取0~6（6是第二位的
     *              边界），第一位为0~4则不是上边界，则其第二为可以取0~9）
     * @return 返回符合条件的个数
     */
    int dfs(int pos, boolean state, boolean limit) {
        if (pos == -1) return 1;
        int j = state ? 1 : 0;
        if (!limit && dp[pos][j] != -1) return dp[pos][j]; // 如果之间出现过这种情况
        int res = 0, up = limit ? a[pos] : 9; // 判断是否为边界，如果是边界，即到边界为止，如果不是则遍历0-9
        for (int i = 0; i <= up; i++) {
            if (i == 4 || (state && i == 2)) continue; //判断是否为4或出现62
            res += dfs(pos - 1, i == 6, limit && i == up);
        }
        if (!limit) dp[pos][j] = res;
        return res;
    }
}
