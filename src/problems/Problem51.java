package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 51. N 皇后
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 *
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：n = 4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：[["Q"]]
 *
 * 链接：https://leetcode-cn.com/problems/n-queens/
 */
public class Problem51 {
    public static void main(String[] args) {
        System.out.println(solveNQueens(4));
    }

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        backtrack(ans, board, n, 0);
        return ans;
    }

    public static void backtrack(List<List<String>> lists, char[][] board, int n, int t) {
        if (t == n) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                list.add(String.valueOf(board[i]));
            }
            lists.add(list);
        } else {
            for (int i = 0; i < n; i++) {
                if (check(board, t, i)) {
                    board[t][i] = 'Q';
                    backtrack(lists, board, n, t + 1);
                    board[t][i] = '.';
                }
            }
        }
    }

    public static boolean check(char[][] board, int i, int j) {
        for (int k = 0; k < i; k++) { // col
            if (board[k][j] == 'Q') return false;
        }

        for (int k = 1; k < board[0].length; k++) { // diagonal
            if (i - k >= 0 && j - k >= 0 && board[i - k][j - k] == 'Q') return false;
//            if (i + k < board[0].length && j + k < board[0].length && board[i + k][j + k] == 'Q') return false;

            if (i - k >= 0 && j + k < board[0].length && board[i - k][j + k] == 'Q') return false;
//            if (i + k < board[0].length && j - k >= 0 && board[i + k][j - k] == 'Q') return false;
        }

        return true;
    }
}
