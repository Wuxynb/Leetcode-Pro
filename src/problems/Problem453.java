package problems;

/**
 * 453. 最小操作次数使数组元素相等
 * 给你一个长度为 n 的整数数组，每次操作将会使 n - 1 个元素增加 1 。返回让数组所有元素相等的最小操作次数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：3
 * 解释：
 * 只需要3次操作（注意每次操作会增加两个元素的值）：
 * [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 * 示例 2：
 *
 * 输入：nums = [1,1,1]
 * 输出：0
 *
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * 答案保证符合 32-bit 整数
 *
 * link: https://leetcode.cn/problems/minimum-moves-to-equal-array-elements/
 */
public class Problem453 {
    public static void main(String[] args) {

    }

    public int minMoves(int[] nums) {
        int min = nums[0], sum = nums[0], n = nums.length;
        for(int i = 1; i < n; i++){
            sum += nums[i];
            if(min > nums[i]) min = nums[i];
        }
        // sum + m * (n - 1) = x * n; x = min + m; => m = sum - n * min
        return sum - min * n;
    }

    public int minMoves_(int[] nums) {
        int min = nums[0], n = nums.length, ans = 0;
        for (int i = 1; i < n; i++) if (min < nums[i]) min = nums[i];
        for (int num : nums) ans += num - min;
        return ans;
    }
}
