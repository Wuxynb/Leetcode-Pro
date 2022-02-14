package problems;

/**
 * 97. 交错字符串
 * 给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
 * <p>
 * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
 * <p>
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 * 提示：a + b 意味着字符串 a 和 b 连接。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出：false
 * 示例 3：
 * <p>
 * 输入：s1 = "", s2 = "", s3 = ""
 * 输出：true
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= s1.length, s2.length <= 100
 * 0 <= s3.length <= 200
 * s1、s2、和 s3 都由小写英文字母组成
 * <p>
 * 链接：https://leetcode-cn.com/problems/interleaving-string/
 */
public class Problem97 {
    public static void main(String[] args) {
        System.out.println(isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println(isInterleave_("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println(isInterleavePlus(
                "abababababababababababababababababababababababababababababababababababababababababababababababababbb",
                "babababababababababababababababababababababababababababababababababababababababababababababababaaaba",
                "ababababababababababababababababababababababababababababababababababababababababababababababababababa" +
                        "bababababababababababababababababababababababababababababababababababababababababababababababababbb"));
    }

    public static boolean isInterleavePlus(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        if (m + n != s3.length()) return false;
        else return backtrack(s1, s2, s3, new boolean[m + 1][n + 1], 0, 0, 0);
    }

    /**
     * 回溯法判断 s3 是否由 s1 和 s2 交错组合而成
     *
     * @param s1      字符串S1
     * @param s2      字符串S2
     * @param s3      字符串S3
     * @param visited 二维数组，boolean类型，记录当前 s1 的前 i 个字符和 s2 的前 j 个字符组成的 s3 的 k = i + j 个字符是否已经被使用
     * @param i       指向S1的指针
     * @param j       指向S2的指针
     * @param k       指向S3的指针，且 k = i + j
     * @return 如果S3是由S1和S2交错组合，返回true，否则返回false
     */
    public static boolean backtrack(String s1, String s2, String s3, boolean[][] visited, int i, int j, int k) {
        if (k == s3.length()) return true;
        if (visited[i][j]) return false;
        visited[i][j] = true;
        if (i < s1.length() && s1.charAt(i) == s3.charAt(k) && backtrack(s1, s2, s3, visited, i + 1, j, k + 1))
            return true;
        if (j < s2.length() && s2.charAt(j) == s3.charAt(k) && backtrack(s1, s2, s3, visited, i, j + 1, k + 1))
            return true;
        return false;
    }

    public static boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length(), t = s3.length();
        if (m + n != t) return false;
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        // 初始边界情况
        for (int i = 1; i <= m; i++) dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        for (int i = 1; i <= n; i++) dp[0][i] = dp[0][i - 1] && s2.charAt(i - 1) == s3.charAt(i - 1);

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) ||
                        (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }

        return dp[m][n];
    }

    public static boolean isInterleave_(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length(), t = s3.length();
        if (m + n != t) return false;
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i > 0) dp[i][j] = dp[i][j] || (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1));
                if (j > 0) dp[i][j] = dp[i][j] || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }

        return dp[m][n];
    }
}
