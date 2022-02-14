package problems;

import java.util.HashMap;
import java.util.Map;

/**
 * 36. 有效的数独
 * 请你判断一个 9x9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 *
 * 注意：
 *
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 *
 *
 * 示例 1：
 *
 *
 * 输入：board =
 * [["5","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * 输出：true
 * 示例 2：
 *
 * 输入：board =
 * [["8","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * 输出：false
 * 解释：除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
 *
 * 链接：https://leetcode-cn.com/problems/valid-sudoku/
 */
public class Problem36 {

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
        System.out.println(isValidSudoku(board));

        System.out.println('1' - '0');
        System.out.println('2' - '1');
    }

    public static boolean isValidSudoku(char[][] board) {
        Map<Character, Integer>[] colMap = new HashMap[9];
        Map<Character, Integer>[] rowMap = new HashMap[9];
        Map<Character, Integer>[] cellMap = new HashMap[9];
        for (int i = 0; i < 9; i++) {
            colMap[i] = new HashMap<>();
            rowMap[i] = new HashMap<>();
            cellMap[i] = new HashMap<>();
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') {
                    int boxIdx = (i / 3) * 3 + j / 3;
                    if (rowMap[i].get(board[i][j]) != null ||
                            colMap[j].get(board[i][j]) != null ||
                            cellMap[boxIdx].get(board[i][j]) != null) return false;
                    else {
                        rowMap[i].put(board[i][j], 1);
                        colMap[j].put(board[i][j], 1);
                        cellMap[boxIdx].put(board[i][j], 1);
                    }
                }
            }
        }

        return true;
    }

    public static boolean isValidSudokuPlus(char[][] board) {
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][] box = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1', boxIdx = (i / 3) * 3 + j / 3;
                    if (row[i][num] || col[j][num] || box[boxIdx][num]) return false;
                    else {
                        row[i][num] = true;
                        col[j][num] = true;
                        box[boxIdx][num] = true;
                    }
                }
            }
        }

        return true;
    }

    public static boolean isValidSudokuL(char[][] board) {
        Map<Character, Integer> colMap = new HashMap<>();
        Map<Character, Integer> rowMap = new HashMap<>();
        Map<Character, Integer> cellMap = new HashMap<>();
        for (int i = 0; i < board.length; i++) {
            rowMap.clear();
            colMap.clear();
            for (int j = 0; j < board[0].length; j++) {
                if (rowMap.get(board[i][j]) != null) return false;
                else if (board[i][j] <= '9' && board[i][j] >= '1') rowMap.put(board[i][j], 1);

                if (colMap.get(board[j][i]) != null) return false;
                else if (board[j][i] <= '9' && board[j][i] >= '1') colMap.put(board[j][i], 1);
            }
        }

        for (int i = 0; i < 9; i++) {
            int r = i / 3, c = i % 3;
            cellMap.clear();
            for (int j = r * 3; j < 3 + r * 3; j++) {
                for (int k = c * 3; k < 3 + c * 3; k++) {
                    if (cellMap.get(board[j][k]) != null) return false;
                    else if (board[j][k] <= '9' && board[j][k] >= '1') cellMap.put(board[j][k], 1);
                }
            }
        }

        return true;
    }
}
