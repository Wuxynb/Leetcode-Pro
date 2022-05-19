package problems;

import java.util.ArrayList;
import java.util.List;

/**
 * 442. 数组中重复的数据
 * 给你一个长度为 n 的整数数组 nums ，其中 nums 的所有整数都在范围 [1, n] 内，且每个整数出现 一次 或 两次 。请你找出所有出现 两次 的整数，并以数组形式返回。
 *
 * 你必须设计并实现一个时间复杂度为 O(n) 且仅使用常量额外空间的算法解决此问题。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [4,3,2,7,8,2,3,1]
 * 输出：[2,3]
 * 示例 2：
 *
 * 输入：nums = [1,1,2]
 * 输出：[1]
 * 示例 3：
 *
 * 输入：nums = [1]
 * 输出：[]
 *
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= n <= 105
 * 1 <= nums[i] <= n
 * nums 中的每个元素出现 一次 或 两次
 *
 * link: https://leetcode-cn.com/problems/find-all-duplicates-in-an-array/
 */
public class Problem442 {
    public static void main(String[] args) {
        System.out.println(findDuplicates(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
        System.out.println(findDuplicates(new int[]{5, 4, 6, 7, 9, 3, 10, 9, 5, 6})); // 9, 5, 6
    }

    /**
     * 输入：nums = [4,3,2,7,8,2,3,1]
     * <p>
     * 输出：[2,3]
     */
    public static List<Integer> findDuplicates2(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0, n = nums.length; i < n; i++) {
            while (nums[i] != nums[nums[i] - 1]) {
                int num = nums[i];
                nums[i] = nums[num - 1];
                nums[num - 1] = num;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] - 1 != i) list.add(nums[i]);
        }
        return list;
    }
    
    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int t = Math.abs(nums[i]) - 1;
            if (nums[t] < 0) list.add(t + 1);
            nums[t] = -nums[t];
        }
        return list;
    }
}
