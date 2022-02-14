package problems;

import java.util.Map;

/**
 * 149. 直线上最多的点数
 * 给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：points = [[1,1],[2,2],[3,3]]
 * 输出：3
 * 示例 2：
 *
 *
 * 输入：points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * 输出：4
 *
 * 链接：https://leetcode-cn.com/problems/max-points-on-a-line/
 */
public class Problem149 {
    public static void main(String[] args) {
        System.out.println(maxPoints(new int[][]{{-6, -1}, {3, 1}, {12, 3}}));
        System.out.println(maxPoints(new int[][]{{1, 1}, {2, 2}, {3, 3}}));
        System.out.println(maxPoints(new int[][]{{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}}));
        System.out.println(maxPoints(new int[][]{{0, 0}, {4, 5}, {7, 8}, {8, 9}, {5, 6}, {3, 4}, {1, 1}}));
    }

    public static int maxPoints(int[][] points) {
        if (points.length <= 2) return points.length;
        int ans = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int x1 = points[i][0], y1 = points[i][1], x2 = points[j][0], y2 = points[j][1];
                int count = 2;
                for (int k = j + 1; k < points.length; k++) {
                    int x = points[k][0], y = points[k][1];
                    if ((y - y1) * (x2 - x1) == (y2 - y1) * (x - x1)) count++;
                }
                ans = Math.max(ans, count);
            }
        }
        return ans;
    }
}
