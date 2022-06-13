package problems;

/**
 * 2132. 用邮票贴满网格图
 * 给你一个 m x n 的二进制矩阵 grid ，每个格子要么为 0 （空）要么为 1 （被占据）。
 * <p>
 * 给你邮票的尺寸为 stampHeight x stampWidth 。我们想将邮票贴进二进制矩阵中，且满足以下 限制 和 要求 ：
 * <p>
 * 覆盖所有 空 格子。
 * 不覆盖任何 被占据 的格子。
 * 我们可以放入任意数目的邮票。
 * 邮票可以相互有 重叠 部分。
 * 邮票不允许 旋转 。
 * 邮票必须完全在矩阵 内 。
 * 如果在满足上述要求的前提下，可以放入邮票，请返回 true ，否则返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：grid = [[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0]], stampHeight = 4, stampWidth = 3
 * 输出：true
 * 解释：我们放入两个有重叠部分的邮票（图中标号为 1 和 2），它们能覆盖所有与空格子。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：grid = [[1,0,0,0],[0,1,0,0],[0,0,1,0],[0,0,0,1]], stampHeight = 2, stampWidth = 2
 * 输出：false
 * 解释：没办法放入邮票覆盖所有的空格子，且邮票不超出网格图以外。
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[r].length
 * 1 <= m, n <= 105
 * 1 <= m * n <= 2 * 105
 * grid[r][c] 要么是 0 ，要么是 1 。
 * 1 <= stampHeight, stampWidth <= 105
 * <p>
 * link: https://leetcode-cn.com/problems/stamping-the-grid/
 */
@SuppressWarnings("all")
public class Problem2132 {
    public static void main(String[] args) {
        System.out.println(possibleToStamp(new int[][]{
                {1, 0, 0, 0},
                {1, 0, 0, 0},
                {1, 0, 0, 0},
                {1, 0, 0, 0},
                {1, 0, 0, 0}}, 4, 3));
    }

    public static boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
        int m = grid.length, n = grid[0].length, w = stampWidth, h = stampHeight;
        int[][] sums = new int[m + 1][n + 1], diffs = new int[m + 2][n + 2]; // 前缀和数组、差分数组
        for (int i = 1; i <= m; i++) { // 求 grid 数组的 （初始化）前缀和数组
            for (int j = 1; j <= n; j++) {
                sums[i][j] = sums[i - 1][j] + sums[i][j - 1] - sums[i - 1][j - 1] + grid[i - 1][j - 1];
            }
        }
        // sums: [[0, 0, 0, 0, 0], [0, 1, 1, 1, 1], [0, 2, 2, 2, 2], [0, 3, 3, 3, 3], [0, 4, 4, 4, 4], [0, 5, 5, 5, 5]]
        for (int i = h; i <= m; i++) { // 利用差分标记可放置的点
            for (int j = w; j <= n; j++) { // 第一轮：sums[h][w]=sums[0][w]-sums[h][0]+sums[0][0] -- 画图理解
                int cnt = sums[i][j] - sums[i - h][j] - sums[i][j - w] + sums[i - h][j - w]; // see P304
                if (cnt == 0) { // 可以放置
                    diffs[i - h + 1][j - w + 1]++;
                    diffs[i - h + 1][j + 1]--;
                    diffs[i + 1][j - w + 1]--;
                    diffs[i + 1][j + 1]++;
                }
            }
        }
        for (int i = 1; i <= m; i++) { // 利用前缀和 与 差分数组还原出填充的数组
            for (int j = 1; j <= n; j++) {
                diffs[i][j] += diffs[i - 1][j] + diffs[i][j - 1] - diffs[i - 1][j - 1];
                if (grid[i - 1][j - 1] == 0 && diffs[i][j] == 0) return false;
            }
        }

        return true;
    }
}
