package problems;

/**
 * 462. 最少移动次数使数组元素相等 II
 * 给你一个长度为 n 的整数数组 nums ，返回使所有数组元素相等需要的最少移动数。
 *
 * 在一步操作中，你可以使数组中的一个元素加 1 或者减 1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：2
 * 解释：
 * 只需要两步操作（每步操作指南使一个元素加 1 或减 1）：
 * [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
 * 示例 2：
 *
 * 输入：nums = [1,10,2,9]
 * 输出：16
 *
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 *
 * link: https://leetcode.cn/problems/minimum-moves-to-equal-array-elements-ii/
 */
public class Problem462 {
    public static void main(String[] args) {

    }

    public int minMoves2(int[] nums) {
        int ans = 0, n = nums.length, mid = quickSelect(0, n - 1, 1 + n >> 1, nums);
        for (int num : nums) ans += Math.abs(num - mid);
        return ans;
    }

    public int quickSelect(int low, int high, int k, int[] nums) {
        if (low == high) return nums[low];
        int i = low - 1, j = high + 1, m = nums[(i + j) >> 1];
        while (i < j) {
            while (i < j && nums[--j] > m);
            while (i < j && nums[++i] < m);
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        int cntl = j - low + 1;
        if (cntl >= k) return quickSelect(low, j, k, nums);
        else return quickSelect(j + 1, high, k - cntl, nums);
    }
}
