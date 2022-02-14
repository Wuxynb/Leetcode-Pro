package problems;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 84. 柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 *
 *
 * 示例 1:
 *
 *
 *
 * 输入：heights = [2,1,5,6,2,3]
 * 输出：10
 * 解释：最大的矩形为图中红色区域，面积为 10
 * 示例 2：
 *
 *
 *
 * 输入： heights = [2,4]
 * 输出： 4
 *
 * 链接：https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
 */
public class Problem84 {
    public static void main(String[] args) {
        System.out.println(largestRectangleAreaPlus(new int[]{2, 1, 5, 6, 2, 3}));
        System.out.println(largestRectangleAreaPlus(new int[]{2, 1, 2}));
        System.out.println(largestRectangleAreaPlus(new int[]{5, 4, 1, 2}));
        System.out.println(largestRectangleAreaPlus(new int[]{4, 2, 0, 3, 2, 5}));
    }

    /*
        Brute force method (Inefficient algorithm)
     */
    public static int largestRectangleAreaBF(int[] heights) {
        int ans = 0;
        for (int i = 0; i < heights.length; i++) {
            int left = i;
            while (left > 0 && heights[left - 1] >= heights[i]) left--;
            int right = i;
            while (right < heights.length - 1 && heights[right + 1] >= heights[i]) right++;
            ans = Math.max(ans, (right - left + 1) * heights[i]);
        }

        return ans;
    }

    public static int largestRectangleArea_(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int ans = 0;
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                int curH = heights[stack.pop()];
                while (!stack.isEmpty() && curH ==heights[stack.peek()]) stack.pop();
                // 当栈为空时，表明当前元素为最小的元素（高度），故此时的宽度为整个数组的长度
                int curW = stack.isEmpty() ? i : i - stack.peek() - 1;
                ans = Math.max(ans, curW * curH);
            }
            stack.push(i);
        }
        int len = heights.length;
        // 当栈中还有元素时，继续计算
        while (!stack.isEmpty()) {
            int curH = heights[stack.pop()];
            while (!stack.isEmpty() && curH == heights[stack.peek()]) stack.pop();
            int curW = stack.isEmpty() ? len : len - stack.peek() - 1;
            ans = Math.max(ans, curH * curW);
        }

        return ans;
    }

    public static int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int ans = 0;
        for (int i = 0; i <= heights.length; i++) {
            while (!stack.isEmpty() && (i == heights.length || heights[i] < heights[stack.peek()])) {
                int curH = heights[stack.pop()];
                // 当栈为空时，表明当前元素为最小的元素（高度），故此时的宽度为整个数组的长度
                int curW = stack.isEmpty() ? i : i - stack.peek() - 1;
                ans = Math.max(ans, curW * curH);
            }
            if (i  < heights.length) stack.push(i);
        }
        return ans;
    }

    public static int largestRectangleAreaPlus(int[] heights) {
        int top = -1, ans = 0;
        int[] stack = new int[heights.length];
        for (int i = 0; i <= heights.length; i++) {
            while (top != -1 && (i == heights.length || heights[i] < heights[stack[top]])) {
                int curH = heights[stack[top--]];
                int curW = top != -1 ? i - stack[top] - 1 : i;
                ans = Math.max(ans, curH * curW);
            }
            if (i < heights.length) stack[++top] = i;
        }

        return ans;
    }
}
