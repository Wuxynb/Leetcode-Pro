package problems;

import java.util.Arrays;

public class Problem883 {
    public static void main(String[] args) {
        System.out.println(projectionArea(new int[][]{{1, 2}, {3, 4}}));
        System.out.println(projectionArea(new int[][]{{1, 0}, {0, 2}}));
        System.out.println(projectionArea(new int[][]{{2}}));
    }

    /*
     * [[1,2], [3,4]]
     * 17
     */
    public static int projectionArea(int[][] grid) {
        int n = grid.length, top = n * n, front = 0, side = 0, sub = 0;
        for (int i = 0; i < n; i++) {
            int maxF = 0, maxS = 0;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) sub++;
                maxF = Math.max(maxF, grid[i][j]);
                maxS = Math.max(maxS, grid[j][i]);
            }
            front += maxF;
            side += maxS;
        }
        return top - sub + front + side;
    }
}
