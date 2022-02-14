package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 字节
 * 56. 合并区间
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 *
 *
 *
 * 示例 1：
 *
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2：
 *
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 * 链接：https://leetcode-cn.com/problems/merge-intervals/
 */
public class Problem56 {
    public static void main(String[] args) {
//        int[][] nums = new int[][] {{3, 6}, {5, 10}, {1, 3}, {15, 18}};
        int[][] nums = new int[][] {{2, 3}, {2, 2}, {3, 3}, {1, 3}, {5, 7}, {2, 2}, {4, 6}};
//        int[][] nums = new int[][] {{1, 4}, {2, 3}};
        System.out.println(Arrays.deepToString(merge_(nums)));
        // Bubble sort
/*        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j][0] > nums[j + 1][0]) {
                    int[] temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }*/
        // Quick sort
//        Arrays.sort(nums, Comparator.comparingInt(a -> a[0]));
//        System.out.println(Arrays.deepToString(nums));
//        System.out.println(Arrays.deepToString(mergeHelper(nums)));
    }

    public static int[][] mergeHelper(int[][] intervals) {
        List<int[]> lists = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            if (i != intervals.length - 1 && intervals[i + 1][0] <= intervals[i][1]) {
                lists.add(new int[]{intervals[i][0], Math.max(intervals[i][1], intervals[i + 1][1])});
                i++;
            } else {
                lists.add(new int[]{intervals[i][0], intervals[i][1]});
            }
        }
        int[][] newNums = lists.toArray(new int[lists.size()][]);

        if (lists.size() == intervals.length) return newNums;
        else return mergeHelper(newNums);
    }

    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int l = intervals[i][0], r = intervals[i][1];
            if (i == 0 || list.get(list.size() - 1)[1] < l) {
                list.add(new int[]{l, r});
            } else {
                list.get(list.size() - 1)[1] = Math.max(list.get(list.size() - 1)[1], r);
            }
        }

        return list.toArray(new int[list.size()][]);
    }

    /*
     * 排序 + 双指针
     */
    public static int[][] merge_(int[][] intervals) {
        if (intervals.length == 0) return intervals;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> list = new ArrayList<>();
        int left = intervals[0][0], right = intervals[0][1]; // 令第一个区间为开始区间
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] > right) { // 如果下一个区间在开始区间右侧且无交集
                list.add(new int[]{left, right}); // 将开始区间放入list
                left = intervals[i][0]; // 更新开始区间为当前区间
                right = intervals[i][1];
            } else { // 如果有交集，更新开始区间为原开始区间和当前区间两个区间的并集
                left = Math.min(left, intervals[i][0]);
                right = Math.max(right, intervals[i][1]);
            }
            if (i == intervals.length - 1) list.add(new int[]{left, right}); // 如果到了最后一个区间，将最后的开始区间值加入
        }

        return list.toArray(new int[list.size()][]);
    }
}
