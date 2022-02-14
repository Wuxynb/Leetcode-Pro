package problems;

/**
 * 50. Pow(x, n)
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。
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
 * 链接：https://leetcode-cn.com/problems/powx-n/
 */
public class Problem50 {

    public static void main(String[] args) {
        System.out.println(myPow(2, 10));
    }

    public static double myPow(double x, int n) {
        long N = n;
//        return N >= 0 ? quickMul(x, N) : 1. / quickMul(x, -N);
        return N >= 0 ? quickMul2(x, N) : 1. / quickMul2(x, -N);
    }

    public static double quickMul(double x, long N) {
        if (N == 0) return 1.;
        double y = quickMul(x, N >> 1);
        return (N & 1) == 1 ? y * y * x : y * y;
    }

    public static double quickMul2(double x, long N) {
        double ans = 1., xV = x;
        while (N != 0) {
            if ((N & 1) == 1) ans *= xV;
            xV *= xV;
            N >>= 1;
        }

        return ans;
    }
}
