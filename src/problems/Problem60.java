package problems;

import java.util.LinkedList;
import java.util.List;

/**
 * 60. 排列序列
 * 给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列。
 * <p>
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 * <p>
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 3, k = 3
 * 输出："213"
 * 示例 2：
 * <p>
 * 输入：n = 4, k = 9
 * 输出："2314"
 * 示例 3：
 * <p>
 * 输入：n = 3, k = 1
 * 输出："123"
 * <p>
 * 链接：https://leetcode-cn.com/problems/permutation-sequence/
 */
public class Problem60 {
    static boolean find = false;

    public static void main(String[] args) {
        System.out.println(getPermutation(4, 9));
        System.out.println(getPermutation_(4, 9));
    }

    public static String getPermutation(int n, int k) {
        String[] ans = new String[1];
        backtrack(n, "", new boolean[n], 0, new int[1], k, ans);
        return ans[0];
    }

    public static void backtrack(int n, String str, boolean[] used, int t, int[] count, int k, String[] ans) {
        if (!find) {
            if (t == n) {
                count[0]++;
                if (count[0] == k) {
                    ans[0] = str;
                    find = true;
                }
            } else {
                for (int i = 1; i <= n; i++) {
                    if (!used[i - 1]) {
                        used[i - 1] = true;
                        backtrack(n, str + i, used, t + 1, count, k, ans);
                        used[i - 1] = false;
                    }
                }
            }
        }
    }

    public static String getPermutation_(int n, int k) {
        boolean[] used = new boolean[n];
        int[] factorial = new int[n + 1];
        calculateFactorial(factorial);
        StringBuilder sb = new StringBuilder();
        backtrack(n, used, k, 0, factorial, sb);
        return sb.toString();
    }

    public static void backtrack(int n, boolean[] used, int k, int idx, int[] factorial, StringBuilder sb) {
        if (idx == n) return;
        int count = factorial[n - 1 - idx]; // 计算还未确定的数字的全排列的个数，第 1 次进入的时候是 n - 1
        for (int i = 1; i <= n; i++) {
            if (used[i - 1]) continue;
            if (k > count) {
                k -= count;
                continue;
            }
            sb.append(i);
            used[i - 1] = true;
            backtrack(n, used, k, idx + 1, factorial, sb);
            // 注意 1：不可以回溯（重置变量），算法设计是「一下子来到叶子结点」，没有回头的过程
            // 注意 2：这里要加 return，后面的数没有必要遍历去尝试了
            return;
        }
    }

    public static void calculateFactorial(int[] factorial) {
        factorial[0] = 1;
        for (int i = 1; i < factorial.length; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
    }

    /*
     * 把候选数放在一个 有序列表 里，从左到右根据「剩下的数的阶乘数」确定每一位填谁，公式 k / (后面几位的阶乘数) 的值 恰好等于候选数组的下标；
     * 选出一个数以后，k 就需要减去相应跳过的阶乘数的倍数；
     * 已经填好的数需要从候选列表里删除，注意保持列表的有序性（因为排列的定义是按照字典序）；
     * 由于这里考虑的是下标，第 k 个数，下标为 k - 1，一开始的时候，k--。
     *
     * 每次选出一个数，就将这个数从列表里面拿出。这个列表需要支持频繁的删除操作，因此使用双链表。在 Java 中 LinkedList 就是使用双链表实现的。
     */
    public String getPermutationPlus(int n, int k) {
        // 注意：相当于在 n 个数字的全排列中找到下标为 k - 1 的那个数，因此 k 先减 1
        k--;

        int[] factorial = new int[n];
        factorial[0] = 1;
        // 先算出所有的阶乘值
        for (int i = 1; i < n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        // 这里使用数组或者链表都行
        List<Integer> nums = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }

        StringBuilder stringBuilder = new StringBuilder();

        // i 表示剩余的数字个数，初始化为 n - 1
        for (int i = n - 1; i >= 0; i--) {
            int index = k / factorial[i];
            stringBuilder.append(nums.remove(index));
            k -= index * factorial[i];
        }
        return stringBuilder.toString();
    }
}
