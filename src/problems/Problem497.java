package problems;

import java.util.Arrays;
import java.util.Random;

/**
 * 497. 非重叠矩形中的随机点
 * 给定一个由非重叠的轴对齐矩形的数组 rects ，其中 rects[i] = [ai, bi, xi, yi] 表示 (ai, bi) 是第 i 个矩形的左下角点，(xi, yi) 是第 i 个矩形的右上角角点。设计一个算法来随机挑选一个被某一矩形覆盖的整数点。矩形周长上的点也算做是被矩形覆盖。所有满足要求的点必须等概率被返回。
 *
 * 在一个给定的矩形覆盖的空间内任何整数点都有可能被返回。
 *
 * 请注意 ，整数点是具有整数坐标的点。
 *
 * 实现 Solution 类:
 *
 * Solution(int[][] rects) 用给定的矩形数组 rects 初始化对象。
 * int[] pick() 返回一个随机的整数点 [u, v] 在给定的矩形所覆盖的空间内。
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入:
 * ["Solution","pick","pick","pick","pick","pick"]
 * [[[[-2,-2,-1,-1],[1,0,3,0]]],[],[],[],[],[]]
 * 输出:
 * [null,[-1,-2],[2,0],[-2,-1],[3,0],[-2,-2]
 *
 * 解释：
 * Solution solution = new Solution([[-2, -2, 1, 1], [2, 2, 4, 6]]);
 * solution.pick(); // 返回 [1, -2]
 * solution.pick(); // 返回 [1, -1]
 * solution.pick(); // 返回 [-1, -2]
 * solution.pick(); // 返回 [-2, -2]
 * solution.pick(); // 返回 [0, 0]
 *
 *
 * 提示：
 *
 * 1 <= rects.length <= 100
 * rects[i].length == 4
 * -109 <= ai < xi <= 109
 * -109 <= bi < yi <= 109
 * xi - ai <= 2000
 * yi - bi <= 2000
 * 所有的矩形不重叠。
 * pick 最多被调用 104 次。
 *
 * link: https://leetcode.cn/problems/random-point-in-non-overlapping-rectangles/
 */
public class Problem497 {
    public static void main(String[] args) {
        Solution obj = new Solution(new int[][]{{-2, -2, 1, 1}, {2, 2, 4, 6}});
        System.out.println(Arrays.toString(obj.pick()));
    }

    /**
     * Your Solution object will be instantiated and called as such:
     * Solution* obj = new Solution(rects);
     * vector<int> param_1 = obj->pick();
     *
     * ["Solution","pick","pick","pick","pick","pick"]
     * [[[[-2,-2,1,1],[2,2,4,6]]],[],[],[],[],[]]
     */
    static class Solution {
        int[][] rects;
        Random random = new Random();
        int[] sums;
        int n;

        public Solution(int[][] rects) {
            this.rects = rects;
            this.n = rects.length;
            sums = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                // 定义矩阵内整数点的数量为「面积」
                sums[i] = sums[i - 1] + (rects[i - 1][2] - rects[i - 1][0] + 1) * (rects[i - 1][3] - rects[i - 1][1] + 1);
            }
        }

        public int[] pick() {
            int area = random.nextInt(sums[n]) + 1, l = 0, r = n;
            while (l < r) {
                int mid = l + r >> 1;
                if (sums[mid] >= area) r = mid;
                else l = mid + 1;
            }
            int len1 = random.nextInt(rects[r - 1][2] - rects[r - 1][0] + 1), len2 = random.nextInt(rects[r - 1][3] - rects[r - 1][1] + 1);
            return new int[]{rects[r - 1][0] + len1, rects[r - 1][1] + len2};
        }
    }
}
