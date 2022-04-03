package problems;

import java.util.Arrays;

public class Problem661 {
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(imageSmoother(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}})));
    }

    /**
     * 输入:img = [[1,1,1],[1,0,1],[1,1,1]]
     * 输出:[[0, 0, 0],[0, 0, 0], [0, 0, 0]]
     * 解释:
     * 对于点 (0,0), (0,2), (2,0), (2,2): 平均(3/4) = 平均(0.75) = 0
     * 对于点 (0,1), (1,0), (1,2), (2,1): 平均(5/6) = 平均(0.83333333) = 0
     * 对于点 (1,1): 平均(8/9) = 平均(0.88888889) = 0
     */
    public static int[][] imageSmoother(int[][] img) {
        int m = img.length, n = img[0].length;
        int[][] ans = new int[m][n], directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1},
                {0, 0}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                double num = 0, sum = 0;
                for (int[] direction : directions) {
                    int x = i + direction[0], y = j + direction[1];
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        num++;
                        sum += img[x][y];
                    }
                }
                ans[i][j] = (int) Math.floor(sum / num);
            }
        }
        return ans;
    }
}
