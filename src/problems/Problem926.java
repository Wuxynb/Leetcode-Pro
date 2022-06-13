package problems;

public class Problem926 {
    public static void main(String[] args) {

    }

    /**
     * 示例 1：
     *
     * 输入：s = "00110"
     * 输出：1
     * 解释：翻转最后一位得到 00111.
     * 示例 2：
     *
     * 输入：s = "010110"
     * 输出：2
     * 解释：翻转得到 011111，或者是 000111。
     * 示例 3：
     *
     * 输入：s = "00011000"
     * 输出：2
     * 解释：翻转得到 00000000。
     */
    public int minFlipsMonoIncr(String s) {
        int n = s.length();
        int[][] dp = new int[n][2]; // 长度为i，最后一位为j的翻转次数
        char[] c = s.toCharArray();
        dp[0][0] = c[0] == '0' ? 0 : 1;
        dp[0][1] = c[0] == '1' ? 0 : 1;
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + c[i] - '0'; // 当前最后一位为0则上一位肯定只能为0
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + '1' - c[i];
        }
        return Math.min(dp[n - 1][0], dp[n - 1][1]);
    }

    public int minFlipsMonoIncr_(String s) {
        int x0 = 0, x1 = 0; // x0表示当前长度字符结尾为0需要的最小翻转次数，x1则是结尾为1
        for (char c : s.toCharArray()) {
            x1 = Math.min(x0, x1) + '1' - c; // 因为要用到上一轮的x0，所以先更新x1
            x0 += c - '0';
        }
        return Math.min(x0, x1);
    }
}
