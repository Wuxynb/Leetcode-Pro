package problems;

import java.util.*;

/**
 * 675. 为高尔夫比赛砍树
 * 你被请来给一个要举办高尔夫比赛的树林砍树。树林由一个 m x n 的矩阵表示， 在这个矩阵中：
 * <p>
 * 0 表示障碍，无法触碰
 * 1 表示地面，可以行走
 * 比 1 大的数 表示有树的单元格，可以行走，数值表示树的高度
 * 每一步，你都可以向上、下、左、右四个方向之一移动一个单位，如果你站的地方有一棵树，那么你可以决定是否要砍倒它。
 * <p>
 * 你需要按照树的高度从低向高砍掉所有的树，每砍过一颗树，该单元格的值变为 1（即变为地面）。
 * <p>
 * 你将从 (0, 0) 点开始工作，返回你砍完所有树需要走的最小步数。 如果你无法砍完所有的树，返回 -1 。
 * <p>
 * 可以保证的是，没有两棵树的高度是相同的，并且你至少需要砍倒一棵树。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：forest = [[1,2,3],[0,0,4],[7,6,5]]
 * 输出：6
 * 解释：沿着上面的路径，你可以用 6 步，按从最矮到最高的顺序砍掉这些树。
 * 示例 2：
 * <p>
 * <p>
 * 输入：forest = [[1,2,3],[0,0,0],[7,6,5]]
 * 输出：-1
 * 解释：由于中间一行被障碍阻塞，无法访问最下面一行中的树。
 * 示例 3：
 * <p>
 * 输入：forest = [[2,3,4],[0,0,5],[8,7,6]]
 * 输出：6
 * 解释：可以按与示例 1 相同的路径来砍掉所有的树。
 * (0,0) 位置的树，可以直接砍去，不用算步数。
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == forest.length
 * n == forest[i].length
 * 1 <= m, n <= 50
 * 0 <= forest[i][j] <= 109
 * <p>
 * link: https://leetcode.cn/problems/cut-off-trees-for-golf-event/
 */
public class Problem675 {
    public static void main(String[] args) {
        System.out.println(cutOffTree(Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(0, 0, 4),
                Arrays.asList(7, 6, 5))));

        System.out.println(cutOffTree(Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(0, 0, 0),
                Arrays.asList(7, 6, 5))));

        System.out.println(cutOffTree(Arrays.asList(
                Arrays.asList(2, 3, 4),
                Arrays.asList(0, 0, 5),
                Arrays.asList(8, 7, 6))));

        System.out.println(cutOffTree(Arrays.asList(
                Collections.singletonList(0),
                Collections.singletonList(0),
                Collections.singletonList(6014))));

        System.out.println(cutOffTree(Arrays.asList(
                Arrays.asList(54581641, 64080174, 24346381, 69107959),
                Arrays.asList(86374198, 61363882, 68783324, 79706116),
                Arrays.asList(668150, 92178815, 89819108, 94701471),
                Arrays.asList(83920491, 22724204, 46281641, 47531096),
                Arrays.asList(89078499, 18904913, 25462145, 60813308))));
    }

    static int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static int cutOffTree(List<List<Integer>> forest) {
        List<int[]> trees = new ArrayList<>();
        int m = forest.size(), n = forest.get(0).size();
        int[][] grid = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if ((grid[i][j] = forest.get(i).get(j)) > 1) trees.add(new int[]{i, j});
            }
        }
        if (grid[0][0] == 0) return -1;
        trees.sort((x, y) -> grid[x[0]][x[1]] - grid[y[0]][y[1]]);
        int x = 0, y = 0, ans = 0;
        for (int[] tree : trees) {
            int i = tree[0], j = tree[1];
            int d = bfs(grid, x, y, i, j);
            if (d == -1) return -1;
            ans += d;
            x = i;
            y = j;
        }
        return ans;
    }

    public static int bfs(int[][] grid, int x, int y, int i, int j) {
        if (x == i && y == j) return 0;
        int dist = 0, m = grid.length, n = grid[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[m][n];
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        while (!queue.isEmpty()) {
            for (int k = 0, size = queue.size(); k < size; k++) {
                int[] cur = queue.poll();
                for (int[] dir : dirs) {
                    int u = cur[0] + dir[0], v = cur[1] + dir[1];
                    if (u == i && v == j) return dist + 1;
                    if (u >= 0 && u < m && v >= 0 && v < n && grid[u][v] != 0 && !visited[u][v]) {
                        queue.offer(new int[]{u, v});
                        visited[u][v] = true;
                    }
                }
            }
            dist++;
        }
        return -1;
    }
}
