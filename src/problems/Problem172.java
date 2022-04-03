package problems;

import java.math.BigInteger;

/**
 *  172. 阶乘后的零
 * 给定一个整数 n ，返回 n! 结果中尾随零的数量。
 *
 * 提示 n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：0
 * 解释：3! = 6 ，不含尾随 0
 * 示例 2：
 *
 * 输入：n = 5
 * 输出：1
 * 解释：5! = 120 ，有一个尾随 0
 * 示例 3：
 *
 * 输入：n = 0
 * 输出：0
 *
 * 链接：https://leetcode-cn.com/problems/factorial-trailing-zeroes/
 */
public class Problem172 {
    public static void main(String[] args) {
        System.out.println(trailingZeroes(5));
        System.out.println(trailingZeroes(10));

        System.out.println(trailingZeroes3(5));
        System.out.println(trailingZeroes3(10));

        System.out.println(trailingZeroes4(5));
        System.out.println(trailingZeroes4(10));
    }

    public static int trailingZeroes3(int n) {
        int ans = 0;
        for (int i = 5; i <= n; i += 5) {
            for (int j = i; j % 5 == 0; j /= 5) {
                ans++;
            }
        }
        return ans;
    }

    public static int trailingZeroes4(int n) {
        int ans = 0;
        while (n != 0) {
            ans += (n /= 5);
        }
        return ans;
    }

    /*
        结论1：只需要找因子2，5的个数，就能确定0的个数
        结论2：2的个数比5多，所以只要找因子5的个数就行。
        结论3：每间隔 5 个数有一个数可以被 5 整除, 然后在这些可被 5 整除的数中, 每间隔 5 个数又有一个可以被 25 整除, 故要再除一次, ...
      直到结果为 0, 表示没有能继续被 5 整除的数了.
     */
    public static int trailingZeroes(int n) {
        return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);
    }

    public static int trailingZeroes1(int n) {
        int count = 0;
        for (int i = 5; i <= n; i += 5) {
            int currFactor = i;
            while (currFactor % 5 == 0) {
                count++;
                currFactor /= 5;
            }
        }
        return count;
    }

    public static int trailingZeroes2(int n) {
        int count = 0;
        for (int i = 5; i <= n; i += 5) {
            int powerOfFive = 5;
            while (i % powerOfFive == 0) {
                count++;
                powerOfFive *= 5;
            }
        }
        return count;
    }

    public static int trailingZeroesLow(int n) {
        BigInteger nFactorial = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            nFactorial = nFactorial.multiply(BigInteger.valueOf(i));
        }
        int count = 0;
        while (nFactorial.mod(BigInteger.TEN).equals(BigInteger.ZERO)) {
            nFactorial = nFactorial.divide(BigInteger.TEN);
            count++;
        }
        return count;
    }
}
