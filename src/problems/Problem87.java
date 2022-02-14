package problems;

/**
 *87. 扰乱字符串 （拼多多）
 * 使用下面描述的算法可以扰乱字符串 s 得到字符串 t ：
 * 如果字符串的长度为 1 ，算法停止
 * 如果字符串的长度 > 1 ，执行下述步骤：
 * 在一个随机下标处将字符串分割成两个非空的子字符串。即，如果已知字符串 s ，则可以将其分成两个子字符串 x 和 y ，且满足 s = x + y 。
 * 随机 决定是要「交换两个子字符串」还是要「保持这两个子字符串的顺序不变」。即，在执行这一步骤之后，s 可能是 s = x + y 或者 s = y + x 。
 * 在 x 和 y 这两个子字符串上继续从步骤 1 开始递归执行此算法。
 * 给你两个 长度相等 的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。如果是，返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s1 = "great", s2 = "rgeat"
 * 输出：true
 * 解释：s1 上可能发生的一种情形是：
 * "great" --> "gr/eat" // 在一个随机下标处分割得到两个子字符串
 * "gr/eat" --> "gr/eat" // 随机决定：「保持这两个子字符串的顺序不变」
 * "gr/eat" --> "g/r / e/at" // 在子字符串上递归执行此算法。两个子字符串分别在随机下标处进行一轮分割
 * "g/r / e/at" --> "r/g / e/at" // 随机决定：第一组「交换两个子字符串」，第二组「保持这两个子字符串的顺序不变」
 * "r/g / e/at" --> "r/g / e/ a/t" // 继续递归执行此算法，将 "at" 分割得到 "a/t"
 * "r/g / e/ a/t" --> "r/g / e/ a/t" // 随机决定：「保持这两个子字符串的顺序不变」
 * 算法终止，结果字符串和 s2 相同，都是 "rgeat"
 * 这是一种能够扰乱 s1 得到 s2 的情形，可以认为 s2 是 s1 的扰乱字符串，返回 true
 * 示例 2：
 *
 * 输入：s1 = "abcde", s2 = "caebd"
 * 输出：false
 * 示例 3：
 *
 * 输入：s1 = "a", s2 = "a"
 * 输出：true
 *
 *
 * 提示：
 *
 * s1.length == s2.length
 * 1 <= s1.length <= 30
 * s1 和 s2 由小写英文字母组成
 *
 * 链接：https://leetcode-cn.com/problems/scramble-string/
 */
public class Problem87 {
    public static void main(String[] args) {
        System.out.println(isScramble("abcde", "caebd"));
    }

    /*

                    S： ——————————      T：——————————

        情况一：       ————   ——————      ————   ——————
                       S1      S2         T1      T2
                    即：S1 --> T1, S2 --> T2

        情况二：       ——————   ————      ————   ——————
                       S1      S2         T1      T2
                    即：S1 --> T2, S2 --> T1

        ----------------------------------------------------------------------------------------

        转移方程
            dp[i][j][k]==
                OR 1<=w<=k-1 {dp[i][j][w] && dp[i+w][j+w][k-w]}
                OR 1<=w<=k-1 {dp[i][j+k-w][w] && dp[i+w][j][k-w]}
        注：枚举S1长度w（从1~k-1，因为要划分），f[i][j][w] 表示S1能变成T1，f[i+w][j+w][k-w]表示S2能变成T2；
                                         f[i][j+k-w][w] 表示S1能变成T2，f[i+w][j][k-w]表示S2能变成T1。

        初始条件
            对于长度是 1 的子串，只有相等才能变过去，相等为 true，不相等为 false。

     */
    public static boolean isScramble(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        char[] c1 = s1.toCharArray(), c2 = s2.toCharArray();
        if (m != n) return false;
        boolean[][][] dp = new boolean[n][n][n + 1];

        // 初始化单个字符的情况
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][1] = c1[i] == c2[j];
            }
        }

        // 枚举区间长度 2 ~ n
        for (int len = 2; len <= n; len++) {
            // 枚举S的起点
            for (int i = 0; i <= n - len; i++) {
                // 枚举T的起点
                for (int j = 0; j <= n - len; j++) {
                    // 枚举 len 的 划分位置
                    for (int k = 1; k < len; k++) {
                        // 第一种情况：S1 --> T1, S2 --> T2
                        if (dp[i][j][k] && dp[i + k][j + k][len - k]) {
                            dp[i][j][len] = true;
                            break;
                        }
                        // 第二种情况：S1 --> T2, S2 --> T1
                        // S1 起点为 i，T2 起点为 j + 前面那段长度 (len - k)
                        // S2 起点为 i + 前面长度 k， T2 起点则为 j
                        if (dp[i][j + len - k][k] && dp[i + k][j][len - k]) {
                            dp[i][j][len] = true;
                            break;
                        }
                    }
                }
            }
        }

        return dp[0][0][n];
    }
}
