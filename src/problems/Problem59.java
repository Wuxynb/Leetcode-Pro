package problems;

import java.util.Arrays;

/**
 * 59. 螺旋矩阵 II
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：[[1]]
 * <p>
 *
 * 链接：https://leetcode-cn.com/problems/spiral-matrix-ii/
 */
public class Problem59 {
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(generateMatrix(10)));
        System.out.println(Arrays.deepToString(generateMatrix_(10)));
    }

    public static int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int top = 0, bottom = n - 1, left = 0, right = n - 1, num = 1;
        while (true) {
            // 往右走
            for (int i = left; i <= right; i++) matrix[top][i] = num++;
            if (++top > bottom) break;
            // 往下走
            for (int i = top; i <= bottom; i++) matrix[i][right] = num++;
            if (--right < left) break;
            // 往左走
            for (int i = right; i >= left; i--) matrix[bottom][i] = num++;
            if (--bottom < top) break;
            // 往上走
            for (int i = bottom; i >= top; i--) matrix[i][left] = num++;
            if (++left > right) break;
        }
        return matrix;
    }

    public static int[][] generateMatrix_(int n) {
        int num = 1, directIdx = 0, row = 0, col = 0;
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int[][] matrix = new int[n][n];
        boolean[][] visited = new boolean[n][n];
        for (int i = 0; i < n * n; i++) {
            matrix[row][col] = num++;
            visited[row][col] = true;
            int nextRow = row + directions[directIdx][0];
            int nextCol = col + directions[directIdx][1];
            if (nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= n || visited[nextRow][nextCol]) {
                directIdx = (directIdx + 1) % 4;
            }
            row += directions[directIdx][0];
            col += directions[directIdx][1];
        }

        return matrix;
    }
}
