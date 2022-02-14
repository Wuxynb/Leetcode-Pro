package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 39. 组合总和
 *
 * 给定一个无重复元素的正整数数组 candidates 和一个正整数 target ，找出 candidates 中所有可以使数字和为目标数 target 的唯一组合。
 * <p>
 * candidates 中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是唯一的。
 *
 * 对于给定的输入，保证和为 target 的唯一组合数少于 150 个。
 * <p>
 * 示例 1：
 * <p>
 * 输入: candidates = [2,3,6,7], target = 7
 * 输出: [[7],[2,2,3]]
 * 示例 2：
 * <p>
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 * 示例 3：
 * <p>
 * 输入: candidates = [2], target = 1
 * 输出: []
 * 示例 4：
 * <p>
 * 输入: candidates = [1], target = 1
 * 输出: [[1]]
 * 示例 5：
 * <p>
 * 输入: candidates = [1], target = 2
 * 输出: [[1,1]]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Problem39 {

    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{2, 3, 5}, 8));
//        System.out.println(combinationSumDP(new int[]{2, 3, 5}, 8));
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(candidates);

//        backtrack3(candidates, target, lists, new ArrayList<>(), 0, 0);
        backtrack(candidates, target, lists, new ArrayList<>(), 0);
        return lists;
    }

    /*
     * 输入: candidates = [2,3,5], target = 8
     * 输出: [[2,2,2,2],[2,3,3],[3,5]]
     */
    public static void backtrack(int[] candidates, int target, List<List<Integer>> lists, List<Integer> list, int idx) {
        if (idx == candidates.length || target < 0) return;
        if (target == 0) {
            lists.add(new ArrayList<>(list));
            return;
        }

        int num = candidates[idx];
        if (num <= target) {
            list.add(num);
            backtrack(candidates, target - num, lists, list, idx);
            list.remove(Integer.valueOf(num));
        }
        backtrack(candidates, target, lists, list, idx + 1);
    }

    public static void backtrack2(int[] candidates, int target, List<List<Integer>> lists, List<Integer> list, int idx) {
        if (idx == candidates.length || target < 0) return;
        if (target == 0) {
            lists.add(new ArrayList<>(list));
            return;
        }

        // 重点理解这里从 idx 开始搜索的语意
        for (int i = idx; i < candidates.length; i++) {
            int num = candidates[i];
            if (num <= target) {
                list.add(num);
                // 注意：由于每一个元素可以重复使用，下一轮搜索的起点依然是 i，这里非常容易弄错
                backtrack(candidates, target - num, lists, list, i);
                list.remove(Integer.valueOf(num));
            } else break;
        }
    }

    public static void backtrack3(int[] candidates, int target, List<List<Integer>> lists, List<Integer> list, int curr, int begin) {
        if (curr == target) {
            lists.add(new ArrayList<>(list));
            return;
        }
        // 从begin开始，从而去除重复（避免begin之前的数字再次被选取，导致结果重复）
        for (int i = begin; i < candidates.length; i++) {
            int num = candidates[i];
            if (curr + num <= target) { // 剪枝操作
                list.add(num);
                // begin = i，由于一个元素可以使用多次
                backtrack3(candidates, target, lists, list, curr + num, i);
                list.remove(Integer.valueOf(num));
            } else break;
        }
    }

    // dp[i] = min{dp[i - vj] + 1},  0<=j<=coins.length - 1
    public static List<List<Integer>> combinationSumDP(int[] candidates, int target) {
        List<List<Integer>>[] dp = new ArrayList[target + 1];
        Arrays.sort(candidates);
        for (int i = 0; i < dp.length; i++) {
            dp[i] = new ArrayList<>();
        }

        for (int i = 0; i <= target; i++) {
            for (int j = 0; j < candidates.length; j++) {
                if (candidates[j] == i)
                    dp[i].add(new ArrayList<>(Collections.singletonList(candidates[j])));
                else if (candidates[j] < i) {
                    List<List<Integer>> pre = dp[i - candidates[j]];
                    for (List<Integer> lists : pre) {
                        List<Integer> l = new ArrayList<>();
                        l.add(candidates[j]);
                        l.addAll(lists);
                        Collections.sort(l);
                        if (!dp[i].contains(l))
                            dp[i].add(l);
                    }
                } else break;
            }
        }
        return dp[target];
    }
}
