package problems;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 2104. 子数组范围和
 * 给你一个整数数组 nums 。nums 中，子数组的 范围 是子数组中最大元素和最小元素的差值。
 *
 * 返回 nums 中 所有 子数组范围的 和 。
 *
 * 子数组是数组中一个连续 非空 的元素序列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：4
 * 解释：nums 的 6 个子数组如下所示：
 * [1]，范围 = 最大 - 最小 = 1 - 1 = 0
 * [2]，范围 = 2 - 2 = 0
 * [3]，范围 = 3 - 3 = 0
 * [1,2]，范围 = 2 - 1 = 1
 * [2,3]，范围 = 3 - 2 = 1
 * [1,2,3]，范围 = 3 - 1 = 2
 * 所有范围的和是 0 + 0 + 0 + 1 + 1 + 2 = 4
 * 示例 2：
 *
 * 输入：nums = [1,3,3]
 * 输出：4
 * 解释：nums 的 6 个子数组如下所示：
 * [1]，范围 = 最大 - 最小 = 1 - 1 = 0
 * [3]，范围 = 3 - 3 = 0
 * [3]，范围 = 3 - 3 = 0
 * [1,3]，范围 = 3 - 1 = 2
 * [3,3]，范围 = 3 - 3 = 0
 * [1,3,3]，范围 = 3 - 1 = 2
 * 所有范围的和是 0 + 0 + 0 + 2 + 0 + 2 = 4
 * 示例 3：
 *
 * 输入：nums = [4,-2,-3,4,1]
 * 输出：59
 * 解释：nums 中所有子数组范围的和是 59
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 1000
 * -109 <= nums[i] <= 109
 *
 *
 * 进阶：你可以设计一种时间复杂度为 O(n) 的解决方案吗？
 *
 * link: https://leetcode-cn.com/problems/sum-of-subarray-ranges/
 */
public class Problem2104 {
    public static void main(String[] args) {
        System.out.println(subArrayRanges(new int[]{1, 2, 3}));
        System.out.println(subArrayRanges(new int[]{3, 1, 3}));
        System.out.println(subArrayRanges(new int[]{1, 3, 3}));
        System.out.println(subArrayRanges(new int[]{4, -2, -3, 4, 1}));
        System.out.println(subArrayRanges(new int[]{2, 1, 3}));

        System.out.println(subArrayRangesStack(new int[]{1, 2, 3}));
        System.out.println(subArrayRangesStack(new int[]{3, 1, 3}));
        System.out.println(subArrayRangesStack(new int[]{1, 3, 3}));
        System.out.println(subArrayRangesStack(new int[]{4, -2, -3, 4, 1}));
        System.out.println(subArrayRangesStack(new int[]{2, 1, 3}));
    }

    // [1, 2, 3]
    // [2, 1, 3]
    // [-3, -2, 1, 4, 4]
    public static long subArrayRanges(int[] nums) {
        long ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            for (int j = i; j < nums.length; j++) {
                min = Math.min(min, nums[j]);
                max = Math.max(max, nums[j]);
                ans += max - min;
            }
        }
        return ans;
    }

    public static long subArrayRangesPlus(int[] nums) {
        int left = 0, right = 0, n = nums.length;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            left = right = i;
            // 找到左右以 nums[i] 为最大值的最远左右点
            while (left - 1 >= 0 && nums[left - 1] < nums[i]) left--;
            while (right + 1 < n && nums[right + 1] <= nums[i]) right++;
            ans += (right - i + 1) * (i - left + 1) * (long) nums[i];   // 结果为最大值-最小值
            left = right = i;
            // 找到左右以 nums[i] 为最小值的最远左右点
            while (left - 1 >= 0 && nums[left - 1] > nums[i]) left--;
            while (right + 1 < n && nums[right + 1] >= nums[i]) right++;
            ans -= (right - i + 1) * (i - left + 1) * (long) nums[i];
        }
        return ans;
    }

    // 2 1 3
    public static long subArrayRangesStack(int[] nums) {
        long ans = 0;
        long[] mins = getCount(nums, true), maxs = getCount(nums, false);
        System.out.println(Arrays.toString(mins));
        System.out.println(Arrays.toString(maxs));
        for (int i = 0; i < nums.length; i++) ans += (maxs[i] - mins[i]) * nums[i];
        return ans;
    }

    public static long[] getCount(int[] nums, boolean isMin) {
        long[] left = new long[nums.length], right = new long[nums.length], ans = new long[nums.length];
        Deque<Integer> deque = new ArrayDeque<>();
        // isMin，左边找到比i小的最近的数
        for (int i = 0, n = nums.length; i < n; i++) {
            while (!deque.isEmpty() && (isMin ? nums[deque.peekLast()] >= nums[i] : nums[deque.peekLast()] <= nums[i]))
                deque.pollLast();
            left[i] = deque.isEmpty() ? -1 : deque.peekLast();
            deque.addLast(i);
        }
        deque.clear();
        for (int n = nums.length, i = n - 1; i >= 0; i--) {
            while (!deque.isEmpty() && (isMin ? nums[deque.peekLast()] > nums[i] : nums[deque.peekLast()] < nums[i]))
                deque.pollLast();
            right[i] = deque.isEmpty() ? n : deque.peekLast();
            deque.addLast(i);
        }
        for (int i = 0, n = nums.length; i < n; i++) ans[i] = (i - left[i]) * (right[i] - i);

        return ans;
    }
}
