package ojs.luogu;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目背景
 * <p>
 * 本题由世界上最蒟蒻最辣鸡最撒比的SOL提供。
 * <p>
 * 寂月城网站是完美信息教室的官网。地址：http://191.101.11.174/mgzd 。
 * <p>
 * 题目描述
 * <p>
 * 辣鸡蒟蒻SOL是一个傻逼，他居然觉得数很萌！
 * <p>
 * 好在在他眼里，并不是所有数都是萌的。只有满足“存在长度至少为2的回文子串”的数是萌的——也就是说，101是萌的，因为101本身就是一个回文数；110是萌的，
 * 因为包含回文子串11；但是102不是萌的，1201也不是萌的。
 * <p>
 * 现在SOL想知道从l到r的所有整数中有多少个萌数。
 * <p>
 * 由于答案可能很大，所以只需要输出答案对1000000007（10^9+7）的余数。
 * <p>
 * 输入格式
 * <p>
 * 输入包含仅1行，包含两个整数：l、r。
 * <p>
 * 输出格式
 * <p>
 * 输出仅1行，包含一个整数，即为答案。
 * <p>
 * 输入输出样例
 * <p>
 * 输入 #1复制
 * <p>
 * 1 100
 * <p>
 * 输出 #1复制
 * <p>
 * 10
 * <p>
 * 输入 #2复制
 * <p>
 * 100 1000
 * <p>
 * 输出 #2复制
 * <p>
 * 253
 * <p>
 * 说明/提示
 * <p>
 * 记n为r在10进制下的位数。
 * <p>
 * 对于10%的数据，n <= 3。
 * <p>
 * 对于30%的数据，n <= 6。
 * <p>
 * 对于60%的数据，n <= 9。
 * <p>
 * 对于全部的数据，n <= 1000，l < r。
 */
public class P3413 {
    static int N = 1005;
    static int mod = (int) 1e9 + 7;
    static int[] array = new int[N];
    static long[][][][] dp = new long[N][10][10][2]; // f[i][j][k][l]，长度为N，前一位是j，再前一位是k，之前是否位萌数l 时的萌数个数

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str1 = scan.next(), str2 = scan.next();
        // 注意：这里一定要模不然会输出负数（因为取完模之后dp[b]的结果可能会小于dp[a-1]的结果，所以通过再取模得正
        System.out.println((solve(str2, 0) - solve(str1, -1) + mod) % mod);
    }

    // https://www.luogu.com.cn/blog/wmz1056193138/solution-p3413
    public static long solve(String str, int d) {
        for (long[][][] longs : dp) for (long[][] longs2 : longs) for (long[] longs1 : longs2) Arrays.fill(longs1, -1);
        Arrays.fill(array, 0);
        int pos = str.length();
        for (char c : str.toCharArray()) {
            array[pos--] = c - '0';
        }
        array[pos + 1] += d;
        return dfs(str.length(), -2, -2, 0, true, true);
    }

    /**
     * 计算 位数位pos、前一位位pre1，前两位为pre2，时共有的萌数个数
     *
     * @param pos   当前位置
     * @param pre1  当前位置的第前一位，-2表示前导零（同下）
     * @param pre2  当前位置的第前两位，-2表示前导零（也可以用10来代替，总之不在0~9范围内，减少判断）
     * @param flag  如果flag为1 则当前这个数是萌的 否则它为0
     * @param lead  前导零
     * @param limit 最高位限制
     */
    public static long dfs(int pos, int pre1, int pre2, int flag, boolean lead, boolean limit) {
        if (pos == 0) return flag;
        if (pre2 != -2 && !lead && !limit && dp[pos][pre1][pre2][flag] != -1) return dp[pos][pre1][pre2][flag];
        long res = 0;
        int up = limit ? array[pos] : 9;
        for (int i = 0; i <= up; i++) {
            if (flag == 1)
                res = (res + dfs(pos - 1, (lead && i == 0) ? -2 : i, pre1, 1, lead && i == 0, i == up && limit)) % mod;
            else {
                // 如果不能确定是不是萌数，则进行判断
                if (i == pre1 || i == pre2) // 如果为萌数
                    res = (res + dfs(pos - 1, (lead && i == 0) ? -2 : i, pre1, 1, lead && i == 0, i == up && limit)) % mod;
                else // 不为萌数
                    res = (res + dfs(pos - 1, (lead && i == 0) ? -2 : i, pre1, 0, lead && i == 0, i == up && limit)) % mod;
            }
        }
        if (pre2 != -2 && !lead && !limit) dp[pos][pre1][pre2][flag] = res;
        return res;
    }
}
