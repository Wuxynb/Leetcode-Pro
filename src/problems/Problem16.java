package problems;

import java.util.Arrays;

/**
 * 16. 最接近的三数之和
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 *
 *
 *
 * 示例：
 *
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 *
 * 链接：https://leetcode-cn.com/problems/3sum-closest/
 */
public class Problem16 {

    public static void main(String[] args) {
        System.out.println(threeSumClosestPlus(new int[]{1,2,4,4,8,16,32,64,128}, 16));
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums); // 1, 2, 4, 8, 16, 32, 64, 128    --  1, 4, 6
        int abs = Integer.MAX_VALUE, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                int j = i + 1, k = nums.length - 1;
                while (j < k) {
                    if (Math.abs(nums[i] + nums[j] + nums[k] - target) < Math.abs(abs)) {
                        abs = nums[i] + nums[j] + nums[k] - target;
                        sum = nums[i] + nums[j] + nums[k];
                    }
                    if (Math.abs(nums[i] + nums[j + 1] + nums[k] - target) < Math.abs(nums[i] + nums[j] + nums[k - 1] - target)) {
                        j++;
                    } else k--;
                }
            }
        }

        return sum;
    }

    /*
        a + b + c < target, j++
        a + b + c > target, k--

     */
    public static int threeSumClosestPlus(int[] nums, int target) {
        if (nums.length < 3) return 0;
        Arrays.sort(nums);
        int sum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                int j = i + 1, k = nums.length - 1;
                while (j < k) {
                    int currentSum = nums[i] + nums[j] + nums[k];
                    sum = Math.abs(sum - target) > Math.abs(currentSum - target) ? currentSum : sum;
                    if (sum == target) return sum;
                    if (currentSum < target)
                        j++;
                    else
                        k--;
                }
            }
        }
        return sum;
    }
}
