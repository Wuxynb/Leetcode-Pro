package offers;

import java.util.*;

/**
 * 剑指 Offer 59 - I. 滑动窗口的最大值
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 * <p>
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * <p>
 * 链接：https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/
 */
public class Offer59 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSlidingWindowQueue(new int[]{9, 10, 9, -7, -4, -8, 2, -6}, 5)));
    }

    public static int[] maxSlidingWindowQueue(int[] nums, int k) {
        if (nums.length == 0) return new int[0];
        Deque<Integer> queue = new LinkedList<>();
        int[] ans = new int[nums.length - k + 1];
        for (int i = 0; i < k; i++) {
            while (!queue.isEmpty() && nums[i] > queue.peekLast()) queue.removeLast();
            queue.addLast(nums[i]);
        }
        ans[0] = queue.peekFirst();
        for (int i = k; i < nums.length; i++) {
            // i-k是已经在区间外了，如果首位等于nums[i-k]，那么说明此时首位值已经不再区间内了，需要删除
            if (queue.peekFirst() == nums[i - k]) queue.removeFirst();
            while (!queue.isEmpty() && nums[i] > queue.peekLast()) queue.removeLast();
            queue.addLast(nums[i]);
            ans[i - k + 1] = queue.peekFirst();
        }
        return ans;
    }

    public static int[] maxSlidingWindowPq(int[] nums, int k) {
        if (nums.length == 0) return new int[0];
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int[] ans = new int[nums.length - k + 1];
        for (int i = 0; i < k - 1; i++) pq.offer(nums[i]);
        for (int i = 0; i <= nums.length - k; i++) {
            pq.offer(nums[i + k - 1]);
            ans[i] = pq.peek();
            pq.remove(nums[i]);
        }
        return ans;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) return new int[0];
        int[] ans = new int[nums.length - k + 1];
        for (int i = 0; i < k; i++) ans[0] = Math.max(ans[0], nums[i]);
        for (int i = 1; i <= nums.length - k; i++) {
            if (nums[i + k - 1] >= ans[i - 1]) ans[i] = nums[i + k - 1];
            else {
                if (nums[i - 1] != ans[i - 1]) ans[i] = ans[i - 1];
                else {
                    ans[i] = Integer.MIN_VALUE;
                    for (int j = i; j < i + k; j++) ans[i] = Math.max(ans[i], nums[j]);
                }
            }
        }
        return ans;
    }
}
