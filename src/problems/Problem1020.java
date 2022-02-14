package problems;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 1020. 飞地的数量
 * 给你一个大小为 m x n 的二进制矩阵 grid ，其中 0 表示一个海洋单元格、1 表示一个陆地单元格。
 *
 * 一次 移动 是指从一个陆地单元格走到另一个相邻（上、下、左、右）的陆地单元格或跨过 grid 的边界。
 *
 * 返回网格中 无法 在任意次数的移动中离开网格边界的陆地单元格的数量。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
 * 输出：3
 * 解释：有三个 1 被 0 包围。一个 1 没有被包围，因为它在边界上。
 * 示例 2：
 *
 *
 * 输入：grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
 * 输出：0
 * 解释：所有 1 都在边界上或可以到达边界。
 *
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 500
 * grid[i][j] 的值为 0 或 1
 *
 * link: https://leetcode-cn.com/problems/number-of-enclaves/
 *
 * @author Wuxinyue
 * @version 1.0
 * @date 2022/2/14 15:31
 */
public class Problem1020 {
    public static void main(String[] args) {

    }

    public int numEnclavesBfs(int[][] grid) {
        int m = grid.length, n = grid[0].length, ans = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        int[][] dirs = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int i = 0; i < n; i++) {
            if (grid[0][i] == 1) {
                queue.offer(new int[]{0, i});
                grid[0][i] = 0;
            }
            if (grid[m - 1][i] == 1) {
                queue.offer(new int[]{m - 1, i});
                grid[m - 1][i] = 0;
            }
        }
        for (int i = 1; i < m - 1; i++) {
            if (grid[i][0] == 1) {
                queue.offer(new int[]{i, 0});
                grid[i][0] = 0;
            }
            if (grid[i][n - 1] == 1) {
                queue.offer(new int[]{i, n - 1});
                grid[i][n - 1] = 0;
            }
        }
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] dir : dirs) {
                int x = cur[0] + dir[0], y = cur[1] + dir[1];
                if (x < 0 || x == m || y < 0 || y == n || grid[x][y] != 1) continue;
                queue.offer(new int[]{x, y});
                grid[x][y] = 0;
            }
        }
        for (int[] ints : grid) {
            for (int j = 0; j < n; j++) {
                if (ints[j] == 1) ans++;
            }
        }
        return ans;
    }

    public int numEnclaves(int[][] grid) {
        int m = grid.length, n = grid[0].length, ans = 0;
        for (int i = 0; i < n; i++) {
            if (grid[0][i] == 1) dfs(grid, 0, i);
            if (grid[m - 1][i] == 1) dfs(grid, m - 1, i);
        }
        for (int i = 1; i < m - 1; i++) {
            if (grid[i][0] == 1) dfs(grid, i, 0);
            if (grid[i][n - 1] == 1) dfs(grid, i, n - 1);
        }
        for (int[] ints : grid) {
            for (int j = 0; j < n; j++) {
                if (ints[j] == 1) ans++;
            }
        }
        return ans;
    }

    public void dfs(int[][] grid, int i, int j) {
        if (i < 0 || i == grid.length || j < 0 || j == grid[0].length || grid[i][j] == 0) return;
        grid[i][j] = 0;
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
    }
}
