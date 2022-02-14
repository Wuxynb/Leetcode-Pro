package problems;

/**
 * 115. 不同的子序列 （动态规划 + 记忆化搜索）
 * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
 *
 * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
 *
 * 题目数据保证答案符合 32 位带符号整数范围。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "rabbbit", t = "rabbit"
 * 输出：3
 * 解释：
 * 如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
 * rabbbit
 * rabbbit
 * rabbbit
 * 示例 2：
 *
 * 输入：s = "babgbag", t = "bag"
 * 输出：5
 * 解释：
 * 如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。
 * babgbag
 * babgbag
 * babgbag
 * babgbag
 * babgbag
 *
 * 链接：https://leetcode-cn.com/problems/distinct-subsequences/
 */
public class Problem115 {
    public static void main(String[] args) {
        System.out.println(numDistinct("taaa", "taa"));
        System.out.println(numDistinctOpti("taaa", "taa"));
        System.out.println(numDistinctOptimize("taaa", "taa"));

        System.out.println(numDistinctDfs("rabbbit", "rabbit"));
    }

    /*
        s(i) == t(j):
            dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];// 不跳过这个字符 + 跳过这个字符
        s(i) != t(j):
            dp[i][j] = dp[i - 1][j];// 跳过这个字符
     */
    public static int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        if (m < n) return 0;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) dp[i][0] = 1;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[m][n];
    }

    public static int numDistinctOpti(String s, String t) {
        char[] ss = s.toCharArray(), tt = t.toCharArray();
        if (ss.length < tt.length) return 0;
        int[][] dp = new int[ss.length + 1][tt.length + 1];
        for (int i = 0; i <= ss.length; i++) dp[i][0] = 1;
        for (int i = 1; i <= ss.length; i++) {
            for (int j = 1; j <= tt.length; j++) {
                dp[i][j] = ss[i - 1] == tt[j - 1] ? dp[i - 1][j - 1] + dp[i - 1][j] : dp[i - 1][j];
            }
        }
        return dp[ss.length][tt.length];
    }

    public static int numDistinctOptimize(String s, String t) {
        int m = s.length(), n = t.length();
        int[] dp = new int[n + 1];
        for (int i = 1; i <= m; i++) {
            int pre = 1;
            for (int j = 1; j <= n; j++) {
                int cur = dp[j];
                if (s.charAt(i - 1) == t.charAt(j - 1)) dp[j] += pre;
                pre = cur;
            }
        }
        return dp[n];
    }

    public static int numDistinctDfs(String s, String t) {
        if (s.length() < t.length()) return 0;
        return dfs(s.toCharArray(), t.toCharArray(), new Integer[s.length()][t.length()], s.length() - 1, t.length() - 1);
    }

    public static int dfs(char[] ss, char[] tt, Integer[][] cache, int i, int j) {
        if (j < 0) return 1;
        if (i < j) return 0;
        if (cache[i][j] != null) return cache[i][j];
        int ans = 0;
        if (ss[i] == tt[j]) ans += dfs(ss, tt, cache, i - 1, j - 1);
        ans += dfs(ss, tt, cache, i - 1, j);
        cache[i][j] = ans;
        return ans;
    }
}
