package offers;

import java.util.*;

/**
 * 剑指 Offer 57 - II. 和为s的连续正数序列
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 *
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：target = 9
 * 输出：[[2,3,4],[4,5]]
 * 示例 2：
 *
 * 输入：target = 15
 * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
 *
 * 链接：https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof/
 */
public class Offer57_II {
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(findContinuousSequence(15)));
    }

    public static int[][] findContinuousSequenceDoublePointer(int target) {
        List<int[]> ans = new ArrayList<>();
        for (int l = 1, r = 2; l < r;) {
            int sum = (l + r) * (r - l + 1) / 2;
            if (sum == target) {
                int[] res = new int[r - l + 1];
                for (int i = l; i <= r; i++) {
                    res[i - l] = i;
                }
                ans.add(res);
                l++;
            } else if (sum < target) r++;
            else l++;
        }
        return ans.toArray(new int[ans.size()][]);
    }

    public static int[][] findContinuousSequence(int target) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 1; i < target; i++) {
            dfs(ans, new LinkedList<>(), i, target);
        }
        List<int[]> arrays = new ArrayList<>();
        for (List<Integer> an : ans) {
            int j = an.size();
            int[] array = new int[j];
            for (int k = 0; k < j; k++) {
                array[k] = an.get(k);
            }
            arrays.add(array);
        }
        return arrays.toArray(new int[arrays.size()][]);
    }

    public static void dfs(List<List<Integer>> lists, Deque<Integer> deque, int idx, int target) {
        if (target - idx == 0) {
            deque.add(idx);
            lists.add(new ArrayList<>(deque));
            return;
        }
        if (target - idx < 0 || target - idx < idx + 1) return;
        deque.add(idx);
        dfs(lists, deque, idx + 1, target - idx);
    }
}
