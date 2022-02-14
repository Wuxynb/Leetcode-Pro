package problems;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 131. 分割回文串
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 * <p>
 * 回文串 是正着读和反着读都一样的字符串。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * 示例 2：
 * <p>
 * 输入：s = "a"
 * 输出：[["a"]]
 * <p>
 * 链接：https://leetcode-cn.com/problems/palindrome-partitioning/
 */
public class Problem131 {
    public static void main(String[] args) {
        System.out.println(partition("a"));
        System.out.println(partition("aab"));
        System.out.println(partition("ababababababababababababcbabababababababababababa"));
    }

    public static List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        boolean[][] dp = new boolean[s.length()][s.length()];
        char[] chars = s.toCharArray();
        // 状态转移方程：在 s[i] == s[j] 的时候，dp[i][j] 参考 dp[i + 1][j - 1]
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                if (chars[j] == chars[i] && (i - j <= 2 || dp[j + 1][i - 1])) dp[j][i] = true;
            }
        }
        backtrack(ans, new LinkedList<>(), s, 0, dp);
        return ans;
    }

    public static void backtrack(List<List<String>> lists, Deque<String> list, String s, int idx, boolean[][] dp) {
        if (idx == s.length()) {
            lists.add(new ArrayList<>(list));
            return;
        }
        for (int i = idx; i < s.length(); i++) {
            if (dp[idx][i]) {
                list.add(s.substring(idx, i + 1));
                backtrack(lists, list, s, i + 1, dp);
                list.removeLast();
            }
        }
    }

    public static int isPalindrome(String s, int i, int j, int[][] f) { // 记忆化搜索
        char[] chars = s.toCharArray();
        if (f[i][j] != 0) return f[i][j];
        if (i >= j) f[i][j] = 1;
        else if (chars[i] == chars[j]) return isPalindrome(s, i + 1, j - 1, f);
        else f[i][j] = -1;
        return f[i][j];
    }
}
