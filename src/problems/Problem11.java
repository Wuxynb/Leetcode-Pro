package problems;

/**
 * 11. 盛最多水的容器
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * 示例 2：
 *
 * 输入：height = [1,1]
 * 输出：1
 * 示例 3：
 *
 * 输入：height = [4,3,2,1,4]
 * 输出：16
 * 示例 4：
 *
 * 输入：height = [1,2,1]
 * 输出：2
 *
 * 链接：https://leetcode-cn.com/problems/container-with-most-water/
 */
public class Problem11 {

    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{4, 3, 2, 1, 4}));
        System.out.println(maxAreaOp(new int[]{4, 3, 2, 1, 4}));
        System.out.println(maxAreaPlus(new int[]{4, 3, 2, 1, 4}));
    }

    public static int maxArea(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length - 1; i++) {
            if (height[i] > max / (height.length - i - 1)) {
                for (int j = i + 1; j < height.length; j++) {
                    max = Math.max((j - i) * Math.min(height[i], height[j]), max);
                }
            }
        }

        return max;
    }

    public static int maxAreaOp(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length - 1; i++) {
            if (height[i] == 0) continue;
            for (int j = max / height[i] + i + 1; j < height.length; j++) {
                max = Math.max(Math.min(height[i], height[j]) * (j - i), max);
            }
        }

        return max;
    }

    /*
        随着第一个元素的递增，第二个元素是递减的，那么就可以使用双指针的方法
     */
    public static int maxAreaPlus(int[] height) {
        int max = 0;
        int i = 0, j = height.length - 1;
        while (i <= j) {
            max = Math.max(Math.min(height[i], height[j]) * (j - i), max);
            if (height[i] < height[j]) i++;
            else j--;
        }

        return max;
    }
}
