package bbc.y2020a;

/**
 * 题目描述
 * 小蓝要用七段码数码管来表示一种特殊的文字。
 * <p>
 * 上图给出了七段码数码管的一个图示，数码管中一共有7 段可以发光的二极管，分别标记为a, b, c, d, e, f, g。
 * 小蓝要选择一部分二极管（至少要有一个）发光来表达字符。在设计字符的表达时，要求所有发光的二极管是连成一片的。
 * 例如：b 发光，其他二极管不发光可以用来表达一种字符。
 * 例如：c 发光，其他二极管不发光可以用来表达一种字符。这种方案与上一行的方案可以用来表示不同的字符，尽管看上去比较相似。
 * 例如：a, b, c, d, e 发光，f, g 不发光可以用来表达一种字符。
 * 例如：b, f 发光，其他二极管不发光则不能用来表达一种字符，因为发光的二极管没有连成一片。
 * 请问，小蓝可以用七段码数码管表达多少种不同的字符？
 * </p>
 * <p>
 * 输出格式
 * 这是一道结果填空的题，你只需要算出结果后提交即可。本题的结果为一个整数，在提交答案时只输出这个整数，输出多余的内容将无法得分。
 * </p>
 * 状态DP 解法
 */
public class SevenSegmentCodeDP {
    static int[][] G;
    static int n, m;

    public static void main(String[] args) {
        n = 7;
        m = (1 << n);
        G = new int[n][n];

        G[0][1] = G[0][5] = G[1][0] = G[1][2] = G[1][6] = 1;
        G[2][1] = G[2][3] = G[2][6] = G[3][2] = G[3][4] = 1;
        G[4][3] = G[4][5] = G[4][6] = G[5][0] = G[5][4] = G[5][6] = 1;
        G[6][1] = G[6][2] = G[6][4] = G[6][5] = 1;

        boolean[] dp = new boolean[m];
        for (int i = 0; i < n; i++) dp[1 << i] = true;
        int ans = 0;
        for (int i = 0; i < m; i++) {
            if (dp[i]) {
                ans++;
                for (int j = 0; j < n; j++) {
                    if (((i >> j) & 1) == 0) {
                        if (check(i, j)) { // 如果 状态i 此时和第j好管连通
                            dp[i | (1 << j)] = true;
                        }
                    }
                }
            }
        }
        System.out.println(ans);
    }

    /**
     * 判断 七段码数码管此时状态 i 是否和 灯 j 连通
     *
     * @param i 状态 i，如 3 -- 0000011
     * @param j 灯 j
     * @return 连通返回 true，否则返回 false
     */
    public static boolean check(int i, int j) {
        for (int k = 0; k < n; k++) {
            if (G[k][j] == 1 && ((i >> k) & 1) == 1) return true;
        }

        return false;
    }
}
