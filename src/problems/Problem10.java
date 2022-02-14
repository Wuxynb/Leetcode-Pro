package problems;

import java.util.regex.Pattern;

/**
 * 10. 正则表达式匹配
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 *
 * 示例 1：
 *
 * 输入：s = "aa" p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 *
 * 输入：s = "aa" p = "a*"
 * 输出：true
 * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3：
 *
 * 输入：s = "ab" p = ".*"
 * 输出：true
 * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 *
 * 链接：https://leetcode-cn.com/problems/regular-expression-matching/
 */
public class Problem10 {

    public static void main(String[] args) {
        System.out.println(isMatch("aab", "c*a*b"));
        System.out.println(isMatch2("aab", "c*a*b"));
    }

    public static boolean isMatch_(String s, String p) {

        return Pattern.matches(p, s);
    }

    /*
        dp[0][0] = True

        if p.charAt(j) == s.charAt(i):  # s = "ab", p = "ab"
            dp[i][j] = dp[i - 1][j - 1]

        if p.charAt(j) == '.':  # s = "ab", p = "a."
            dp[i][j] = dp[i - 1][j - 1]

        if p.charAt(j) == '*':
            if p.charAt(j - 1) != s.charAt(i): # s = "ab", p = "abc*" 或者 s = "", p = "a*" , （c 出现0次）
                dp[i][j] = dp[i][j - 2]
            if p.charAt(j - 1) == s.charAt(i): # s = "abc", p = "abc*",  或者 s = "abcc", p = "abc*" 或者 s = "ac", p = "acc*"
                dp[i][j] = dp[i][j - 1] or dp[i - 1][j]  // 匹配一次 or 匹配多次（匹配完i后，舍弃i继续往前匹配）
            if p.charAt(j - 1) == '.': # s = "abc", p = "ab.*",  或者 s = "abcc", p = "ab.*" 或者 s = "ac", p = "ac.*"
                dp[i][j] = dp[i][j - 1] or dp[i - 1][j]

     */
    public static boolean isMatch2(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (p.charAt(j - 1) != '*') {
                    if (i != 0 && (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.')) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else {
                    if (i == 0 || (p.charAt(j - 2) != s.charAt(i - 1))) {// s = "", p = ".*"
                        dp[i][j] = dp[i][j - 2];
                    }
                    if (i != 0 && (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.')) {
                        dp[i][j] = dp[i][j - 1] || dp[i - 1][j] || dp[i][j - 2];
                    }
                }
            }
        }

        return dp[s.length()][p.length()];
    }

    /*
     * if (p[j] != '*')  :
     *          F[i][j] = F[i - 1][j - 1]  if s(i) = p(j)
     *          else F[i][j] = false
     *
     * otherwise :
     *          F[i][j] = F[i - 1][j] or F[i][j - 2]  if s(i) = p(j - 1)
     *          else F[i][j] = F[i][j - 2]
     */
    public static boolean isMatch(String s, String p) {
        boolean[][] F = new boolean[s.length() + 1][p.length() + 1];

        F[0][0] = true;
        for (int i = 0; i < F.length; i++) {
            for (int j = 1; j < F[0].length; j++) {
                if (p.charAt(j - 1) != '*') {
                    if (i != 0 && (p.charAt(j - 1) == '.' || s.charAt(i - 1) == p.charAt(j - 1))) {
                        F[i][j] = F[i - 1][j - 1];
                    }
                } else {
                    F[i][j] = F[i][j - 2];
                    if (i != 0 && (p.charAt(j - 2) == '.' || s.charAt(i - 1) == p.charAt(j - 2))) {
                        F[i][j] = F[i][j] || F[i - 1][j];
                    }
                }
            }
        }

        return F[F.length - 1][F[0].length - 1];
    }
}
