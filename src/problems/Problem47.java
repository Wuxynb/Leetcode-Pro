package problems;

import java.util.*;

/**
 * 47. 全排列 II
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 * 示例 2：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * 链接：https://leetcode-cn.com/problems/permutations-ii/
 */
public class Problem47 {

    public static void main(String[] args) {
        System.out.println(permuteUnique(new int[]{1, 2, 1}));
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(lists, nums, new LinkedList<>(), new int[nums.length]);

        return lists;
    }

    /*
     *  if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1])) {
            continue;
     *  }
        加上 !vis[i - 1]来去重主要是通过限制一下两个相邻的重复数字的访问顺序

     *     举个栗子，对于两个相同的数11，我们将其命名为1a1b, 1a表示第一个1，1b表示第二个1； 那么，不做去重的话，会有两种重复排列 1a1b, 1b1a，
     *  我们只需要取其中任意一种排列； 为了达到这个目的，限制一下1a, 1b访问顺序即可。 比如我们只取1a1b那个排列的话，只有当visit nums[i-1]
     *  之后我们才去visit nums[i]， 也就是如果!visited[i-1]的话则continue
     *
     */
    public static void backtrack(List<List<Integer>> lists, int[] nums, Deque<Integer> list, int[] used) {
        if (list.size() == nums.length) {
            lists.add(new ArrayList<>(list));
        }
        else {
            for (int i = 0; i < nums.length; i++) {
                if (used[i] == 0) {
                    // 如果这个数和之前的数一样，并且之前的数还未使用过（说明已经回溯过nums[i - 1]了） used[i - 1] != 0也行
                    if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == 0) continue;
                    list.add(nums[i]);
                    used[i] = 1;
                    backtrack(lists, nums, list, used);
                    used[i] = 0;
                    list.removeLast();
                }
            }
        }
    }
}
