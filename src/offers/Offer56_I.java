package offers;

import java.util.Arrays;

/**
 * 剑指 Offer 56 - I. 数组中数字出现的次数
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [4,1,4,6]
 * 输出：[1,6] 或 [6,1]
 * 示例 2：
 *
 * 输入：nums = [1,2,10,4,1,4,3,3]
 * 输出：[2,10] 或 [10,2]
 *
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/
 */
public class Offer56_I {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(singleNumbers(new int[]{1, 2, 10, 4, 1, 4, 3, 3})));
    }

    public static int[] singleNumbers(int[] nums) {
        int res = 0, div = 1, a = 0, b = 0;
        for (int num : nums) res ^= num;
        while ((div & res) == 0) div <<= 1;
        for (int num : nums) {
            if ((div & num) == 0) a ^= num;
            else b ^= num;
        }
        return new int[]{a, b};
    }
}
