package problems;

import util.FNode;

public class Problem427 {
    public static void main(String[] args) {
        System.out.println(construct(new int[][]{{0, 1}, {1, 0}}));
        System.out.println(construct(new int[][]{
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0},
        }));
        System.out.println(construct(new int[][]{ // [[1,1,0,0],[0,0,1,1],[1,1,0,0],[0,0,1,1]]
                {1, 1, 0, 0},
                {0, 0, 1, 1},
                {1, 1, 0, 0},
                {0, 0, 1, 1}
        })); // [[0,1],[1,1],[1,1],[1,0],[1,0]]
        // [[0,1],[0,1],[0,1],[0,1],[0,1],[1,1],[1,1],[1,0],[1,0],[1,0],[1,0],[1,1],[1,1],[1,1],[1,1],[1,0],[1,0],[1,0],[1,0],[1,1],[1,1]]
    }

    public static FNode construct(int[][] grid) {
        return build(grid, new int[]{0, 0}, new int[]{grid.length, grid.length});
    }

    public static FNode build(int[][] gird, int[] start, int[] end) {
        boolean flag = true;
        for (int i = start[0]; i < end[0] && flag; i++) {
            for (int j = start[1]; j < end[1] && flag; j++) {
                if (gird[i][j] != gird[start[0]][start[1]]) flag = false;
            }
        }
        if (flag) return new FNode(gird[start[0]][start[1]] == 1, true);
        FNode node = new FNode(true, false);
        node.topLeft = build(gird, start, new int[]{(start[0] + end[0]) / 2, (start[1] + end[1]) / 2});
        node.topRight = build(gird, new int[]{start[0], (start[1] + end[1]) / 2}, new int[]{(start[0] + end[0]) / 2, end[1]});
        node.bottomLeft = build(gird, new int[]{(start[0] + end[0]) / 2, start[1]}, new int[]{end[0], (start[1] + end[1]) / 2});
        node.bottomRight = build(gird, new int[]{(start[0] + end[0]) / 2, (start[1] + end[1]) / 2}, end);
        return node;
    }
}
