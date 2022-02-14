package problems;

import java.util.*;

/**
 * 90. 子集 II
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 * 示例 2：
 *
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 *
 * 链接：https://leetcode-cn.com/problems/subsets-ii/
 */
public class Problem90 {
    public static void main(String[] args) {
        System.out.println(subsetsWithDup(new int[]{1, 2, 2}));
    }

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(ans, new LinkedList<>(), nums, 0);

        return ans;
    }

    public static void backtrack(List<List<Integer>> lists, Deque<Integer> list, int[] nums, int idx) {
        lists.add(new ArrayList<>(list));
        if (idx == nums.length) return;
        for (int i = idx; i < nums.length; i++) {
            if (i == idx || nums[i - 1] != nums[i]) {
                list.add(nums[i]);
                backtrack(lists, list, nums, i + 1);
                list.removeLast();
            }
        }
    }
}
