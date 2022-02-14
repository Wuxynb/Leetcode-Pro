package offers;

/**
 * 剑指 Offer 43. 1～n 整数中 1 出现的次数
 * 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
 *
 * 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 12
 * 输出：5
 * 示例 2：
 *
 * 输入：n = 13
 * 输出：6
 *
 * 链接：https://leetcode-cn.com/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof/
 */
public class Offer43 {
    public static void main(String[] args) {
        System.out.println(countDigitOne(1024));
    }

    public static int countDigitOne(int n) {
        // 高位，      低位，    当前位，   数量，      位次数（个十百千万）
        int high = n, low = 0, cur = 0, count = 0, num = 1;
        while (high != 0 || cur != 0) {
            cur = high % 10;
            high /= 10;
            if (cur == 0) count += high * num;
            else if (cur == 1) count += high * num + low + 1;
            else count += (high + 1) * num;
            low = cur * num + low;
            num *= 10;
        }
        return count;
    }
}
