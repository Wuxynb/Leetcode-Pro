package problems;

/**
 * 85. 最大矩形
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：6
 * 解释：最大矩形如上图所示。
 * 示例 2：
 * <p>
 * 输入：matrix = []
 * 输出：0
 * 示例 3：
 * <p>
 * 输入：matrix = [["0"]]
 * 输出：0
 * 示例 4：
 * <p>
 * 输入：matrix = [["1"]]
 * 输出：1
 * 示例 5：
 * <p>
 * 输入：matrix = [["0","0"]]
 * 输出：0
 * <p>
 * 链接：https://leetcode-cn.com/problems/maximal-rectangle/
 */
public class Problem85 {
    public static void main(String[] args) {
        System.out.println(maximalRectangle(new char[][]{
                {'1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '0'},
                {'1', '1', '1', '1', '1', '1', '1', '0'},
                {'1', '1', '1', '1', '1', '0', '0', '0'},
                {'0', '1', '1', '1', '1', '0', '0', '0'},
        }));
        System.out.println(maximalRectangle(new char[][]{{'1'}}));
        System.out.println(maximalRectangle(new char[][]{{'0'}}));
    }

    public static int maximalRectangleBF(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int ans = 0, n = matrix.length, m = matrix[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == '1') {
                    int width, height;
                    for (width = j; width < m && matrix[i][width] == '1'; width++) {
                        height = i;
                        while (height < n - 1 && matrix[height + 1][j] == '1') {
                            int k;
                            for (k = j; k <= width; k++) if (matrix[height + 1][k] != '1') break;
                            if (k == width + 1) height++;
                            else break;
                        }
                        ans = Math.max(ans, (width - j + 1) * (height - i + 1));
                    }
                }
            }
        }

        return ans;
    }

    public static int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length, ans = 0, top;
        int[] heights = new int[n + 1];
        for (int i = 0; i < m; i++) {
            heights[n] = 0; top = -1;
            int[] stack = new int[n];
            for (int j = 0; j <= n; j++) {
                if (j < n) {
                    if (matrix[i][j] == '1') heights[j] += 1;
                    else heights[j] = 0;
                }

                while (top != -1 && heights[j] < heights[stack[top]]) {
                    int curH = heights[stack[top--]];
                    int curW = top != -1 ? j - stack[top] - 1 : j;
                    ans = Math.max(ans, curH * curW);
                }

                if (top < n - 1) stack[++top] = j; // 避免全为0的情况，栈索引越界
            }
        }

        return ans;
    }
}
