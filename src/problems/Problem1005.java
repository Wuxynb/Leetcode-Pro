package problems;

import java.util.Arrays;

/**
 * 1005. K 次取反后最大化的数组和
 * 给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
 *
 * 选择某个下标 i 并将 nums[i] 替换为 -nums[i] 。
 * 重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
 *
 * 以这种方式修改数组后，返回数组 可能的最大和 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [4,2,3], k = 1
 * 输出：5
 * 解释：选择下标 1 ，nums 变为 [4,-2,3] 。
 * 示例 2：
 *
 * 输入：nums = [3,-1,0,2], k = 3
 * 输出：6
 * 解释：选择下标 (1, 2, 2) ，nums 变为 [3,1,0,2] 。
 * 示例 3：
 *
 * 输入：nums = [2,-3,-1,5,-4], k = 2
 * 输出：13
 * 解释：选择下标 (1, 4) ，nums 变为 [2,3,-1,5,4] 。
 *
 * 链接：https://leetcode-cn.com/problems/maximize-sum-of-array-after-k-negations/
 */
public class Problem1005 {
    public static void main(String[] args) {
        System.out.println(largestSumAfterKNegations(new int[]{-3, -5, -2}, 4));
    }

    public static int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        int neg = 0, n = nums.length;
        for (int i : nums) if (i < 0) neg++;
        if (k <= neg) {
            for (int i = 0; i < k; i++) nums[i] = -nums[i];
        } else {
            for (int i = 0; i < neg; i++) nums[i] = -nums[i];
            System.out.println(Arrays.toString(nums));
            int idx = neg == n ? n - 1 : neg != 0 && nums[neg - 1] < nums[neg] ? neg - 1 : neg;
            for (int i = 0; i < k - neg; i++) nums[idx] = -nums[idx];
        }
        return Arrays.stream(nums).sum();
    }
}
