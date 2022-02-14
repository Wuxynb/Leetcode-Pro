package offers;

/**
 * 剑指 Offer 56 - II. 数组中数字出现的次数 II
 * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,4,3,3]
 * 输出：4
 * 示例 2：
 * <p>
 * 输入：nums = [9,1,7,9,7,9,7]
 * 输出：1
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= nums.length <= 10000
 * 1 <= nums[i] < 2^31
 * <p>
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/
 */
public class Offer56_II {
    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{9, 1, 7, 9, 7, 9, 7}));
    }

    /**
     * a = 0, b = 0;
     * <p>
     * x ^ 0 = x;
     * x ^ x = 0;
     * x & ~x = 1;
     * x & 0 = 0;
     * <p>
     * 1:  a = a ^ num & ~b = num,  b = b ^ num & ~a = 0;
     * 2:  a = num ^ num & ~0 = 0, b = 0 ^ num & ~0 = num;
     */
    public static int singleNumber(int[] nums) {
        int a = 0, b = 0;
        for (int num : nums) {
            a = a ^ num & ~b;
            b = b ^ num & ~a;
        }
        return a;
    }
}
