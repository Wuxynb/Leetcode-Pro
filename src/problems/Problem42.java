package problems;

import java.util.Stack;

/**
 * 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 示例 2：
 *
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 *
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water/
 */
public class Problem42 {

    public static void main(String[] args) {
        System.out.println(trapBF1(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(trapDP1(new int[]{4, 2, 0, 3, 2, 5}));
        System.out.println(trapDP2(new int[]{4, 2, 0, 3, 2, 5}));
        System.out.println(trapDP2Plus(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(trapMonotoneStack(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

    public static int trapBF1(int[] height) {
        int ans = 0;
        int size = height.length;
        for (int i = 1; i < size - 1; i++) {
            int max_left = 0, max_right = 0;
            for (int j = i; j >= 0; j--) { //Search the left part for max bar size
                max_left = Math.max(max_left, height[j]);
            }
            for (int j = i; j < size; j++) { //Search the right part for max bar size
                max_right = Math.max(max_right, height[j]);
            }
            ans += Math.min(max_left, max_right) - height[i];
        }
        return ans;
    }

    public static int trapBF2(int[] height) {
        int ans = 0, leftMax = 0, rightMax = 0, max = 0;
        for (int i = 1; i < height.length - 1; i++) {
            leftMax = rightMax = 0;
            for (int j = i; j >= 0; j--) {
                leftMax = Math.max(leftMax, height[j]);
            }
            for (int j = i; j < height.length; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }
            max = Math.min(leftMax, rightMax);
            if (max >= height[i]) ans += max - height[i];
        }

        return ans;
    }

    public static int trapDP1(int[] height) {
        if (height == null || height.length == 0)
            return 0;
        int ans = 0;
        int size = height.length;
        int[] left_max = new int[size];
        int[] right_max = new int[size];
        left_max[0] = height[0];
        for (int i = 1; i < size; i++) {
            left_max[i] = Math.max(height[i], left_max[i - 1]);
        }
        right_max[size - 1] = height[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            right_max[i] = Math.max(height[i], right_max[i + 1]);
        }
        for (int i = 1; i < size - 1; i++) {
            ans += Math.min(left_max[i], right_max[i]) - height[i];
        }
        return ans;
    }

    // dynamic programming
    public static int trapDP2(int[] height) {
        int ans = 0, leftMax = 0, rightMax = 0;
        int[] leftMaxs = new int[height.length];
        int[] rightMaxs = new int[height.length];
        leftMaxs[0] = height[0];
        rightMaxs[height.length - 1] = height[height.length - 1];
        // search for the left max value of each elem
        for (int i = 1; i < height.length; i++) {
            leftMax = Math.max(leftMaxs[i - 1], height[i]);
            leftMaxs[i] = leftMax;
        }

        for (int i = height.length - 2; i >= 0; i--) {
            rightMax = Math.max(rightMaxs[i + 1], height[i]);
            rightMaxs[i] = rightMax;
        }

        for (int i = 1; i < height.length - 1; i++) {
            int max = Math.min(leftMaxs[i], rightMaxs[i]);
            ans += max -height[i];
        }

        return ans;
    }

    // dynamic programming
    public static int trapDP2Plus(int[] height) {
        int ans = 0, leftMax = 0, rightMax = 0;
        int[] leftMaxs = new int[height.length];
        int[] rightMaxs = new int[height.length];
        rightMaxs[height.length - 1] = height[height.length - 1];
        leftMaxs[0] = height[0];
        // search for the left max value of each elem
        for (int i = 1; i < height.length; i++) {
            leftMax = Math.max(leftMaxs[i - 1], height[i]);
            leftMaxs[i] = leftMax;
            int j = height.length - i - 1;
            rightMax = Math.max(rightMaxs[j + 1], height[j]);
            rightMaxs[j] = rightMax;
        }

        for (int i = 1; i < height.length - 1; i++) {
            int max = Math.min(leftMaxs[i], rightMaxs[i]);
            ans += max -height[i];
        }

        return ans;
    }

    // double pointer
    public static int trap(int[] height) {
        int left = 0, right = height.length - 1, leftMax = 0, rightMax = 0;
        int ans = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (leftMax <= rightMax) {
                ans += leftMax - height[left++];
            } else {
                ans += rightMax - height[right--];
            }
        }

        return ans;
    }

    // monotone stack
    public static int trapMonotoneStack(int[] height) {
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                ans += (i - left - 1) * (Math.min(height[i], height[left]) - height[top]);
            }
            stack.push(i);
        }

        return ans;
    }
}
