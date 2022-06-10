package problems;

import java.util.Arrays;

/**
 * 730. 统计不同回文子序列
 * 给定一个字符串 s，返回 s 中不同的非空「回文子序列」个数 。
 * <p>
 * 通过从 s 中删除 0 个或多个字符来获得子序列。
 * <p>
 * 如果一个字符序列与它反转后的字符序列一致，那么它是「回文字符序列」。
 * <p>
 * 如果有某个 i , 满足 ai != bi ，则两个序列 a1, a2, ... 和 b1, b2, ... 不同。
 * <p>
 * 注意：
 * <p>
 * 结果可能很大，你需要对 109 + 7 取模 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = 'bccb'
 * 输出：6
 * 解释：6 个不同的非空回文子字符序列分别为：'b', 'c', 'bb', 'cc', 'bcb', 'bccb'。
 * 注意：'bcb' 虽然出现两次但仅计数一次。
 * 示例 2：
 * <p>
 * 输入：s = 'abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba'
 * 输出：104860361
 * 解释：共有 3104860382 个不同的非空回文子序列，104860361 对 109 + 7 取模后的值。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 1000
 * s[i] 仅包含 'a', 'b', 'c' 或 'd'
 * <p>
 * link: https://leetcode.cn/problems/count-different-palindromic-subsequences/
 */
public class Problem730 {
    public static void main(String[] args) {

    }

    int mod = (int) 1e9 + 7;
    // https://leetcode.cn/problems/count-different-palindromic-subsequences/solution/tong-ji-butong-by-jiang-hui-4-q5xf/
    public int countPalindromicSubsequences(String s) {
        char[] c = s.toCharArray();
        int n = c.length;
        long[][] dp = new long[n][n]; // dp[i][j]表示字符串从i到j的回文序列个数
        for (int i = 0; i < n; i++) dp[i][i] = 1; // 一个单字符是一个回文子序列
        for (int d = 1; d < n; d++) { // 从长度为2的子串开始计算
            for (int i = 0; i < n - d; i++) {
                if (c[i] == c[i + d]) { // 情况(1) 相等
                    int l = i + 1, r = i + d - 1;
                    while (c[l] != c[i]) l++; // 找到第一个和s[i]相同的字符
                    while (c[r] != c[i]) r--; // 找到第一个和s[j]相同的字符
                    if (l > r) dp[i][i + d] = dp[i + 1][i + d - 1] * 2 + 2; // 情况① 没有重复字符
                    else if (l == r) dp[i][i + d] = dp[i + 1][i + d - 1] * 2 + 1; // 情况② 出现一个重复字符
                    else dp[i][i + d] = dp[i + 1][i + d - 1] * 2 - dp[l + 1][r - 1] + mod; // 情况③ 有两个及两个以上
                } else {  // 情况(2) 不相等
                    dp[i][i + d] = dp[i][i + d - 1] + dp[i + 1][i + d] - dp[i + 1][i + d - 1] + mod;
                }
                dp[i][i + d] %= mod; // 处理超范围结果
            }
        }
        return (int) dp[0][n - 1];
    }
}
