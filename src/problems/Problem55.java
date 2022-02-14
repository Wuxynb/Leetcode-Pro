package problems;

/**
 * 55. 跳跃游戏
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个下标。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 * 示例 2：
 *
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 *
 * 链接：https://leetcode-cn.com/problems/jump-game/
 */
public class Problem55 {
    public static void main(String[] args) {
        System.out.println(canJump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(canJump(new int[]{3, 2, 1, 0, 4}));
    }

    /*
     * left < -- right
     */
    public static boolean canJump(int[] nums){
        int end = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] + i >= end) end = i;
        }

        return end == 0;
    }

    /*
     *  left -- > right
     */
    public static boolean canJump_(int[] nums) {
        int position = 0; // 每次能跳到的最大距离
        for (int i = 0; i < nums.length; i++) {
            if (i <= position) {
                position = Math.max(position, i + nums[i]); // 更新最大距离
                if (position >= nums.length - 1) return true;
            }
        }

        return false;
    }

    /*
     * nums = [2, 3, 1, 1, 4]
     */
    public static boolean jump(int[] nums) {
        int end = 0, position = 0;  // end 当前边界，position存放下一个最大边界
        for (int i = 0; i < nums.length - 1; i++) {
            position = Math.max(position, i + nums[i]);
            if (i == end) {
                if (end == position) return false; // 如果下一次边界等于上一次边界，则表示无法再往后跳
                end = position;
            }
        }

        return true;
    }

    /*
     * nums = [3, 2, 1, 0, 4]
     */
    public static boolean jump_(int[] nums) {
        int position = nums.length - 1;
        while (position != 0) {
            boolean f = false;
            for (int i = 0; i < position; i++) {
                if (nums[i] + i >= position) {
                    position = i;
                    f = true;
                    break;
                }
            }
            if (!f) return false;
        }

        return true;
    }
}
