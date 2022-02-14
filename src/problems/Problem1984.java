package problems;

import java.util.Arrays;

/**
 * @author Wuxinyue
 * @version 1.0
 * @date 2022/2/14 16:36
 */
public class Problem1984 {
    public static void main(String[] args) {
        System.out.println(minimumDifference(new int[]{90}, 1));
        System.out.println(minimumDifference(new int[]{9, 4, 1, 7}, 2));
    }

    public static int minimumDifference(int[] nums, int k) {
        int ans = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = 0; i + k - 1 < nums.length; i++) {
            ans = Math.min(ans, nums[i + k - 1] - nums[i]);
        }
        return ans;
    }
}
