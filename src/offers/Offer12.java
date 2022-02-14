package offers;

/**
 * 剑指 Offer 12. 矩阵中的路径
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 *
 *
 * 例如，在下面的 3×4 的矩阵中包含单词 "ABCCED"（单词中的字母已标出）。
 *
 *
 *
 *
 *
 * 示例 1：
 *
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * 示例 2：
 *
 * 输入：board = [["a","b"],["c","d"]], word = "abcd"
 * 输出：false
 *
 * 链接：https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/
 */
public class Offer12 {
    public static void main(String[] args) {

    }

    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (backtrack(board, word, i, j, 0, visited)) return true;
                }
            }
        }
        return false;
    }

    public boolean backtrack(char[][] board, String word, int i, int j, int t, boolean[][] visited) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j] || board[i][j] != word.charAt(t)) return false;
        if (t == word.length() - 1) return true;
        visited[i][j] = true;
        boolean res = backtrack(board, word, i, j + 1, t + 1, visited) || backtrack(board, word, i + 1, j, t + 1, visited) ||
                backtrack(board, word, i, j - 1, t + 1, visited) || backtrack(board, word, i - 1, j, t + 1, visited);
        visited[i][j] = false;
        return res;
    }
}
