package offers;

/**
 * 剑指 Offer 14- II. 剪绳子 II
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1] 。请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 *
 *
 * 示例 1：
 *
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1
 * 示例 2:
 *
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
 *
 * link: https://leetcode-cn.com/problems/jian-sheng-zi-ii-lcof/
 */
public class Offer14_II {
    public static void main(String[] args) {
        System.out.println(cuttingRope(127));
    }

    public static int cuttingRope(int n) {
        if (n < 4) return n - 1;
        int a = n / 3, b = n % 3;
        long res = 1;
        for (int i = 0; i < (b == 1 ? a - 1 : a); i++) {
            res = (long) (res * 3 % (1e9 + 7));
        }
        if (b == 0) return (int) (res % (1e9 + 7));
        if (b == 1) return (int) (res * 4 % (1e9 + 7));
        return (int) (res * 2 % 1000000007);
    }
}
