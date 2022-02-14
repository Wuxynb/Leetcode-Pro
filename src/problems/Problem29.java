package problems;

/**
 * 29. 两数相除
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * <p>
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * <p>
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
 * 示例 2:
 * <p>
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 解释: 7/-3 = truncate(-2.33333..) = -2
 * <p>
 *
 * 链接：https://leetcode-cn.com/problems/divide-two-integers/
 */
public class Problem29 {

    public static void main(String[] args) {
//        System.out.println(dividePlus(10, 3));
        System.out.println(dividePlusTwo(-2147483648, 2));
    }

    public static int divide_(int dividend, int divisor) {
        long ans = (long) dividend / divisor;

        return ans > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) ans;
    }

    public static int divide(int dividend, int divisor) {
        int c = 0;
        boolean f = (dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0);
        if (divisor == 1) return dividend;
        if (divisor == -1) {
            if (dividend != Integer.MIN_VALUE) return -dividend;
            else return Integer.MAX_VALUE;
        }

        long dividend2 = dividend, divisor2 = divisor;
        dividend2 = dividend2 < 0 ? -dividend2 : dividend2;
        divisor2 = divisor2 < 0 ? -divisor2 : divisor2;

        while (dividend2 >= divisor2) {
            dividend2 -= divisor2;
            c++;
        }

        return f ? -c : c;
    }

    // as for a / b, answer ∈ [0, a]
    public static int dividePlus(int a, int b) {
        long x = a, y = b;
        boolean isNeg = (x > 0 && y < 0) || (x < 0 && y > 0);
        if (x < 0) x = -x;
        if (y < 0) y = -y;
        long l = 0, r = x;
        // dichotomy
        while (l < r) {
            long mid = l + r + 1 >> 1;
            if (mul(mid, y) <= x) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        long ans = isNeg ? -l : l;
        if (ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE) return Integer.MAX_VALUE;
        return (int) ans;
    }

    // quick multiplication
    static long mul(long a, long k) {
        long ans = 0;
        while (k > 0) {
            if ((k & 1) == 1) ans += a;
            k >>= 1;
            a += a;
        }
        return ans;
    }

    public static int dividePlusTwo(int dividend, int divisor) {
        if (divisor == 1) return dividend;
        if (divisor == -1) return dividend == Integer.MIN_VALUE ? Integer.MAX_VALUE : -dividend;
        boolean sign = (dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0);
        long x = Math.abs((long) dividend);
        long y = Math.abs((long) divisor);
        long ans = div(x, y);
        if (ans > Integer.MAX_VALUE) return Integer.MAX_VALUE;

        return (int) (sign ? -ans : ans);
    }

    /*
        100/3
        100>3 100>6 100>12 100>24 100>48 100>96 100<192 ---- 使用了 2^5 = 32 个3，还剩 100 - 96 = 4 需要被除
        4>3 4<6                                         ---- 使用了 2^0 = 1  个3，还剩 4   - 3  = 1 需要被除
        1<3                                             ---- 被除数小于除数，递归结束，总计使用了 33 个 3
     */
    public static long div(long a, long b) {
        if (a < b) return 0;
        long count = 1;
        long temp = b;
        while ((temp << 1) <= a) { // temp << 1 == 0, out of bounds
            count <<= 1;
            temp <<= 1;
        }
        return count + div(a - temp, b);
    }
}
