package problems;

/**
 * 44. 通配符匹配
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 *
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 *
 * 说明:
 *
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 * 示例 1:
 *
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 *
 * 输入:
 * s = "aa"
 * p = "*"
 * 输出: true
 * 解释: '*' 可以匹配任意字符串。
 * 示例 3:
 *
 * 输入:
 * s = "cb"
 * p = "?a"
 * 输出: false
 * 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 * 示例 4:
 *
 * 输入:
 * s = "adceb"
 * p = "*a*b"
 * 输出: true
 * 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
 * 示例 5:
 *
 * 输入:
 * s = "acdcb"
 * p = "a*c?b"
 * 输出: false
 *
 * 链接：https://leetcode-cn.com/problems/wildcard-matching/
 */
public class Problem44 {

    public static void main(String[] args) {
        System.out.println(isMatch("aa", "a"));
        System.out.println(isMatch("aa", "*"));
        System.out.println(isMatch("cb", "?a"));
        System.out.println(isMatch("adceb", "*a*b"));
        System.out.println(isMatch("acdcb", "a*c?b"));
    }

    /*

        dp[i][j]
        1. p.charAt(j) != '*':
            if p.charAt(j) == s.charAt(i):  # s = "ab", p = "ab"
                dp[i][j] = dp[i - 1][j - 1]
            if p.charAt(j) == '?':  # s = "ab", p = "a?"
                dp[i][j] = dp[i - 1][j - 1]

        2. p.charAt(j) == '*':  # s = "abcde", p = "a*" or "*"
                dp[i][j] = dp[i - 1][j] or dp[i][j - 1]
                                |               |
                                v               v
                             一直往前匹配    匹配*前面的一个字符（*匹配0个字符）
     */
    public static boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;

        for (int i = 0; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (p.charAt(j - 1) != '*') {
                    if (i != 0 && (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '?'))
                        dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // s = "", p = "*";    s = "abc", p = "a*"
                    dp[i][j] = dp[i][j - 1]; // *匹配0个字符
                    if (i != 0) // 向前匹配字符
                        dp[i][j] = dp[i - 1][j] || dp[i][j];
                }
            }
        }

        return dp[s.length()][p.length()];
    }
}
