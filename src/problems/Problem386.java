package problems;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 386. 字典序排数
 * 给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数。
 *
 * 你必须设计一个时间复杂度为 O(n) 且使用 O(1) 额外空间的算法。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 13
 * 输出：[1,10,11,12,13,2,3,4,5,6,7,8,9]
 * 示例 2：
 *
 * 输入：n = 2
 * 输出：[1,2]
 *
 *
 * 提示：
 *
 * 1 <= n <= 5 * 104
 *
 * link: https://leetcode-cn.com/problems/lexicographical-numbers/
 */
public class Problem386 {
    public static void main(String[] args) {
        System.out.println(lexicalOrder(13));

        System.out.println(lexicalOrderDfs(13));

        System.out.println(lexicalOrderNlogn(13));
    }

    public static List<Integer> lexicalOrderDfs(int n) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            dfs(ans, i, n);
        }
        return ans;
    }

    public static void dfs(List<Integer> ans, int start, int n) {
        if (start > n) return;
        ans.add(start);
        for (int i = 0; i < 10 && start * 10 + i <= n; i++) {
            dfs(ans, start * 10 + i, n);
        }
    }

    public static List<Integer> lexicalOrder(int n) { // 迭代实现
        List<Integer> ans = new ArrayList<>();
        int number = 1;
        for (int i = 0; i < n; i++) {
            ans.add(number);
            if (number * 10 <= n) {
                number *= 10;
            } else {
                while (number % 10 == 9 || number >= n)
                    number /= 10;
                number++;
            }
        }
        return ans;
    }

    public static List<Integer> lexicalOrderNlogn(int n) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= n; i++) ans.add(i);
        ans.sort(Comparator.comparing(String::valueOf));
        return ans;
    }
}
