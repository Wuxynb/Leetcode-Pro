package problems;

import java.util.Arrays;

/**
 * 45. 跳跃游戏 II
 * 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 *
 * 假设你总是可以到达数组的最后一个位置。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 示例 2:
 *
 * 输入: nums = [2,3,0,1,4]
 * 输出: 2
 *
 * 链接：https://leetcode-cn.com/problems/jump-game-ii/
 */
public class Problem45 {

    public static void main(String[] args) {
        System.out.println(jump(new int[]{0}) == 0); // 0
        System.out.println(jump(new int[]{1}) == 0); // 0
        System.out.println(jump(new int[]{2, 3, 1, 1, 4}) == 2); // 2
        System.out.println(jump(new int[]{2, 1, 2, 1, 4}) == 2); // 2
//        System.out.println(jump(new int[]{1, 1, 1, 1}) == 3); // 3
//        System.out.println(jump_(new int[]{1, 2, 1, 1, 1}) == 3); // 3
//        System.out.println(jump_(new int[]{1, 2, 1, 1}) == 2); // 2
//        System.out.println(jump_(new int[]{2, 1, 1, 1, 1}) == 3); // 3
//        System.out.println(jump_(new int[]{5,9,3,2,1,0,2,3,3,1,0,0}) == 3); // 3
//        System.out.println(jumpDp(new int[]{2, 3, 1, 2, 4, 2, 3}) == 3); // 3
//        System.out.println(jumpDp(new int[]{2, 3, 1, 1, 4}) == 2); // 2
//        System.out.println(jumpDp(new int[]{2, 3, 1, 1, 4}) == 2); // 2
    }

    /*
     *从数组的第 0 个位置开始跳，跳的距离小于等于数组上对应的数。求出跳到最后个位置需要的最短步数。
     * 比如上图中的第 0 个位置是 2，那么可以跳 1 个距离，或者 2 个距离，我们选择跳 1 个距离，就
     * 跳到了第 1 个位置，也就是 3 上。然后我们可以跳 1，2，3 个距离，我们选择跳 3 个距离，就直
     * 接到最后了。所以总共需要 2 步。
     *
     *   2, 3, 1, 2, 4, 2, 3
     */
    public static int jump(int[] nums) {
        int len = nums.length;
        int end = 0, maxPosition = 0, step = 0;// end 表示当前能跳的边界, maxPosition表示最大位置
        for (int i = 0; i < len - 1; i++) {
            // 找能跳的最远的
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) { // 遇到边界，就更新边界，并且步数加一
                end = maxPosition;
                step++;
            }
        }
        return step;
    }

    /*
     *    我们知道最终要到达最后一个位置，然后我们找前一个位置，遍历数组，找到能到达它的位置，离它最远的就是要找的位置。
     * 然后继续找上上个位置，最后到了第 0 个位置就结束了。至于离它最远的位置，其实我们从左到右遍历数组，第一个满足的
     * 位置就是我们要找
     *
     * 2, 3, 1, 1, 4
     */
    public static int jump_(int[] nums) {
        int position = nums.length - 1, steps = 0; // position即为要找的位置
        while (position != 0) { // 是否为第0个元素
            for (int i = 0; i < position; i++) {
                if (nums[i] >= position - i) {
                    position = i; // 更新要找的位置
                    steps++;
                    break;
                }
            }
        }

        return steps;
    }

    /*
        dp[j] = min{ dp[i] + 1, dp[j] },      i <= j <= i + nums[i] && j < nums.length

        2, 3, 1, 1, 4

     */
    public static int jumpDp(int[] nums) {
        int[] dp = new int[nums.length]; // 到达位置i所用的最小步数
        Arrays.fill(dp, dp.length - 1);
        dp[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length && j <= i + nums[i]; j++) {// 从i都能1步到达的位置
                dp[j] = Math.min(dp[i] + 1, dp[j]);
            }
        }

        return dp[dp.length - 1];
    }
}
