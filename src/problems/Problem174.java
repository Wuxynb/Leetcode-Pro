package problems;

import java.util.Arrays;

/**
 * 174. 地下城游戏 (shopee)
 * 一些恶魔抓住了公主（P）并将她关在了地下城的右下角。地下城是由 M x N 个房间组成的二维网格。我们英勇的骑士（K）最初被安置在左上角的房间里，他必须
 * 穿过地下城并通过对抗恶魔来拯救公主。
 *
 * 骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。
 *
 * 有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；其他房间要么是空的（房间里的值为 0）
 * ，要么包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。
 *
 * 为了尽快到达公主，骑士决定每次只向右或向下移动一步。
 *
 *
 *
 * 编写一个函数来计算确保骑士能够拯救到公主所需的最低初始健康点数。
 *
 * 例如，考虑到如下布局的地下城，如果骑士遵循最佳路径 右 -> 右 -> 下 -> 下，则骑士的初始健康点数至少为 7。
 *
 * -2 (K) -3	3
 * -5	 -10	1
 * 10	  30   -5 (P)
 *
 * 链接：https://leetcode-cn.com/problems/dungeon-game/
 */
public class Problem174 {
    public static void main(String[] args) {
        System.out.println(calculateMinimumHP(new int[][]{
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}
        }));

        System.out.println(calculateMinimumHPDp(new int[][]{
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}
        }));
    }

    /*
        dp[i][j] 表示从坐标i，j到终点所需的最小hp值。
        dp[i][j] = max(min{dp[i + 1][j], dp[i][j + 1]}, 1}
        经验之谈，一般涉及到二维矩阵的 dp 问题都是从后往前推.
     */
    public static int calculateMinimumHPDp(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
        dp[m][n - 1] = dp[m - 1][n] = 1;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                dp[i][j] = Math.max(Math.min(dp[i][j + 1], dp[i + 1][j]) - dungeon[i][j], 1);
            }
        }
        return dp[0][0];
    }

    public static int calculateMinimumHP(int[][] dungeon) {
        Integer[][] cache = new Integer[dungeon.length][dungeon[0].length];
        return dfs(dungeon, 0, 0, cache) + 1;
    }

    /*
        f(i, j) -- 需消耗的hp值;
        f(i, j) = min{f(i, j + 1), f(i + 1, j)} - dungeon[i][j];
        f(i, j) < 0 --> f(i, j) = 0;
     */
    public static int dfs(int[][] dungeon, int i, int j, Integer[][] cache) {
        if (i >= dungeon.length || j >= dungeon[0].length) return Integer.MAX_VALUE;
        if (cache[i][j] != null) return cache[i][j];
        if (i == dungeon.length - 1 && j == dungeon[0].length - 1) return dungeon[i][j] > 0 ? 0 : -dungeon[i][j];
        int right = dfs(dungeon, i, j + 1, cache);
        int down = dfs(dungeon, i + 1, j, cache);
        int res = Math.min(right, down) - dungeon[i][j];
        res = Math.max(res, 0);
        cache[i][j] = res;
        return res;
    }
}
