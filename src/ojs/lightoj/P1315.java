package ojs.lightoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 超级骑士就像一个国际象棋骑士，只是它有一些普通骑士无法完成的特殊动作。Alice和Bob正在玩这个游戏（你可能想知道为什么他们总是玩这些游戏！）。
 * 像往常一样，他们都交替转身，以最佳状态比赛，爱丽丝先开始。对于这个游戏，超级骑士有6个有效的招式，如下图所示（圆圈显示骑士）。
 * <p>
 * 超级骑士招式
 * <p>
 * 他们在一个无限的棋盘上玩这个游戏，其中左上角的单元格是（0，0），右下角的单元格是（0，0），下角的单元格是（1，0）。最初在棋盘上有一些超级骑士，
 * 在每一回合中，玩家选择一个骑士，并给出一个有效的骑士移动，如图所示。无法进行有效移动的玩家将失败。多个骑士可以进入同一个牢房，但每回合只能移动一个
 * 骑士。
 * <p>
 * 你在棋盘上获得了最初的骑士位置，你必须找到游戏的赢家。
 * <p>
 * 输入
 * <p>
 * 输入以整数T开头(≤ 200），表示测试用例的数量。每种情况都以包含整数n（1）的行开始≤ N≤ 1000）其中n表示超级骑士的数量。接下来的n行中的每一行都包
 * 含两个整数x y（0≤ x、 y<500）表示骑士的位置。
 * <p>
 * 输出
 * <p>
 * 对于每个案例，打印案例编号和获胜玩家的姓名。
 * <p>
 * Sample nput
 * <p>
 * 2
 * <p>
 * 1
 * <p>
 * 1 0
 * <p>
 * 2
 * <p>
 * 2 5
 * <p>
 * 3 5
 * <p>
 * Output
 * <p>
 * Case 1: Bob
 * <p>
 * Case 2: Alice
 * <p>
 * 题解：
 * <p>
 * 典型的博弈SG函数，对每一个骑士的起始位置求SG值，然后将所有的SG值进行异或，如果其值为0，则先手必败，即Bob 获胜，否则先手必胜，Alice获胜。
 * <p>
 * 又由于这道题是二维的，因此每一个位置都是由x和y两个值来决定的，因此这道题无法使用打表的方式进行求SG值，需要时dfs的方式，SG初始化为-1即可。
 * <p>
 * 原文链接：https://blog.csdn.net/m0_37611893/article/details/80405987
 */
public class P1315 {
    static int maxn = 1005;
    static int[][] sg = new int[maxn][maxn];
    static int[][] dirs = new int[][]{{1, -2}, {-1, -3}, {-1, -2}, {-2, -1}, {-3, -1}, {-2, 1}};

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 65534);
    static StringTokenizer tokenizer = new StringTokenizer("");

    public static void main(String[] args) {
        int t = nextInt(), c = 1;
        for (int i = 0; i < maxn; i++) Arrays.fill(sg[i], -1);
        while (t-- > 0) {
            int n = nextInt(), ans = 0;
            while (n-- > 0) {
                int x = nextInt(), y = nextInt();
                ans ^= getSg(x, y);
            }
            if (ans == 0) System.out.println("Case " + (c++) + ": Bob");
            else System.out.println("Case " + (c++) + ": Alice");
        }
    }

    public static int getSg(int x, int y) {
        if (sg[x][y] != -1) return sg[x][y];
        boolean[] visited = new boolean[maxn];
        for (int[] dir : dirs) {
            int i = x + dir[0], j = y + dir[1];
            if (i >= 0 && j >= 0) {
                sg[i][j] = getSg(i, j);
                visited[sg[i][j]] = true;
            }
        }
        for (int i = 0; ; i++) {
            if (!visited[i]) return sg[x][y] = i;
        }
    }

    public static String next() {
        while (!tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tokenizer.nextToken();
    }

    public static int nextInt() { return Integer.parseInt(next()); }
}
