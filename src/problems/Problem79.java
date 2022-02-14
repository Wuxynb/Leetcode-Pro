package problems;

/**
 * 79. 单词搜索 （小米）
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * 示例 2：
 * <p>
 * <p>
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * 输出：true
 * 示例 3：
 * <p>
 * <p>
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board 和 word 仅由大小写英文字母组成
 * <p>
 * 链接：https://leetcode-cn.com/problems/word-search/
 */
public class Problem79 {
    public static void main(String[] args) {
        System.out.println(exist(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'A', 'C', 'C', 'E'},
                {'S', 'E', 'C', 'S'},
                {'A', 'D', 'E', 'E'},
        }, "ABCED"));
    }

    public static boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (backtrack(i, j, board, visited, word, 0)) return true;
                }
            }
        }

        return false;
    }

    public static boolean backtrack(int i, int j, char[][] board, boolean[][] visited, String word, int k) {
        if (board[i][j] != word.charAt(k)) return false;
        else if (k == word.length() - 1) return true;

        visited[i][j] = true;
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int[] dire : directions) {
            int newI = i + dire[0], newJ = j + dire[1];
            if (newI >= 0 && newI < board.length && newJ >= 0 && newJ < board[0].length && !visited[newI][newJ]) {
                if (backtrack(newI, newJ, board, visited, word, k + 1)) {
                    return true;
                }
            }
        }
        visited[i][j] = false;

        return false;
    }
}
