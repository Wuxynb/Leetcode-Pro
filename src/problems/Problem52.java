package problems;

import java.util.Arrays;

/**
 * 52. N皇后 II
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：n = 4
 * 输出：2
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：1
 * <p>
 * 链接：https://leetcode-cn.com/problems/n-queens-ii/
 */
public class Problem52 {
    public static void main(String[] args) {

    }

    public int totalNQueens(int n) {
        int[] ans = new int[1];
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        backtrack(ans, board, n, 0);
        return ans[0];
    }

    public void backtrack(int[] ans, char[][] board, int n, int t) {
        if (t == n) {
            ans[0] += 1;
        } else {
            for (int i = 0; i < n; i++) {
                if (check(board, t, i)) {
                    board[t][i] = 'Q';
                    backtrack(ans, board, n, t + 1);
                    board[t][i] = '.';
                }
            }
        }
    }

    public boolean check(char[][] board, int i, int j) {
        for (int k = 0; k < i; k++) {
            if (board[k][j] == 'Q') return false;
        }

        for (int k = 1; k < board[0].length; k++) {
            if (i - k >= 0 && j - k >= 0 && board[i - k][j - k] == 'Q') return false;
            if (i - k >= 0 && j + k < board[0].length && board[i - k][j + k] == 'Q') return false;
        }

        return true;
    }
}
