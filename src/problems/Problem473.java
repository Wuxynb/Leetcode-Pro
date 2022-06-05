package problems;

import java.util.Arrays;

/**
 * 473. 火柴拼正方形
 * 你将得到一个整数数组 matchsticks ，其中 matchsticks[i] 是第 i 个火柴棒的长度。你要用 所有的火柴棍 拼成一个正方形。你 不能折断 任何一根火柴棒，但你可以把它们连在一起，而且每根火柴棒必须 使用一次 。
 *
 * 如果你能使这个正方形，则返回 true ，否则返回 false 。
 *
 *
 *
 * 示例 1:
 *
 *
 *
 * 输入: matchsticks = [1,1,2,2,2]
 * 输出: true
 * 解释: 能拼成一个边长为2的正方形，每边两根火柴。
 * 示例 2:
 *
 * 输入: matchsticks = [3,3,3,3,4]
 * 输出: false
 * 解释: 不能用所有火柴拼成一个正方形。
 *
 *
 * 提示:
 *
 * 1 <= matchsticks.length <= 15
 * 1 <= matchsticks[i] <= 108
 *
 * link: https://leetcode.cn/problems/matchsticks-to-square/
 */
public class Problem473 {
    public static void main(String[] args) {
        System.out.println(makesquareBacktrackDp(new int[]{1, 1, 2, 2, 2}));
        System.out.println(makesquareBacktrackDp(new int[]{3, 3, 3, 3, 4}));
        System.out.println(makesquareBacktrackDp(new int[]{5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3}));
        System.out.println(makesquareBacktrackDp(new int[]{6, 6, 6, 6, 4, 2, 2}));
        System.out.println(makesquareBacktrackDp(new int[]{5, 5, 5, 5, 16, 4, 4, 4, 4, 4, 3, 3, 3, 3, 4}));
    }

    public static boolean makesquare(int[] matchsticks) {
        int sum = 0, avg, n = matchsticks.length;
        for (int stick : matchsticks) sum += stick;
        if (sum % 4 != 0) return false;
        avg = sum / 4;
        int[] dp = new int[1 << n];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 1; i < (1 << n); i++) {
            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 0) continue;
                int state = i ^ (1 << j);
                if (dp[state] >= 0 && (dp[state] + matchsticks[j]) <= avg) {
                    dp[i] = (dp[state] + matchsticks[j]) % avg;
                    break;
                }
            }
        }
        return dp[(1 << n) - 1] == 0;
    }

    public static boolean makesquareSlow(int[] matchsticks) {
        int sum = 0, avg, n = matchsticks.length;
        for (int stick : matchsticks) sum += stick;
        if (sum % 4 != 0) return false;
        avg = sum / 4;
        int[] dp = new int[1 << n];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 1; i < (1 << n); i++) {
            int curr = getSum(matchsticks, i);
            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 0) continue;
                int state = i ^ (1 << j);
                dp[i] = Math.max(dp[i], dp[state]);
                if (curr % avg == 0 && curr / avg == 1 + dp[state]) dp[i] = dp[state] + 1;
            }
        }
        return dp[(1 << n) - 1] == 4;
    }

    public static int getSum(int[] sticks, int state) {
        int sum = 0;
        for (int i = 0; i < sticks.length; i++) {
            if (((state >> i) & 1) == 1) sum += sticks[i];
        }
        return sum;
    }

    public static boolean makesquareBacktrack(int[] matchsticks) {
        int sum = 0, avg, n = matchsticks.length, l = 0, r = n - 1;
        for (int stick : matchsticks) sum += stick;
        if (sum % 4 != 0) return false;
        avg = sum / 4;
        Arrays.sort(matchsticks);
        while (l < r) {
            int temp = matchsticks[l];
            matchsticks[l++] = matchsticks[r];
            matchsticks[r--] = temp;
        }
        return backtrack(matchsticks, 0, avg, new int[4]);
    }

    public static boolean backtrack(int[] sticks, int idx, int avg, int[] edges) {
        if (idx == sticks.length) return true;
        for (int i = 0; i < edges.length; i++) {
            if (edges[i] + sticks[idx] > avg) continue;
            edges[i] += sticks[idx];
            if (backtrack(sticks, idx + 1, avg, edges)) return true;
            edges[i] -= sticks[idx];
        }
        return false;
    }

    public static boolean makesquareBacktrackDp(int[] matchsticks) {
        int sum = 0, avg, n = matchsticks.length;
        for (int stick : matchsticks) sum += stick;
        if (sum % 4 != 0) return false;
        avg = sum / 4;
        Arrays.sort(matchsticks); // 排序 13ms，不排序 14ms
        int[] dp = new int[1 << n];
        Arrays.fill(dp, -1);
        return backtrack(matchsticks, avg, 0, dp, 0) == 1;
//        return dp[(1 << n) - 1] == 1;
    }

    public static int backtrack(int[] sticks, int avg, int curr, int[] dp, int state) {
        if (dp[state] != -1) return dp[state];
        if (state  == (1 << sticks.length) - 1) return dp[state] = 1;
        for (int i = 0; i < sticks.length && curr + sticks[i] <= avg; i++) {
            if (((state >> i) & 1) == 1) continue;
            if (backtrack(sticks, avg, (curr + sticks[i]) % avg, dp, state | (1 << i)) == 1) {
                return dp[state | (1 << i)] = 1;
            }
        }
        return dp[state] = 0;
    }
}
