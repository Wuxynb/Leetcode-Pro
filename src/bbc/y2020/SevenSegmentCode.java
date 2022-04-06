package bbc.y2020;

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
 * 输出格式
 * 这是一道结果填空的题，你只需要算出结果后提交即可。本题的结果为一个整数，在提交答案时只输出这个整数，输出多余的内容将无法得分。
 */
public class SevenSegmentCode {
    static int[][] G;
    static int n = 7, ans = 0;
    static int[] father;
    static boolean[] light;

    public static void main(String[] args) {
        G = new int[n + 1][n + 1];
        light = new boolean[n + 1];
        father = new int[n + 1];

        G[1][2] = G[1][6] = G[2][1] = G[2][3] = G[2][7] = 1;
        G[3][2] = G[3][4] = G[3][7] = G[4][3] = G[4][5] = 1;
        G[5][4] = G[5][6] = G[5][7] = G[6][1] = G[6][5] = G[6][7] = 1;
        G[7][2] = G[7][3] = G[7][5] = G[7][6] = 1;

        backtrack(1);

        System.out.println(ans);
    }

    public static int find(int x) {
        if (x == father[x]) return x;
        return find(father[x]);
    }

    public static void backtrack(int idx) {
        if (idx == n + 1) {
            for (int i = 1; i <= n; i++) {
                father[i] = i;
            }
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (G[i][j] == 1 && light[i] && light[j]) father[find(j)] = find(i);
                }
            }
            int k = 0;
            for (int i = 1; i <= 7; i++) {
                if (light[i] && i == father[i]) k++; // i == father[i]表明i是他自己的父节点，即为一个帮派领主
            }
            if (k == 1) ans++;
        } else {
            light[idx] = true;
            backtrack(idx + 1);
            light[idx] = false;
            backtrack(idx + 1);
        }
    }
}
