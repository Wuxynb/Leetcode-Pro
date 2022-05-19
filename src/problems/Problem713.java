package problems;

/**
 * 713. 乘积小于 K 的子数组
 * 给你一个整数数组 nums 和一个整数 k ，请你返回子数组内所有元素的乘积严格小于 k 的连续子数组的数目。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [10,5,2,6], k = 100
 * 输出：8
 * 解释：8 个乘积小于 100 的子数组分别为：[10]、[5]、[2],、[6]、[10,5]、[5,2]、[2,6]、[5,2,6]。
 * 需要注意的是 [10,5,2] 并不是乘积小于 100 的子数组。
 * 示例 2：
 *
 * 输入：nums = [1,2,3], k = 0
 * 输出：0
 *
 *
 * 提示:
 *
 * 1 <= nums.length <= 3 * 104
 * 1 <= nums[i] <= 1000
 * 0 <= k <= 106
 *
 * link: https://leetcode-cn.com/problems/subarray-product-less-than-k/
 */
public class Problem713 {
    public static void main(String[] args) {
        System.out.println(numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100));
        System.out.println(numSubarrayProductLessThanK(new int[]{1, 2, 3}, 0));
    }

    /**
     * 输入：nums = [10,5,2,6], k = 100
     * 输出：8
     * 解释：8 个乘积小于 100 的子数组分别为：[10]、[5]、[2],、[6]、[10,5]、[5,2]、[2,6]、[5,2,6]。
     * 需要注意的是 [10,5,2] 并不是乘积小于 100 的子数组。
     */
    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        int left = 0, right = 0, mul = 1, ans = 0;
        while (right < nums.length) {
            mul *= nums[right++];
            while (left < right && mul >= k) {
                mul /= nums[left++];
            }
            ans += right - left; // [left, right)新增每一个都可以，包括自己本身（上一轮的区间数）
        }
        return ans;
    }

    public static int numSubarrayProductLessThanKBf(int[] nums, int k) {
        int ans = 0;
        for (int i = 0, n = nums.length; i < n; i++) {
            if (nums[i] >= k) continue;
            for (int j = i, mul = 1; j < n && mul * nums[j] < k; j++) {
                ans++;
                mul *= nums[j];
            }
        }
        return ans;
    }
}
