package problems;

import java.util.ArrayList;
import java.util.List;

/**
 *77. 组合
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 *
 * 你可以按 任何顺序 返回答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 4, k = 2
 * 输出：
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 * 示例 2：
 *
 * 输入：n = 1, k = 1
 * 输出：[[1]]
 *
 * 链接：https://leetcode-cn.com/problems/combinations/
 */
public class Problem77 {
    public static void main(String[] args) {
        System.out.println(combine(4, 3));
    }

    List<Integer> temp = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public List<List<Integer>> combine_(int n, int k) {
        dfs(1, n, k);
        return ans;
    }

    public void dfs(int cur, int n, int k) {
        // 剪枝：temp 长度加上区间 [cur, n] 的长度小于 k，不可能构造出长度为 k 的 temp
        if (temp.size() + (n - cur + 1) < k) {
            return;
        }
        // 记录合法的答案
        if (temp.size() == k) {
            ans.add(new ArrayList<Integer>(temp));
            return;
        }
        // 考虑选择当前位置
        temp.add(cur);
        dfs(cur + 1, n, k);
        temp.remove(temp.size() - 1);
        // 考虑不选择当前位置
        dfs(cur + 1, n, k);
    }

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> lists = new ArrayList<>();
        backtrack(lists, new ArrayList<>(), n, k, 0);
        return lists;
    }

    public static void backtrack(List<List<Integer>> lists, List<Integer> list, int n, int k, int idx) {
        // 剪枝：list 长度加上区间 [idx, n] 的长度小于 k，不可能构造出长度为 k 的 list
        if (list.size() + (n - idx) < k) return;
        if (list.size() == k) {
            lists.add(new ArrayList<>(list));
        } else {
            for (int i = idx + 1; i <= n; i++) {
                list.add(i);
                backtrack(lists, list, n, k, i);
                list.remove(Integer.valueOf(i));
            }
        }
    }
}
