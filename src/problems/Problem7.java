package problems;

import java.util.HashMap;
import java.util.Map;

/**
 * 7. 整数反转
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 *
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 *
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 *
 *
 * 示例 1：
 *
 * 输入：x = 123
 * 输出：321
 * 示例 2：
 *
 * 输入：x = -123
 * 输出：-321
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Problem7 {

    public static void main(String[] args) {
        System.out.println(reverse(1534236469));
    }

    public static int reverse_(int x) {
        String s = x + "";
        String a = "";
        char[] arr = s.toCharArray();
        for (int i = arr.length - 1; i >= 0; i--) {
            if (i == 0 && arr[i] == '-') {
                a = arr[i] + a;
            } else {
                a += arr[i] + "";
            }
        }

        long ans = Long.parseLong(a);

        return ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE ? 0 : (int) ans;
    }

    public static int reverse(int x) {
        int y = 0;
        int last;
        while (x != 0) {
            last = y;
            y = y * 10 + x % 10;

            if (last != y / 10) {
                return 0;
            }
            x = x / 10;
        }
        return y;
    }
}
