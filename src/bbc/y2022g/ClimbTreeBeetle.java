package bbc.y2022g;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * <p>
 * 有一只甲壳虫想要爬上一颗高度为n 的树，它一开始位于树根，高度为0。
 * <p>
 * 当它尝试从高度 i-1 爬到高度为i 的位置时有 Pi 的概率会掉回树根。
 * <p>
 * 求它从树根爬到树顶时，经过的时间的期望值是多少。
 * <p>
 * 输入格式
 * <p>
 * 输入第一行包含一个整数 n 表示树的高度。
 * <p>
 * 接下来 n 行每行包含两个整数xi, yi，用一个空格分隔，表示Pi = xi/yi。
 * <p>
 * 20%的测试数据：1≤n≤2，1≤xi< yi≤20;
 * <p>
 * 50%的测试数据：1≤n≤500，1≤xi< yi<200；
 * <p>
 * 100%的测试数据：1≤n≤100000，1≤xi< yi≤10^9。
 * <p>
 * 输出格式
 * <p>
 * 输出一行包含一个整数表示答案，答案是一个有理数，请输出答案对质数 998244353 取模的结果。
 * <p>
 * 其中有理数 a/b 对质数 P 取模的结果是整数 c 满足0 ≤ c < P 且c · b ≡ a (mod P)。
 * <p>
 * 输入样例 复制
 * <p>
 * 样例1：
 * <p>
 * 1
 * <p>
 * 1 2
 * <p>
 * <p>
 * 样例2：
 * <p>
 * 3
 * <p>
 * 1 2
 * <p>
 * 3 5
 * <p>
 * 7 11
 * <p>
 * 输出样例 复制
 * <p>
 * 样例1：
 * <p>
 * 2
 * <p>
 * <p>
 * 样例2：
 * <p>
 * 623902744
 * <p>
 * 分类标签
 * <p>
 * 进阶题 动态规划 数论 期望DP
 */
public class ClimbTreeBeetle {
    static int maxn = 100005;
    static long[] dp = new long[maxn];  // dp[i] 表示从i到终点的花费的期望时间
    static long mod = 998244353L;
    static long[] p = new long[maxn]; // pi 表示在 高度p处掉下去的概率

    /*
     *   dp[i] 表示从i到终点的花费的期望时间
     *   当前在第 i-1 层，有两种情况：要么到第 i + 1 层，要么回到第 0 层
     *      1、到达第 i 层有 (1-pi)dp[i] + 1
     *      2、回到第 0 层有 pi * dp[0]
     *   故有状态转移方程：
     *      dp[i-1] = pi * dp[0] + (1-pi)dp[i] + 1
     *      dp[n] = 0
     *    递推得：
     *      dp[0] = p1 * dp[0] + (1-p1) * dp[1] + 1
     *
     * ==============================================================
     *
     * 分析
     *  先考虑求从0到1的期望。
     *  有p pp1的概率掉落，1−p1的概率向上移动一次
     *  只走一次上去的期望时间是 1*(1-p_1)
     *  只走两次上去的期望时间是 2*(1-p_1)*p_1
     *  只走三次上去的期望时间是 3*(1-p_1)*p_1^2
     *  …
     *  所以期望时间是 Ex(1)=Σ i*(1-p1)*p1^(i-1) = (1-p1) * Σ i * p1^(i-1)
     *  令F ( x ) = ∑_i=1 i * x^(i-1)则 F(x) = Σ_i=1 (x^i)' = (Σ_i=1 x^i)' = (Σ_i=0 x^i)'
     *  显然,F ( x ) F(x)F(x)是首项为x xx的等比数列求和，运用等比数列求和公式可得F ( x ) = ( 1 − x ∞ 1 − x ) ′
     *  所以，E x ( 1 ) = ( 1 − p 1 ) ∗ F ( p 1 ) = ( 1 − p 1 ) ∗ 1 ( 1 − p 1 ) 2 = 1 1 − p 1
     *  那么从n-1到n的期望时间分析如下：
     *  一次上去的期望时间是(E x ( n − 1 ) + 1 ) ∗ ( 1 − p n )
     *  (理解为第一次掉下去，第二次上去)
     *  三次上去的期望时间是(3 ∗ （ E x ( n − 1 ) + 1 ) ) ∗ ( 1 − p n ) ∗ p n 2
     *  (理解为前两次掉下去，第三次上去)
     *  …
     *  那么，同理可得 E_x(n)=(E_x(n-1)+1)*\sum_{i=1}i*p_n^{i-1}=(E_x(n-1)+1)*\frac{1}{1-p_n}E
     *
     *  即得到递推式Ex(n) = (Ex(n−1) + 1)∗ 1/1−p_n
     *  即 Ex(n) = (Ex(n−1)+1) ∗ yi / yi−xi
     *  对于除以(yi − xi)在 mod P的意义下等价于乘以(yi−xi)^−1 ，由于P为质数，根据费马小定理，运用快速幂求逆元。
     *
     * link: https://blog.csdn.net/qq_36408082/article/details/124089032
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Arrays.fill(dp, -1);
        int n = scan.nextInt();
        long ans = 0;
        for (int i = 1; i <= n; i++) {
            long x = scan.nextLong(), y = scan.nextLong();
            ans = (ans + 1) * y % mod * quickMi(y - x, mod - 2) % mod;
            ans = (ans + mod) % mod;
        }
        System.out.println(ans);
        scan.close();
    }

    public static long quickMi(long a, long b) {
        long res = 1;
        while (b != 0) {
            if ((b & 1) == 1) res = res * a % mod;
            a = a * a % mod;
            b >>= 1;
        }
        return res;
    }
}
