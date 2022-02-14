package offers;

/**
 * 剑指 Offer 42. 连续子数组的最大和
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 *
 * 要求时间复杂度为O(n)。
 *
 *
 *
 * 示例1:
 *
 * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 * 链接：https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/
 */
public class Offer42 {
    public static void main(String[] args) {

    }

    public int maxSubArray(int[] nums) {
        int ans = nums[0], sum = 0;
        for (int num : nums) {
            if (sum < 0) sum = num;
            else sum += num;
            ans = Math.max(ans, sum);
        }
        return ans;
    }
}
