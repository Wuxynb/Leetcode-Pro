package problems;

import java.util.Arrays;

/**
 * 436. 寻找右区间
 * 给你一个区间数组 intervals ，其中 intervals[i] = [starti, endi] ，且每个 starti 都 不同 。
 *
 * 区间 i 的 右侧区间 可以记作区间 j ，并满足 startj >= endi ，且 startj 最小化 。
 *
 * 返回一个由每个区间 i 的 右侧区间 的最小起始位置组成的数组。如果某个区间 i 不存在对应的 右侧区间 ，则下标 i 处的值设为 -1 。
 *
 *
 * 示例 1：
 *
 * 输入：intervals = [[1,2]]
 * 输出：[-1]
 * 解释：集合中只有一个区间，所以输出-1。
 * 示例 2：
 *
 * 输入：intervals = [[3,4],[2,3],[1,2]]
 * 输出：[-1,0,1]
 * 解释：对于 [3,4] ，没有满足条件的“右侧”区间。
 * 对于 [2,3] ，区间[3,4]具有最小的“右”起点;
 * 对于 [1,2] ，区间[2,3]具有最小的“右”起点。
 * 示例 3：
 *
 * 输入：intervals = [[1,4],[2,3],[3,4]]
 * 输出：[-1,2,-1]
 * 解释：对于区间 [1,4] 和 [3,4] ，没有满足条件的“右侧”区间。
 * 对于 [2,3] ，区间 [3,4] 有最小的“右”起点。
 *
 *
 * 提示：
 *
 * 1 <= intervals.length <= 2 * 104
 * intervals[i].length == 2
 * -106 <= starti <= endi <= 106
 * 每个间隔的起点都 不相同
 *
 * link: https://leetcode.cn/problems/find-right-interval/
 */
public class Problem436 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(findRightInterval(new int[][]{{3, 4}, {2, 3}, {1, 2}})));
        System.out.println(Arrays.toString(findRightInterval(new int[][]{{1, 4}, {2, 3}, {3, 4}})));
    }

    public static int[] findRightInterval(int[][] intervals) {
        int m = intervals.length, n = intervals[0].length;
        int[][] starts = new int[m][n];
        for (int i = 0; i < m; i++) starts[i] = new int[]{intervals[i][0], i};
        Arrays.sort(starts, (x, y) -> x[0] - y[0]);
        int[] ret = new int[m];
        for (int i = 0; i < m; i++) {
            int end = intervals[i][1], left = 0, right = m - 1;
            while (left < right) {
                int mid = left + right >> 1;
                if (end > starts[mid][0]) left = mid + 1;
                else right = mid;
            }
            ret[i] = starts[right][0] < end ? -1 : starts[right][1];
        }
        return ret;
    }
}
