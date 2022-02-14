package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 37. 解数独
 * 编写一个程序，通过填充空格来解决数独问题。
 *
 * 数独的解法需 遵循如下规则：
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 *
 *
 *
 * 示例：
 *
 *
 * 输入：board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
 * 输出：[["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
 * 解释：输入的数独如上图所示，唯一有效的解决方案如下所示：
 *
 * 链接：https://leetcode-cn.com/problems/sudoku-solver/
 */
public class Problem37 {
    static boolean[][] row = new boolean[9][9];
    static boolean[][] col = new boolean[9][9];
    static boolean[][][] box = new boolean[3][3][9];
    static List<int[]> spaces = new ArrayList<>();
    static boolean valid = false;

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'},};
        solveSudoku(board);
        System.out.println(Arrays.deepToString(board));
    }

    public static void solveSudoku(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';
                    row[i][num] = col[j][num] = box[i / 3][j / 3][num] = true;
                } else spaces.add(new int[]{i, j});
            }
        }

        dfs(board, 0);
    }

    public static void dfs(char[][] board, int pos) {
        if (pos == spaces.size()) {
            valid = true;
            return;
        }
        int[] space = spaces.get(pos);
        int i = space[0], j = space[1];
        for (int num = 0; num < 9 && !valid; num++) {
            if (!row[i][num] && !col[j][num] && !box[i / 3][j / 3][num]) {
                row[i][num] = col[j][num] = box[i / 3][j / 3][num] = true;
                board[i][j] = (char) (num + 1 + '0');
                dfs(board, pos + 1);
                row[i][num] = col[j][num] = box[i / 3][j / 3][num] = false;
                // spaces数组记录空白位置，pos在迭代的过程中返回上层时会-1，
                // 表示数组已经认为这个位置可填写。迭代的标准是spaces是否遍历完而非board是否全填满
            }
        }
    }
}
