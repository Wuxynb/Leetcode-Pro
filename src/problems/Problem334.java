package problems;

/**
 * 334. 递增的三元子序列
 * 给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。
 *
 * 如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，使得 nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,4,5]
 * 输出：true
 * 解释：任何 i < j < k 的三元组都满足题意
 * 示例 2：
 *
 * 输入：nums = [5,4,3,2,1]
 * 输出：false
 * 解释：不存在满足题意的三元组
 * 示例 3：
 *
 * 输入：nums = [2,1,5,0,4,6]
 * 输出：true
 * 解释：三元组 (3, 4, 5) 满足题意，因为 nums[3] == 0 < nums[4] == 4 < nums[5] == 6
 *
 * 链接：https://leetcode-cn.com/problems/increasing-triplet-subsequence/
 */
public class Problem334 {
    public static void main(String[] args) {
        System.out.println(increasingTriplet(new int[]{1, 2, 1, 2, 1, 2}));
    }

    public static boolean increasingTriplet(int[] nums) {
        int a = nums[0], b = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= a) a = nums[i];
            else if (nums[i] <= b) b = nums[i];
            else return true;
        }
        return false;
    }

    public static boolean increasingTripletAnswer(int[] nums) {
        int a = nums[0], b = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > b) return true;
            else if (nums[i] > a) b = nums[i];
            else a = nums[i];
        }
        return false;
    }
}
