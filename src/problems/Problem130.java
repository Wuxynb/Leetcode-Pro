package problems;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 130. 被围绕的区域
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *
 *
 * 示例 1：
 *
 *
 * 输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * 输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 * 示例 2：
 *
 * 输入：board = [["X"]]
 * 输出：[["X"]]
 *
 * 链接：https://leetcode-cn.com/problems/surrounded-regions/
 */
public class Problem130 {
    public static void main(String[] args) {
        /*char[][] board = new char[][]{
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };*/
       /* char[][] board = new char[][]{
                {'O', 'X', 'X', 'O', 'X'},
                {'X', 'O', 'O', 'X', 'O'},
                {'X', 'O', 'X', 'O', 'X'},
                {'O', 'X', 'O', 'O', 'O'},
                {'X', 'X', 'O', 'X', 'O'}
        };*/
        char[][] board = new char[][]{
                {'O', 'O', 'O', 'O', 'X', 'X'},
                {'O', 'O', 'O', 'O', 'O', 'O'},
                {'O', 'X', 'O', 'X', 'O', 'O'},
                {'O', 'X', 'O', 'O', 'X', 'O'},
                {'O', 'X', 'O', 'X', 'O', 'O'},
                {'O', 'X', 'O', 'O', 'O', 'O'}
        };
        solveBFS(board);
        System.out.println(Arrays.deepToString(board));
    }

    public static void solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            dfs(board, i, 0);
            dfs(board, i, board[0].length - 1);
        }
        for (int j = 1; j < board[0].length - 1; j++) {
            dfs(board, 0, j);
            dfs(board, board.length - 1, j);
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'A') board[i][j] ='O';
                else if (board[i][j] == 'O') board[i][j] = 'X';
            }
        }
    }

    public static void dfs(char[][] board, int i, int j) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] != 'O') return;
        board[i][j] = 'A';
        dfs(board, i - 1, j);
        dfs(board, i, j + 1);
        dfs(board, i + 1, j);
        dfs(board, i, j - 1);
    }

    public static void solveBFS(char[][] board) {
        int m = board.length, n = board[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                queue.offer(new int[]{i, 0});
                board[i][0] = 'A';
            }
            if (board[i][n - 1] == 'O') {
                queue.offer(new int[]{i, n - 1});
                board[i][n - 1] = 'A';
            }
        }
        for (int j = 1; j < n - 1; j++) {
            if (board[0][j] == 'O') {
                queue.offer(new int[]{0, j});
                board[0][j] = 'A';
            }
            if (board[m - 1][j] == 'O') {
                queue.offer(new int[]{m - 1, j});
                board[m - 1][j] = 'A';
            }
        }
        int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];
            for (int[] direction : directions) {
                int newX = x + direction[0], newY = y + direction[1];
                if (newY < 0 || newX < 0 || newX >= m || newY >= n || board[newX][newY] != 'O') continue;
                board[newX][newY] = 'A';
                queue.offer(new int[]{newX, newY});
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'A') board[i][j] ='O';
                else if (board[i][j] == 'O') board[i][j] = 'X';
            }
        }
    }
}
