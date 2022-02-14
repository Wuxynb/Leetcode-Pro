package problems;

import java.util.*;

public class Problem40 {

    public static void main(String[] args) {
        System.out.println(combinationSum2(new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}, 30));
        System.out.println(combinationSum2(new int[]{10,1,2,7,6,1,5}, 8));
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(candidates, target, lists, new LinkedList<>(), 0);
        return lists;
    }

    /*
        输入: candidates =[10,1,2,7,6,1,5], target =8,
        输出:[[1,1,6], [1,2,5], [1,7], [2,6]]

     */
    public static void backtrack_(int[] candidates, int target, List<List<Integer>> lists, List<Integer> list, int idx) {
        if (target == 0) {
            Collections.sort(list);
            if (!lists.contains(list))
                lists.add(new ArrayList<>(list));
            return;
        }
        if (idx == candidates.length) return;
        // choose the idx-th elem
        if (candidates[idx] <= target) {
            list.add(candidates[idx]);
            backtrack_(candidates, target - candidates[idx], lists, list, idx + 1);
            list.remove(Integer.valueOf(candidates[idx]));
        }
        // don't choose the idx-th elem
        backtrack_(candidates, target, lists, list, idx + 1);
    }

    /*
       输入: candidates =[10,1,2,7,6,1,5], target =8,
       输出:[[1,1,6], [1,2,5], [1,7], [2,6]]

    */
    public static void backtrack(int[] candidates, int target, List<List<Integer>> lists, Deque<Integer> list, int idx) {
        if (target == 0) {
            lists.add(new ArrayList<>(list));
            return;
        }
        for (int i = idx; i < candidates.length; i++) {
            if (i == idx || candidates[i] != candidates[i - 1]) { // 去重
                if (candidates[i] <= target) {
                    list.add(candidates[i]);
                    backtrack(candidates, target - candidates[i], lists, list, i + 1);
                    list.removeLast();
                } else break;
            }
        }
    }
}
