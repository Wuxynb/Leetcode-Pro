package problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 496. 下一个更大元素 I
 * nums1 中数字 x 的 下一个更大元素 是指 x 在 nums2 中对应位置 右侧 的 第一个 比 x 大的元素。
 *
 * 给你两个 没有重复元素 的数组 nums1 和 nums2 ，下标从 0 开始计数，其中nums1 是 nums2 的子集。
 *
 * 对于每个 0 <= i < nums1.length ，找出满足 nums1[i] == nums2[j] 的下标 j ，并且在 nums2 确定 nums2[j] 的 下一个更大元素 。如果不存在下一个更大元素，那么本次查询的答案是 -1 。
 *
 * 返回一个长度为 nums1.length 的数组 ans 作为答案，满足 ans[i] 是如上所述的 下一个更大元素 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums1 = [4,1,2], nums2 = [1,3,4,2].
 * 输出：[-1,3,-1]
 * 解释：nums1 中每个值的下一个更大元素如下所述：
 * - 4 ，用加粗斜体标识，nums2 = [1,3,4,2]。不存在下一个更大元素，所以答案是 -1 。
 * - 1 ，用加粗斜体标识，nums2 = [1,3,4,2]。下一个更大元素是 3 。
 * - 2 ，用加粗斜体标识，nums2 = [1,3,4,2]。不存在下一个更大元素，所以答案是 -1 。
 * 示例 2：
 *
 * 输入：nums1 = [2,4], nums2 = [1,2,3,4].
 * 输出：[3,-1]
 * 解释：nums1 中每个值的下一个更大元素如下所述：
 * - 2 ，用加粗斜体标识，nums2 = [1,2,3,4]。下一个更大元素是 3 。
 * - 4 ，用加粗斜体标识，nums2 = [1,2,3,4]。不存在下一个更大元素，所以答案是 -1 。
 *
 *
 * 提示：
 *
 * 1 <= nums1.length <= nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 104
 * nums1和nums2中所有整数 互不相同
 * nums1 中的所有整数同样出现在 nums2 中
 *
 * 链接：https://leetcode-cn.com/problems/next-greater-element-i/
 */
public class Problem496 {
    /** 求下一个更大的元素，就是用单调栈解 **/
    public static void main(String[] args) {
        System.out.println(Arrays.toString(nextGreaterElement(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2})));
        System.out.println(Arrays.toString(nextGreaterElementOptimize(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2})));
    }

    // 1 3 4 2
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[nums1.length];
        for (int j : nums2) {
            while (!stack.isEmpty() && stack.peek() < j) map.put(stack.pop(), j);
            stack.push(j);
        }
        for (int i = 0; i < nums1.length; i++) ans[i] = map.getOrDefault(nums1[i], -1);
        return ans;
    }

    // [4, 1, 2]   [1, 3, 4, 2]
    public static int[] nextGreaterElementOptimize(int[] nums1, int[] nums2) {
        int[] hash = new int[10001], stack = new int[nums2.length], ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) hash[nums1[i]] = i + 1;
        int top = -1;
        for (int i = nums2.length - 1; i >= 0; --i) {
            while (top != -1 && stack[top] < nums2[i]) top--; // 如果当前元素大于栈顶元素，则将栈顶元素弹出
            if (hash[nums2[i]] != 0) ans[hash[nums2[i]] - 1] = top == -1 ? -1 : nums2[top];
            stack[++top] = nums2[i];
        }
        return ans;
    }

    public int[] nextGreaterElementHash(int[] nums1, int[] nums2) {
        int[] hash = new int[(int) 1e4 + 1];
        for (int i = 0; i < nums2.length; i++) hash[nums2[i]] = i;
        for (int i = 0; i < nums1.length; i++) {
            int idx = hash[nums1[i]], f = -1;
            for (int j = idx + 1; j < nums2.length; j++) {
                if (nums2[j] > nums1[i]) {
                    nums1[i] = nums2[j];
                    f = -f;
                    break;
                }
            }
            if (f == -1) nums1[i] = -1;
        }
        return nums1;
    }
}
