package problems;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * 1765. 地图中的最高点
 * 给你一个大小为 m x n 的整数矩阵 isWater ，它代表了一个由 陆地 和 水域 单元格组成的地图。
 * <p>
 * 如果 isWater[i][j] == 0 ，格子 (i, j) 是一个 陆地 格子。
 * 如果 isWater[i][j] == 1 ，格子 (i, j) 是一个 水域 格子。
 * 你需要按照如下规则给每个单元格安排高度：
 * <p>
 * 每个格子的高度都必须是非负的。
 * 如果一个格子是是 水域 ，那么它的高度必须为 0 。
 * 任意相邻的格子高度差 至多 为 1 。当两个格子在正东、南、西、北方向上相互紧挨着，就称它们为相邻的格子。（也就是说它们有一条公共边）
 * 找到一种安排高度的方案，使得矩阵中的最高高度值 最大 。
 * <p>
 * 请你返回一个大小为 m x n 的整数矩阵 height ，其中 height[i][j] 是格子 (i, j) 的高度。如果有多种解法，请返回 任意一个 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：isWater = [[0,1],[0,0]]
 * 输出：[[1,0],[2,1]]
 * 解释：上图展示了给各个格子安排的高度。
 * 蓝色格子是水域格，绿色格子是陆地格。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：isWater = [[0,0,1],[1,0,0],[0,0,0]]
 * 输出：[[1,1,0],[0,1,1],[1,2,2]]
 * 解释：所有安排方案中，最高可行高度为 2 。
 * 任意安排方案中，只要最高高度为 2 且符合上述规则的，都为可行方案。
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == isWater.length
 * n == isWater[i].length
 * 1 <= m, n <= 1000
 * isWater[i][j] 要么是 0 ，要么是 1 。
 * 至少有 1 个水域格子。
 * <p>
 * 链接：https://leetcode-cn.com/problems/map-of-highest-peak/
 *
 * @author Wuxinyue
 * @version 1.0
 * @date 2022/1/29 20:06
 */
public class Problem1765 { // 多源BFS
    public static void main(String[] args) {
        for (int[] arr : highestPeak(new int[][]{
                {0, 1},
                {0, 0}})) {
            System.out.println(Arrays.toString(arr));
        }
        for (int[] arr : highestPeak(new int[][]{
                {0, 0, 1},
                {1, 0, 0},
                {0, 0, 0}})) {
            System.out.println(Arrays.toString(arr));
        }
    }

    public static int[][] highestPeak(int[][] isWater) {
        int m = isWater.length, n = isWater[0].length;
        int[][] ans = new int[m][n], directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            Arrays.fill(ans[i], -1);
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1) {
                    ans[i][j] = 0;
                    queue.offer(new int[]{i, j});
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] xy = queue.poll();
            for (int[] direction : directions) {
                int x = xy[0] + direction[0], y = xy[1] + direction[1];
                if (x >= 0 && x < m && y >= 0 && y < n && ans[x][y] == -1) {
                    ans[x][y] = ans[xy[0]][xy[1]] + 1;
                    queue.offer(new int[]{x, y});
                }
            }
        }
        return ans;
    }
}
