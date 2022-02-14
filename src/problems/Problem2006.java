package problems;

/**
 * 2006. 差的绝对值为 K 的数对数目
 * 给你一个整数数组 nums 和一个整数 k ，请你返回数对 (i, j) 的数目，满足 i < j 且 |nums[i] - nums[j]| == k 。
 * <p>
 * |x| 的值定义为：
 * <p>
 * 如果 x >= 0 ，那么值为 x 。
 * 如果 x < 0 ，那么值为 -x 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,2,1], k = 1
 * 输出：4
 * 解释：差的绝对值为 1 的数对为：
 * - [1,2,2,1]
 * - [1,2,2,1]
 * - [1,2,2,1]
 * - [1,2,2,1]
 * 示例 2：
 * <p>
 * 输入：nums = [1,3], k = 3
 * 输出：0
 * 解释：没有任何数对差的绝对值为 3 。
 * 示例 3：
 * <p>
 * 输入：nums = [3,2,1,5,4], k = 2
 * 输出：3
 * 解释：差的绝对值为 2 的数对为：
 * - [3,2,1,5,4]
 * - [3,2,1,5,4]
 * - [3,2,1,5,4]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 * 1 <= k <= 99
 * <p>
 * link: https://leetcode-cn.com/problems/count-number-of-pairs-with-absolute-difference-k/
 *
 * @author Wuxinyue
 * @version 1.0
 * @date 2022/2/9 11:13
 */
public class Problem2006 {
    public static void main(String[] args) {
        System.out.println(countKDifference(new int[]{3, 2, 1, 5, 4, 6}, 2));
    }

    public static int countKDifference(int[] nums, int k) {
        int[] hash = new int[201];
        int ans = 0;
        for (int num : nums) {
            ans += (num - k < 0 ? 0 : hash[num - k]) + hash[num + k];
            hash[num]++;
        }
        return ans;
    }

    public static int countKDifferenceBF(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (Math.abs(nums[i] - nums[j]) == k) ans++;
            }
        }
        return ans;
    }

    public int countKDifference_(int[] nums, int k) {
        int[] hash = new int[101];
        for (int num : nums) hash[num]++;
        for (int i = 1; i + k < 101; i++) hash[0] += hash[i] * hash[i + k];
        return hash[0];
    }
}
