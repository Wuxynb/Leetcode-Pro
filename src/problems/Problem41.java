package problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 41. 缺失的第一个正数
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 *
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,0]
 * 输出：3
 * 示例 2：
 *
 * 输入：nums = [3,4,-1,1]
 * 输出：2
 * 示例 3：
 *
 * 输入：nums = [7,8,9,11,12]
 * 输出：1
 *
 * 链接：https://leetcode-cn.com/problems/first-missing-positive/
 */
public class Problem41 {

    public static void main(String[] args) {
        System.out.println(firstMissingPositive(new int[]{-1, 4, 2, 1, 5, 7}));
    }

    /* hashtable */
    public static int firstMissingPositive(int[] nums) {
        // [1, n + 1]
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0) {
                nums[i] = nums.length + 1;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            int num = Math.abs(nums[i]);
            if (num <= nums.length) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) return i + 1;
        }

        return nums.length + 1;
    }


    // displace
    public int firstMissingPositivePlus(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) return i + 1;
        }

        return nums.length + 1;
    }

    public int firstMissingPositive_(int[] nums) {
        int min = 1;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (min == nums[i]) min++;
        }
        return min;
    }


    public int firstMissingPositive__(int[] nums) {
        int min = 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        while (map.get(min) != null) {
            min++;
        }
        return min;
    }
}
