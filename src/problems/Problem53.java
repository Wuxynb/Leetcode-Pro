package problems;

/**
 * 53. 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * 示例 2：
 *
 * 输入：nums = [1]
 * 输出：1
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：0
 * 示例 4：
 *
 * 输入：nums = [-1]
 * 输出：-1
 * 示例 5：
 *
 * 输入：nums = [-100000]
 * 输出：-100000
 *
 * 链接：https://leetcode-cn.com/problems/maximum-subarray/
 */
public class Problem53 {
    public static void main(String[] args) {
        System.out.println(maxSubArrayDC(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println(maxSubArrayDC(new int[]{-1, -2147483648, 1}));
    }

    /*
     * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出：6
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
     */
    public static int maxSubArray(int[] nums) { // 贪心
        int ans = nums[0], sum = 0;
        /*
         * 假设sum<=0，那么后面的子序列肯定不包含目前的子序列，
         * 所以令sum = num；如果sum > 0对于后面的子序列是有好处的。
         * res = Math.max(res, sum)保证可以找到最大的子序和
         */
        for (int num : nums) {
            if (sum > 0) sum += num;
            else sum = num;
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    /*
     * f(i) = max{f(i - 1) + nums[i], nums[i]}
     */
    public static int maxSubArrayDp(int[] nums) {
        int ans = nums[0], sum = 0;
        /*
         * 假设sum + num <= num，那么后面的子序列肯定不包含目前的子序列，
         * 所以令sum = num；如果sum + num > num对于后面的子序列是有好处的。
         * res = Math.max(res, sum)保证可以找到最大的子序和
         */
        for (int num : nums) {
            sum = Math.max(sum + num, num);
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    /*
     * 分治法
     */
    public static int maxSubArrayDC(int[] nums) {

        return maxSubArrayHelper(nums, 0, nums.length - 1);
    }

    public static int maxSubArrayHelper(int[] nums, int left, int right) {
        if (left == right) return nums[right];
        int mid = left + (right - left) / 2;
        int leftSum = maxSubArrayHelper(nums, left, mid);
        int rightSum = maxSubArrayHelper(nums, mid + 1, right);
        int midSum = findMaxCrossingSubArray(nums, left, mid, right);
        int result = Math.max(leftSum, rightSum);
        result = Math.max(result, midSum);
        return result;
    }

    public static int findMaxCrossingSubArray(int[] nums, int left, int mid, int right) {
        int leftSum = Integer.MIN_VALUE, rightSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >=left; i--) {
            sum += nums[i];
            leftSum = Math.max(leftSum, sum);
        }
        sum = 0;
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            rightSum = Math.max(rightSum, sum);
        }
        return leftSum + rightSum;
    }

    /*
     * 蛮力法
     */
    public static int maxSubArrayBF(int[] nums) {
        int ans = nums[0], max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            max = 0;
            for (int j = i; j < nums.length; j++) {
                max += nums[j];
                ans = Math.max(ans, max);
            }
        }

        return ans;
    }
}
