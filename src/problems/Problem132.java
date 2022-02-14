package problems;

/**
 * 132. 分割回文串 II
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。
 *
 * 返回符合要求的 最少分割次数 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "aab"
 * 输出：1
 * 解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
 * 示例 2：
 *
 * 输入：s = "a"
 * 输出：0
 * 示例 3：
 *
 * 输入：s = "ab"
 * 输出：1
 *
 * 链接：https://leetcode-cn.com/problems/palindrome-partitioning-ii/
 */
public class Problem132 {
    public static void main(String[] args) {
        System.out.println(minCut("aab"));
        System.out.println(minCut("ababababababababababababcbabababababababababababa"));
    }

    /*
        预处理 g[l][r] 的过程可以用递推去做。
        要想 g[l][r] = true ，必须满足以下两个条件：
            g[l + 1][r - 1] = true
            s[l] = s[r]
        由于状态 g[l][r] 依赖于状态 g[l + 1][r - 1]，因此需要我们左端点 l 是「从大到小」进行遍历；而右端点 r 是「从小到大」进行遍历。
     */
    public static int minCut(String s) {
        boolean[][] dp = new boolean[s.length() + 1][s.length() + 1];
        char[] chars = s.toCharArray();
        // dp[left][right]
        for (int right = 1; right <= s.length(); right++) {
            for (int left = right; left >= 1; left--) {
                if (left == right) dp[left][right] = true; // 如果只有一个字符，则[l,r]属于回文
                else {
                    if (chars[left - 1] == chars[right - 1]) { // 在 l 和 r 字符相同的前提下
                        // 如果 l 和 r 长度只有 2；或者 [l+1,r-1] 这一段满足回文，则[l,r]属于回文
                        dp[left][right] = right - left == 1 || dp[left + 1][right - 1];
                    }
                }
            }
        }
        int[] f = new int[s.length() + 1];
        for (int right = 1; right <= s.length(); right++) {
            if (dp[1][right]) f[right] = 0;
            else {
                f[right] = right - 1; // 先设定一个最大分割次数（r 个字符最多消耗 r - 1 次分割）
                for (int left = 1; left <= right; left++) { // 在所有符合 [l,r] 回文的方案中取最小值
                    if (dp[left][right]) f[right] = Math.min(f[right], f[left - 1] + 1);
                }
            }
        }

        return f[s.length()];
    }
}
