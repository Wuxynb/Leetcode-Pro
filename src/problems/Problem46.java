package problems;

import java.util.ArrayList;
import java.util.List;

/**
 *46. 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 示例 2：
 *
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * 示例 3：
 *
 * 输入：nums = [1]
 * 输出：[[1]]
 *
 * 链接：https://leetcode-cn.com/problems/permutations/
 */
public class Problem46 {

    public static void main(String[] args) {
        System.out.println(permute(new int[]{1, 2, 3}));
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        backtrack(lists, nums, new ArrayList<>(), 0);
        return lists;
    }

    public static void backtrack(List<List<Integer>> lists, int[] nums, List<Integer> list, int t) {
        if (t == nums.length) lists.add(new ArrayList<>(list));
        else {
            for (int i = 0; i < nums.length; i++) {
                if (!list.contains(nums[i])) {
                    list.add(nums[i]);  // used[i] = true;
                    backtrack(lists, nums, list, t + 1);
                    list.remove(Integer.valueOf(nums[i]));  // used[i] = false;
                }
            }
        }
    }
}
