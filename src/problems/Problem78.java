package problems;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 78. 子集
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
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
 * nums 中的所有元素 互不相同
 *
 * 链接：https://leetcode-cn.com/problems/subsets/
 */
public class Problem78 {
    public static void main(String[] args) {
        System.out.println(subsets(new int[]{1, 2, 3}));
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        backtrack(lists, new LinkedList<>(), nums, 0);
        return lists;
    }

    public static void backtrack(List<List<Integer>> lists, Deque<Integer> list, int[] nums, int idx) {
        lists.add(new ArrayList<>(list));
        if (idx == nums.length) return;
        for (int i = idx; i < nums.length; i++) {
            list.add(nums[i]);
            backtrack(lists, list, nums, i + 1);
            list.removeLast();
        }
    }
}
