package offers;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 剑指 Offer 13. 机器人的运动范围
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移
 * 动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格
 * [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：m = 2, n = 3, k = 1
 * 输出：3
 * 示例 2：
 * <p>
 * 输入：m = 3, n = 1, k = 0
 * 输出：1
 * <p>
 * 链接：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/
 */
public class Offer13 {
    public static void main(String[] args) {
        System.out.println(movingCount(7, 7, 3));
        System.out.println(movingCountBFS(7, 7, 3));
    }

    public static int movingCountBFS(int m, int n, int k) {
        int[][] directions = new int[][]{{0, 1}, {1, 0}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        int ans = 1;
        boolean[][] visited = new boolean[m][n];
        while (!queue.isEmpty()) {
            int[] xy = queue.poll();
            for (int[] direction : directions) {
                int i = xy[0] + direction[0], j = xy[1] + direction[1];
                if (i < 0 || i > m - 1 || j < 0 || j > n - 1 || visited[i][j] || sum(i) + sum(j) > k) continue;
                queue.offer(new int[]{i, j});
                visited[i][j] = true;
                ans++;
            }
        }
        return ans;
    }

    public static int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        return dfs(m, n, 0, 0, k, visited);
    }

    public static int dfs(int m, int n, int i, int j, int k, boolean[][] visited) {
        if (i < 0 || j < 0 || i > m - 1 || j > n - 1 || sum(i) + sum(j) > k || visited[i][j]) return 0;
        visited[i][j] = true;
        // 由于从（0，0）到 （m - 1， n - 1）故，只需考虑向右和向下
        return 1 + dfs(m, n, i + 1, j, k, visited) + dfs(m, n, i, j + 1, k, visited);
    }

    public static int sum(int i) {
        int ret = 0;
        while (i != 0) {
            ret += i % 10;
            i /= 10;
        }
        return ret;
    }
}
