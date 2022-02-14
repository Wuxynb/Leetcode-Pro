package problems;

import java.util.Arrays;

/**
 * 152. 乘积最大子数组
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 *
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 * 链接：https://leetcode-cn.com/problems/maximum-product-subarray/
 */
public class Problem152 {
    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{-2, 4, -1}));
        System.out.println(maxProductDp(new int[]{-2, 4, -1}));
    }

    public static int maxProductDp(int[] nums) {
        int[] maxF = new int[nums.length];
        int[] minF = new int[nums.length];
        maxF[0] = minF[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxF[i] = Math.max(maxF[i - 1] * nums[i], Math.max(nums[i], minF[i - 1] * nums[i]));
            minF[i] = Math.min(minF[i - 1] * nums[i], Math.min(nums[i], maxF[i - 1] * nums[i]));
        }
        int ans = maxF[0];
        for (int i = 1; i < maxF.length; i++) {
            ans = Math.max(ans, maxF[i]);
        }

        return Arrays.stream(maxF).max().getAsInt();
    }

    // -2, 4, -1
    public static int maxProduct(int[] nums) {
        int ans = Integer.MIN_VALUE, maxV = 1, minV = 1;
        for (int num : nums) {
            if (num < 0) {
                int tmp = maxV;
                maxV = minV;
                minV = tmp;
            }
            maxV = Math.max(maxV * num, num);
            minV = Math.min(minV * num, num);
            ans = Math.max(maxV, ans);
        }
        return ans;
    }
}
