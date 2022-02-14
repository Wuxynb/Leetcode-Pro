package offers;

public class Offer19 {
    public static void main(String[] args) {
        System.out.println(isMatch("abcc", "abc*"));
    }

    /*
        状态转移表：
        --------------------------
        | \| '' | a | b | c | c |
        --------------------------
        |' '| T | F | F | F | F |
        --------------------------
        | a | F | T | F | F | F |
        --------------------------
        | b | F | F | T | F | F |
        --------------------------
        | c | F | F | F | T | F |
        --------------------------
        | * | F | F | T | T | T |
        --------------------------
     */
    public static boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) { // 当i不为0而j为0时，都为false，（因为*不能单独出现），故从1开始
                if (p.charAt(j - 1) != '*') {
                    if (i != 0 && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.'))
                        dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 2]; // *匹配0个，*不能单独出现，故不需判断j >= 2；
                    if (i != 0 && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.'))
                        dp[i][j] = dp[i][j] || dp[i][j - 1] || dp[i - 1][j]; // 匹配零个，匹配一个，配个多个（即匹配当前字符i后丢弃当前字符i再往前匹配）
                }
            }
        }
        return dp[m][n];
    }
}
