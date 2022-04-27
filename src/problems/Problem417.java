package problems;

import java.util.*;

/**
 * 417. 太平洋大西洋水流问题
 * 有一个 m × n 的矩形岛屿，与 太平洋 和 大西洋 相邻。 “太平洋” 处于大陆的左边界和上边界，而 “大西洋” 处于大陆的右边界和下边界。
 *
 * 这个岛被分割成一个由若干方形单元格组成的网格。给定一个 m x n 的整数矩阵 heights ， heights[r][c] 表示坐标 (r, c) 上单元格 高于海平面的高度 。
 *
 * 岛上雨水较多，如果相邻单元格的高度 小于或等于 当前单元格的高度，雨水可以直接向北、南、东、西流向相邻单元格。水可以从海洋附近的任何单元格流入海洋。
 *
 * 返回 网格坐标 result 的 2D列表 ，其中 result[i] = [ri, ci] 表示雨水可以从单元格 (ri, ci) 流向 太平洋和大西洋 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
 * 输出: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 * 示例 2：
 *
 * 输入: heights = [[2,1],[1,2]]
 * 输出: [[0,0],[0,1],[1,0],[1,1]]
 *
 *
 * 提示：
 *
 * m == heights.length
 * n == heights[r].length
 * 1 <= m, n <= 200
 * 0 <= heights[r][c] <= 105
 *
 * link: https://leetcode-cn.com/problems/pacific-atlantic-water-flow/
 */
public class Problem417 {
    public static void main(String[] args) {
        System.out.println(pacificAtlantic(new int[][]{
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        }));
    }

    public static void bfs(int[][] heights, int i, int j, boolean[][] visited) {
        visited[i][j] = true;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{i, j});
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] dir : dirs) {
                int x = cur[0] + dir[0], y = cur[1] + dir[1];
                if (x < 0 || y < 0 || x >= heights.length || y >= heights[0].length || heights[x][y] < heights[cur[0]][cur[1]] || visited[x][y]) continue;
                queue.offer(new int[]{x, y});
                visited[x][y] = true;
            }
        }
    }

    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> ans = new ArrayList<>();
        int m = heights.length, n = heights[0].length;
        boolean[][] pacific = new boolean[m][n], atlantic = new boolean[m][n];
        for (int j = 0; j < n; j++) {
            bfs(heights, 0, j, pacific);
            bfs(heights, m - 1, j, atlantic);
        }
        for (int i = 0; i < m; i++) {
            bfs(heights, i, 0, pacific);
            bfs(heights, i, n - 1, atlantic);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) ans.add(Arrays.asList(i, j));
            }
        }
        return ans;
    }

    public static void dfs(int[][] heights, int i, int j, boolean[][] visited) {
        visited[i][j] = true;
        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        for (int[] direction : directions) {
            int x = i + direction[0], y = j + direction[1];
            if (x < 0 || x >= heights.length || y < 0 || y >= heights[0].length || heights[x][y] < heights[i][j] || visited[x][y]) continue;
            dfs(heights, x, y, visited);
        }
    }

    public static List<List<Integer>> pacificAtlantic2(int[][] heights) {
        List<List<Integer>> ans = new ArrayList<>();
        int m = heights.length, n = heights[0].length;
        Boolean[][] cache1 = new Boolean[m][n], cache2 = new Boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(heights, i, j, true, cache1, new boolean[m][n]) &&
                        dfs(heights, i, j, false, cache2, new boolean[m][n]))
                    ans.add(Arrays.asList(i, j));
            }
        }
        return ans;
    }

    public static boolean dfs(int[][] heights, int i, int j, boolean pacific, Boolean[][] cache, boolean[][] visited) {
        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        boolean ret = false;
        visited[i][j] = true;
        if (cache[i][j] != null && cache[i][j]) return cache[i][j];
        for (int[] direction : directions) {
            int x = i + direction[0], y = j + direction[1];
            if ((pacific && (x < 0 || y < 0)) || (!pacific && (x >= heights.length || y >= heights[0].length))) {
                cache[i][j] = true;
                return true;
            }
            if (x < 0 || x >= heights.length || y < 0 || y >= heights[0].length || heights[x][y] > heights[i][j]) continue;
            if (!visited[x][y] && dfs(heights, x, y, pacific, cache, visited)) {
                ret = true;
                break;
            }
        }
        cache[i][j] = ret;
        return ret;
    }
}
