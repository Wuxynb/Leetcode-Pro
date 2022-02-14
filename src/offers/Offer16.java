package offers;

/**
 * 剑指 Offer 16. 数值的整数次方
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。
 *
 *
 *
 * 示例 1：
 *
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * 示例 2：
 *
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * 示例 3：
 *
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 *
 * link: https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/
 */
public class Offer16 {
    public static void main(String[] args) {

    }

    /* 快速幂
       迭代
     */
    public static double myPowRecursion(double x, int n) {
        if (n == 0) return 1;
        if (n == -1) return 1 / x;
        if ((n & 1) == 1) return x * myPowRecursion(x * x, n >> 1);
        else return myPowRecursion(x * x, n >> 1);
    }

    /*  快速幂
        n为偶数：x^n = x^(n/2) * x^(n/2)
        n为奇数：x^n = x^(n/2) * x^(n/2) * x
     */
    public static double myPow(double x, int n) {
        if (x == 0) return 0;
        long b = n;
        double res = 1.;
        if (b < 0) {
            x = 1 / x;
            b = -b;
        }
        while (b > 0) {
            if ((b & 1) == 1) res *= x;
            x *= x;
            b >>= 1;
        }
        return res;
    }
}
