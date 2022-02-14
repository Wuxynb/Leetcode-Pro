package problems;

import java.util.*;

/**
 * 2013. 检测正方形
 * 给你一个在 X-Y 平面上的点构成的数据流。设计一个满足下述要求的算法：
 *
 * 添加 一个在数据流中的新点到某个数据结构中。可以添加 重复 的点，并会视作不同的点进行处理。
 * 给你一个查询点，请你从数据结构中选出三个点，使这三个点和查询点一同构成一个 面积为正 的 轴对齐正方形 ，统计 满足该要求的方案数目。
 * 轴对齐正方形 是一个正方形，除四条边长度相同外，还满足每条边都与 x-轴 或 y-轴 平行或垂直。
 *
 * 实现 DetectSquares 类：
 *
 * DetectSquares() 使用空数据结构初始化对象
 * void add(int[] point) 向数据结构添加一个新的点 point = [x, y]
 * int count(int[] point) 统计按上述方式与点 point = [x, y] 共同构造 轴对齐正方形 的方案数。
 *
 *
 * 示例：
 *
 *
 * 输入：
 * ["DetectSquares", "add", "add", "add", "count", "count", "add", "count"]
 * [[], [[3, 10]], [[11, 2]], [[3, 2]], [[11, 10]], [[14, 8]], [[11, 2]], [[11, 10]]]
 * 输出：
 * [null, null, null, null, 1, 0, null, 2]
 *
 * 解释：
 * DetectSquares detectSquares = new DetectSquares();
 * detectSquares.add([3, 10]);
 * detectSquares.add([11, 2]);
 * detectSquares.add([3, 2]);
 * detectSquares.count([11, 10]); // 返回 1 。你可以选择：
 *                                //   - 第一个，第二个，和第三个点
 * detectSquares.count([14, 8]);  // 返回 0 。查询点无法与数据结构中的这些点构成正方形。
 * detectSquares.add([11, 2]);    // 允许添加重复的点。
 * detectSquares.count([11, 10]); // 返回 2 。你可以选择：
 *                                //   - 第一个，第二个，和第三个点
 *                                //   - 第一个，第三个，和第四个点
 *
 *
 * 提示：
 *
 * point.length == 2
 * 0 <= x, y <= 1000
 * 调用 add 和 count 的 总次数 最多为 5000
 *
 * link: https://leetcode-cn.com/problems/detect-squares/
 */
public class Problem2013 {
    public static void main(String[] args) {
        DetectSquares detectSquares = new DetectSquares();
        detectSquares.add(new int[]{3, 10});
        detectSquares.add(new int[]{11, 2});
        detectSquares.add(new int[]{3, 2});
        detectSquares.add(new int[]{11, 10});
        System.out.println(detectSquares.count(new int[]{11, 10})); // 返回 1 。你可以选择：
        System.out.println(detectSquares.count(new int[]{14, 8}));  // 返回 0 。查询点无法与数据结构中的这些点构成正方形。
        detectSquares.add(new int[]{11, 2});    // 允许添加重复的点。
        System.out.println(detectSquares.count(new int[]{11, 10})); // 返回 2 。你可以选择：
    }

    /**
     * Your DetectSquares object will be instantiated and called as such:
     * DetectSquares obj = new DetectSquares();
     * obj.add(point);
     * int param_2 = obj.count(point);
     */
    static class DetectSquares {
        // 一个map时，<x, <y, <cnt>>
        // Map<Integer, Map<Integer, Integer>> map;
        Map<Integer, List<Integer>> xy;
        Map<Integer, List<Integer>> yx;
        Map<Long, Integer> count;

        public DetectSquares() {
            xy = new HashMap<>();
            yx = new HashMap<>();
            count = new HashMap<>();
        }

        public void add(int[] point) {
            long hash = point[0] * 1311L + point[1];
            count.put(hash, count.getOrDefault(hash, 0) + 1);
            if (count.get(hash) > 1) return;
            xy.putIfAbsent(point[0], new ArrayList<>());
            xy.get(point[0]).add(point[1]);
            yx.putIfAbsent(point[1], new ArrayList<>());
            yx.get(point[1]).add(point[0]);
        }

        public int count(int[] point) {
            List<Integer> y = xy.get(point[0]); // x坐标相同，y坐标不同
            List<Integer> x = yx.get(point[1]); // y坐标相同，x坐标不同
            if (x == null || y == null) return 0;
            int cnt = 0;
            for (Integer xa : x) {
                for (Integer ya : y) {
                    if (count.containsKey(xa * 1311L + ya)) { // 存在第四个点时
                        //todo: 如果长宽不相等，或者第四个点的y值对于第一个点的y值，即重叠
                        if (Math.abs(xa - point[0]) != Math.abs(ya - point[1]) || ya == point[1]) continue;
                        cnt += count.get(xa * 1311L + point[1]) * // 第二个点
                                count.get(point[0] * 1311L + ya) * // 第三个点
                                count.get(xa * 1311L + ya); // 第四个点
                    }
                }
            }
            return cnt;
        }
    }
}
