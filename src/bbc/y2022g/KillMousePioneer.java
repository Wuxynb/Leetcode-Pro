package bbc.y2022g;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 题目描述
 * <p>
 * 灭鼠先锋是一个老少咸宜的棋盘小游戏，由两人参与，轮流操作。
 * <p>
 * <p>
 * 灭鼠先锋的棋盘有各种规格，本题中游戏在两行四列的棋盘上进行。
 * 游戏的规则为：两人轮流操作，每次可选择在棋盘的一个空位上放置一个棋子，或在同一行的连续两个空位上各放置一个棋子，放下棋子后使棋盘放满的一方输掉游戏。
 * <p>
 * 小蓝和小乔一起玩游戏，小蓝先手，小乔后手。小蓝可以放置棋子的方法很多，通过旋转和翻转可以对应如下四种情况：
 *
 * <p>
 * XOOO XXOO OXOO OXXO
 * <p>
 * OOOO OOOO OOOO OOOO
 * <p>
 * 其中 O 表示棋盘上的一个方格为空，X 表示该方格已经放置了棋子。
 * <p>
 * 请问，对于以上四种情况，如果小蓝和小乔都是按照对自己最优的策略来玩游戏，小蓝是否能获胜。
 * <p>
 * 如果获胜，请用 V 表示，否则用 L 表示。
 * <p>
 * 请将四种情况的胜负结果按顺序连接在一起提交。
 * <p>
 * 这是一道结果填空的题，你只需要算出结果后输出即可。
 * <p>
 * 本题的结果为一个长度为 4 的由大写字母 V 和 L 组成的字符串，如 VVLL，在提交答案时只需输出这个字符串。
 */
public class KillMousePioneer {
    static BufferedReader input;
    static StringTokenizer tokenizer;

    public static void main(String[] args) {
        input = new BufferedReader(new InputStreamReader(System.in), 65536);
        tokenizer = new StringTokenizer("");
        // LLLV
        char[][] grid1 = new char[][]{{'X', 'O', 'O', 'O'}, {'O', 'O', 'O', 'O'}};
        char[][] grid2 = new char[][]{{'X', 'X', 'O', 'O'}, {'O', 'O', 'O', 'O'}};
        char[][] grid3 = new char[][]{{'O', 'X', 'O', 'O'}, {'O', 'O', 'O', 'O'}};
        char[][] grid4 = new char[][]{{'O', 'X', 'X', 'O'}, {'O', 'O', 'O', 'O'}};
        System.out.print(dfs(grid1, 7) ? "L" : "V");
        System.out.print(dfs(grid2, 6) ? "L" : "V");
        System.out.print(dfs(grid3, 7) ? "L" : "V");
        System.out.print(dfs(grid4, 6) ? "L" : "V");
    }

    /*
     *  博弈论核心：
     *      只能转移到必胜态的，均为必败态。
     *      可以转移到必败态的，均为必胜态。
     *
     */
    public static boolean isWin(char[][] grid, int rest) {
        if (rest == 0) return true; // 剩余为0时，当前下棋的人获胜
        boolean ret = false; // 假设当前状态为不能赢，则后续遍历所有情况，将能赢的结果留给自己
        for (int l = 0; l < grid.length; l++) {
            for (int k = 0; k < grid[0].length; k++) {
                if (grid[l][k] == 'O') {
                    grid[l][k] = 'X';
                    if (!isWin(grid, rest - 1)) ret = true; // 可以到达必败态均为必胜态
                    if (k + 1 < grid[0].length && grid[l][k + 1] == 'O') {
                        grid[l][k + 1] = 'X';
                        if (!isWin(grid, rest - 2)) ret = true; // 可以到达必败态均为必胜态
                        grid[l][k + 1] = 'O';
                    }
                    grid[l][k] = 'O';
                }
            }
        }
        // 运行到此，说明只能转移到必胜态，此时为必败态
        return ret;
    }

    public static boolean backtrack(char[][] grid, int rest) {
        if (rest == 0) return true; // 小桥获胜
        boolean ans = false;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 'O') {
                    grid[i][j] = 'X';
                    if (!backtrack(grid, rest - 1)) ans = true; // todo：这里不直接返回的原因是需要 回退恢复改变的 grid
                    if (j + 1 < grid[0].length && grid[i][j + 1] == 'O') {
                        grid[i][j + 1] = 'X';
                        if (!backtrack(grid, rest - 2)) ans = true;
                        grid[i][j + 1] = 'O';
                    }
                    grid[i][j] = 'O';
                }
            }
        }
        return ans;
    }

    public static boolean dfs(char[][] grid, int rest) {
        if (rest < 2) return rest == 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 'O') {
                    grid[i][j] = 'X';
                    if (!dfs(grid, rest - 1)) {
                        grid[i][j] = 'O';
                        return true;
                    }
                    grid[i][j] = 'O';
                }
            }
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 'O' && j + 1 < grid[0].length && grid[i][j + 1] == 'O') {
                    grid[i][j] = grid[i][j + 1] = 'X';
                    if (!dfs(grid, rest - 2)) {
                        grid[i][j] = grid[i][j + 1] = 'O';
                        return true;
                    }
                    grid[i][j] = grid[i][j + 1] = 'O';
                }
            }
        }
        return false;
    }

    private static String next() {
        while (!tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(input.readLine());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }
}
