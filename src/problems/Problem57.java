package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 57. 插入区间
 * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
 *
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 *
 *
 *
 * 示例 1：
 *
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 * 示例 2：
 *
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 * 示例 3：
 *
 * 输入：intervals = [], newInterval = [5,7]
 * 输出：[[5,7]]
 * 示例 4：
 *
 * 输入：intervals = [[1,5]], newInterval = [2,3]
 * 输出：[[1,5]]
 * 示例 5：
 *
 * 输入：intervals = [[1,5]], newInterval = [2,7]
 * 输出：[[1,7]]
 *
 * 链接：https://leetcode-cn.com/problems/insert-interval/
 */
public class Problem57 {
    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1, 5}};
        int[] newInterval = new int[]{0, 3};
        System.out.println(Arrays.deepToString(insertPlus(intervals, newInterval)));
    }

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) return new int[][]{newInterval};
        int[][] newIntervals = new int[intervals.length + 1][2];
        boolean isInsert = false;
        for (int i = 0; i < intervals.length; i++) {
            if (!isInsert && newInterval[0] <= intervals[i][0]) {
                newIntervals[i] = Arrays.copyOf(newInterval, 2);
                newIntervals[i + 1] = Arrays.copyOf(intervals[i], 2);
                isInsert = true;
            } else {
                if (!isInsert) newIntervals[i] = Arrays.copyOf(intervals[i], 2);
                else newIntervals[i + 1] = Arrays.copyOf(intervals[i], 2);
            }
        }
        if (!isInsert) newIntervals[newIntervals.length - 1] = newInterval;

        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < newIntervals.length; i++) {
            int l = newIntervals[i][0], r = newIntervals[i][1];
            if (i == 0 || list.get(list.size() - 1)[1] < l) {
                list.add(new int[]{l, r});
            } else {
                list.get(list.size() - 1)[1] = Math.max(list.get(list.size() - 1)[1], r);
            }
        }

        return list.toArray(new int[list.size()][]);
    }

    /*
     * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
     * 输出：[[1,5],[6,9]]
     */
    public static int[][] insert_(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) return new int[][]{newInterval};
        boolean isInsert = false;
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int l = intervals[i][0], r = intervals[i][1];
            if (!isInsert && newInterval[0] < l) {
                if (i == 0 || list.get(list.size() - 1)[1] < newInterval[0]) {
                    list.add(newInterval);
                } else {
                    list.get(list.size() - 1)[1] = Math.max(list.get(list.size() - 1)[1], newInterval[1]);
                }
                isInsert = true;
                i = i - 1;
            } else {
               if ((i == 0 && !isInsert) || list.get(list.size() - 1)[1] < l) {
                   list.add(new int[]{l, r});
               } else {
                   list.get(list.size() - 1)[1] = Math.max(list.get(list.size() - 1)[1], r);
               }
            }
        }

        if (!isInsert) {
            if (list.get(list.size() - 1)[1] < newInterval[0]) list.add(newInterval);
            else list.get(list.size() - 1)[1] = Math.max(list.get(list.size() - 1)[1], newInterval[1]);
        }

        return list.toArray(new int[list.size()][]);
    }

    /*
        输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
        输出：[[1,2],[3,10],[12,16]]
        解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10]重叠。
     */
    public static int[][] insertPlus(int[][] intervals, int[] newInterval) {
        int left = newInterval[0], right = newInterval[1];
        List<int[]> list = new ArrayList<>();
        boolean isInsert = false;
        for (int[] interval : intervals) {
            // 在插入区间的右侧且无交集
            if (interval[0] > right) {
                if (!isInsert) {
                    list.add(newInterval);
                    isInsert = true;
                }
                list.add(interval);
            } else if (interval[1] < left) { // 在插入区间的左侧且无交集
                list.add(interval);
            } else { // 和插入区间有交集
                // 更新最新的插入区间
                left = Math.max(interval[0], left);
                right = Math.max(interval[1], right);
            }
        }
        if (!isInsert) list.add(new int[]{left, right});

        return list.toArray(new int[list.size()][]);
    }
}
