package problems;

import java.util.Arrays;
import java.util.List;

/**
 * 120. 三角形最小路径和 （动态规划 + 记忆化搜索）
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 *
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * 也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出：11
 * 解释：如下面简图所示：
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * 示例 2：
 *
 * 输入：triangle = [[-10]]
 * 输出：-10
 *
 * 链接：https://leetcode-cn.com/problems/triangle/
 */
public class Problem120 {
    public static void main(String[] args) {
        System.out.println(minimumTotal(Arrays.asList(
                Arrays.asList(2),
                Arrays.asList(3, 4),
                Arrays.asList(6, 5, 7),
                Arrays.asList(4, 1, 8, 3))));

        System.out.println(minimumTotal(Arrays.asList(
                Arrays.asList(-10))));

        System.out.println(minimumTotalDp(Arrays.asList(
                Arrays.asList(2),
                Arrays.asList(3, 4),
                Arrays.asList(6, 5, 7),
                Arrays.asList(4, 1, 8, 3))));

        System.out.println(minimumTotalDp(Arrays.asList(
                Arrays.asList(-10))));

        System.out.println(minimumTotalDp2(Arrays.asList(
                Arrays.asList(2),
                Arrays.asList(3, 4),
                Arrays.asList(6, 5, 7),
                Arrays.asList(4, 1, 8, 3))));

        System.out.println(minimumTotalDp2(Arrays.asList(
                Arrays.asList(-10))));

        System.out.println(minimumTotalDPOpti(Arrays.asList(
                Arrays.asList(2),
                Arrays.asList(3, 4),
                Arrays.asList(6, 5, 7),
                Arrays.asList(4, 1, 8, 3))));

        System.out.println(minimumTotalDPOpti(Arrays.asList(
                Arrays.asList(-10))));
    }

    // 动态规划，自底向上
    public static int minimumTotalDp(List<List<Integer>> triangle) {
        int n = triangle.size(), ans = Integer.MAX_VALUE;
        int[][] dp = new int[n][n];
        dp[0][0] = triangle.get(0).get(0);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) dp[i][j] = dp[i - 1][0] + triangle.get(i).get(0);
                else if (j == i) dp[i][j] = dp[i - 1][j - 1] + triangle.get(i).get(j);
                else dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle.get(i).get(j);
            }
        }

        for (int i = 0; i < n; i++) ans = Math.min(dp[n - 1][i], ans);

        return ans;
    }

    public static int minimumTotalDp2(List<List<Integer>> triangle) {
        int n = triangle.size(), ans = Integer.MAX_VALUE;
        int[] dp = new int[n];
        dp[0] = triangle.get(0).get(0);

        for (int i = 1; i < n; i++) {
            for (int j = i; j >= 0; j--) {
                if (j == 0) dp[j] = dp[j] + triangle.get(i).get(j);
                else if (j == i) dp[j] = dp[j - 1] + triangle.get(i).get(j);
                else dp[j] = Math.min(dp[j - 1], dp[j]) + triangle.get(i).get(j);
            }
        }

        for (int i = 0; i < n; i++) ans = Math.min(dp[i], ans);

        return ans;
    }

    // 从三角形底下 往上走
    public static int minimumTotalDPOpti(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) { //从最后一行开始向第一行走  即从下到上
            for (int j = 0; j <= i; j++) { //从第一列向最后一列走， 从左到右
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j); // 先选择最小的元素 然后再加上要计算的元素
            }
        }

        return dp[0];
    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        return dfs(triangle, 0, 0, new Integer[triangle.size()][triangle.size()]);
    }

    public static int dfs(List<List<Integer>> triangle, int i, int j, Integer[][] cache) {
        if (i == triangle.size() - 1) return triangle.get(i).get(j);
        if (cache[i][j] != null) return cache[i][j];
        int ret = Math.min(dfs(triangle, i + 1, j, cache), dfs(triangle, i + 1, j + 1, cache)) + triangle.get(i).get(j);
        cache[i][j] = ret;
        return ret;
    }
}
