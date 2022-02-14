package problems;

import java.util.Arrays;

/**
 * 1220. 统计元音字母序列的数目
 * 给你一个整数 n，请你帮忙统计一下我们可以按下述规则形成多少个长度为 n 的字符串：
 * <p>
 * 字符串中的每个字符都应当是小写元音字母（'a', 'e', 'i', 'o', 'u'）
 * 每个元音 'a' 后面都只能跟着 'e'
 * 每个元音 'e' 后面只能跟着 'a' 或者是 'i'
 * 每个元音 'i' 后面 不能 再跟着另一个 'i'
 * 每个元音 'o' 后面只能跟着 'i' 或者是 'u'
 * 每个元音 'u' 后面只能跟着 'a'
 * 由于答案可能会很大，所以请你返回 模 10^9 + 7 之后的结果。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 1
 * 输出：5
 * 解释：所有可能的字符串分别是："a", "e", "i" , "o" 和 "u"。
 * 示例 2：
 * <p>
 * 输入：n = 2
 * 输出：10
 * 解释：所有可能的字符串分别是："ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" 和 "ua"。
 * 示例 3：
 * <p>
 * 输入：n = 5
 * 输出：68
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 2 * 10^4
 * <p>
 * 链接：https://leetcode-cn.com/problems/count-vowels-permutation/
 */
public class Problem1220 {
    public static void main(String[] args) {
        System.out.println(countVowelPermutation(2));
        System.out.println(countVowelPermutation(5));
        System.out.println(countVowelPermutation(20000));

        System.out.println(countVowelPermutationOptimize(2));
        System.out.println(countVowelPermutationOptimize(5));
        System.out.println(countVowelPermutationOptimize(20000));

        System.out.println(countVowelPermutationMatrix(2));
        System.out.println(countVowelPermutationMatrix(5));
        System.out.println(countVowelPermutationMatrix(20000));
    }

    public static int countVowelPermutationMatrix(int n) {
        long[][] matrix = new long[][]{
                {0, 1, 0, 0, 0},
                {1, 0, 1, 0, 0},
                {1, 1, 0, 1, 1},
                {0, 0, 1, 0, 1},
                {1, 0, 0, 0, 0}
        };
        long[][] ans = new long[][]{{1}, {1}, {1}, {1}, {1}};
        int x = n - 1;
        while (x != 0) {
            if ((x & 1) == 1) ans = multiply(matrix, ans);
            matrix = multiply(matrix, matrix);
            x >>= 1;
        }
        long sum = 0;
        for (int i = 0; i < 5; i++) sum += ans[i][0];
        return (int) (sum % 1000000007);
    }

    public static long[][] multiply(long[][] a, long[][] b) {
        int m = a.length, n = b[0].length;
        long[][] c = new long[m][n];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < a[0].length; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                    c[i][j] %= 1000000007;
                }
            }
        }
        return c;
    }

    public static int countVowelPermutationOptimize(int n) {
        int base = 1000000007, ans = 0;
        long[][] dp = new long[2][5];
        Arrays.fill(dp[0], 1);
        for (int i = 1; i < n; i++) {
            dp[1][0] = (dp[0][1] + dp[0][2] + dp[0][4]) % base;
            dp[1][1] = (dp[0][0] + dp[0][2]) % base;
            dp[1][2] = (dp[0][1] + dp[0][3]) % base;
            dp[1][3] = (dp[0][2]) % base;
            dp[1][4] = (dp[0][2] + dp[0][3]) % base;
            System.arraycopy(dp[1], 0, dp[0], 0, 5);
        }
        for (long num : dp[0]) ans = (int) ((ans + num) % base);
        return ans;
    }

    public static int countVowelPermutation(int n) {
        int base = 1000000007, ans = 0;
        long[][] dp = new long[n][5];
        Arrays.fill(dp[0], 1);
        for (int i = 1; i < n; i++) {
            dp[i][0] = (dp[i - 1][1] + dp[i - 1][2] + dp[i - 1][4]) % base;
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % base;
            dp[i][2] = (dp[i - 1][1] + dp[i - 1][3]) % base;
            dp[i][3] = (dp[i - 1][2]) % base;
            dp[i][4] = (dp[i - 1][2] + dp[i - 1][3]) % base;
        }
        for (long num : dp[n - 1]) ans = (int) ((ans + num) % base);
        return ans;
    }
}
