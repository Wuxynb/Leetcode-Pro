package problems;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. 螺旋矩阵 （字节）
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 *
 *
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 *
 * 链接：https://leetcode-cn.com/problems/spiral-matrix/
 */
public class Problem54 {
    public static void main(String[] args) {
        System.out.println(spiralOrder(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        }));
        System.out.println(spiralOrder_(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        }));
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        // 初始化四个边界（上、下、左、右）
        int top = 0, bottom = matrix.length - 1, left = 0, right = matrix[0].length - 1;
        while (true) {
            // 往左（此时行为top，列从left到right）
            for (int i = left; i <= right; i++) ans.add(matrix[top][i]);
            // 将上边界加一（上边界往下走）
            if (++top > bottom) break;

            // 往下（此时行从top开始到bottom，列为right）
            for (int i = top; i <= bottom; i++) ans.add(matrix[i][right]);
            // 将右边界减一（右边界往左走）
            if (--right < left) break;

            // 往右（此时行为bottom，列从right到left）
            for (int i = right; i >= left; i--) ans.add(matrix[bottom][i]);
            // 将下边界减一（下边界往上走）
            if (--bottom < top) break;

            // 往上（此时行从bottom到top，列为left）
            for (int i = bottom; i >= top; i--) ans.add(matrix[i][left]);
            // 将左边界加一（左边界往右走）
            if (++left > right) break;
        }

        return ans;
    }

    public static List<Integer> spiralOrder_(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        int row = 0, col = 0, direct = 0;
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length * matrix[0].length; i++) {
            ans.add(matrix[row][col]);
            visited[row][col] = true;
            int nextRow = row + directions[direct][0];
            int nextCol = col + directions[direct][1];
            if (nextCol < 0 || nextRow < 0 || nextRow >= matrix.length || nextCol >= matrix[0].length || visited[nextRow][nextCol]) {
                direct = (direct + 1) % 4;
            }
            row += directions[direct][0];
            col += directions[direct][1];
        }

        return ans;
    }
}
