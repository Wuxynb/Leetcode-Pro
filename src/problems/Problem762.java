package problems;

/**
 * 762. 二进制表示中质数个计算置位
 * 给你两个整数 left 和 right ，在闭区间 [left, right] 范围内，统计并返回 计算置位位数为质数 的整数个数。
 *
 * 计算置位位数 就是二进制表示中 1 的个数。
 *
 * 例如， 21 的二进制表示 10101 有 3 个计算置位。
 *
 *
 * 示例 1：
 *
 * 输入：left = 6, right = 10
 * 输出：4
 * 解释：
 * 6 -> 110 (2 个计算置位，2 是质数)
 * 7 -> 111 (3 个计算置位，3 是质数)
 * 9 -> 1001 (2 个计算置位，2 是质数)
 * 10-> 1010 (2 个计算置位，2 是质数)
 * 共计 4 个计算置位为质数的数字。
 * 示例 2：
 *
 * 输入：left = 10, right = 15
 * 输出：5
 * 解释：
 * 10 -> 1010 (2 个计算置位, 2 是质数)
 * 11 -> 1011 (3 个计算置位, 3 是质数)
 * 12 -> 1100 (2 个计算置位, 2 是质数)
 * 13 -> 1101 (3 个计算置位, 3 是质数)
 * 14 -> 1110 (3 个计算置位, 3 是质数)
 * 15 -> 1111 (4 个计算置位, 4 不是质数)
 * 共计 5 个计算置位为质数的数字。
 *
 *
 * 提示：
 *
 * 1 <= left <= right <= 106
 * 0 <= right - left <= 104
 *
 * link: https://leetcode-cn.com/problems/prime-number-of-set-bits-in-binary-representation/
 */
public class Problem762 {
    public static void main(String[] args) {

    }

    /**
     * 1，计算整数x的二进制表示有多少个1： x&=x-1可以消除x最低位的1，while循环计数，直到x=0即可。
     *
     * 2，只保留整数x最低位的1： x&-x ，暨鼎鼎大名的 lowbit
     */
    public int countPrimeSetBits(int left, int right) {
        int ans = 0;
        for (int i = left; i <= right; i++) {
            int bit = Integer.bitCount(i);
            if (bit == 2 || bit == 3 || bit == 5 || bit == 7 || bit == 11 || bit == 13 || bit == 17 || bit == 19) ans++;
        }
        return ans;
    }

    public int countPrimeSetBitsP(int left, int right) {
        int ans = 0;
        for (int i = left; i <= right; i++) {
            if (((1 << Integer.bitCount(i)) & 665772) != 0) ans++;
        }
        return ans;
    }

    public int getOneNum(int n) {
        int ans = 0;
        while (n != 0) {
            if ((n & 1) == 1) ans++;
            n >>= 1;
        }
        return ans;
    }
}
