package problems;

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
        int top = 0, front = 0, side = 0;
        for (int i = 0, n = grid.length; i < n; i++) {
            int maxF = 0, maxS = 0;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) top++;
                maxF = Math.max(maxF, grid[i][j]);
                maxS = Math.max(maxS, grid[j][i]);
            }
            front += maxF;
            side += maxS;
        }
        return top + front + side;
    }
}
