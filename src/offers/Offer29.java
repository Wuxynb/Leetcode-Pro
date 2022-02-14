package offers;

import java.util.Arrays;

/**
 * 剑指 Offer 29. 顺时针打印矩阵
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 *
 *
 *
 * 示例 1：
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 *
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * 链接：https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/
 */
public class Offer29 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(spiralOrder(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}})));
    }

    public static int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return new int[]{};
        int m = matrix.length, n = matrix[0].length;
        int[] res = new int[m * n];
        int top = 0, left = 0, right = n - 1, bottom = m - 1, c = 0;
        while (true) {
            for (int i = left; i <= right; i++) res[c++] = matrix[top][i];
            if (++top > bottom) break;
            for (int i = top; i <= bottom; i++) res[c++] = matrix[i][right];
            if (--right < left) break;
            for (int i = right; i >= left; i--) res[c++] = matrix[bottom][i];
            if (--bottom < top) break;
            for (int i = bottom; i >= top; i--) res[c++] = matrix[i][left];
            if (++left > right) break;
        }
        return res;
    }
}
