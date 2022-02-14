package problems;

/**
 * 1748. 唯一元素的和
 * 给你一个整数数组 nums 。数组中唯一元素是那些只出现 恰好一次 的元素。
 *
 * 请你返回 nums 中唯一元素的 和 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,2]
 * 输出：4
 * 解释：唯一元素为 [1,3] ，和为 4 。
 * 示例 2：
 *
 * 输入：nums = [1,1,1,1,1]
 * 输出：0
 * 解释：没有唯一元素，和为 0 。
 * 示例 3 ：
 *
 * 输入：nums = [1,2,3,4,5]
 * 输出：15
 * 解释：唯一元素为 [1,2,3,4,5] ，和为 15 。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 *
 * link: https://leetcode-cn.com/problems/sum-of-unique-elements/
 *
 * @author Wuxinyue
 * @version 1.0
 * @date 2022/2/6 11:58
 */
public class Problem1748 {
    public static void main(String[] args) {

    }

    public int sumOfUnique(int[] nums) {
        int[] hash = new int[101];
        for (int i : nums) {
            hash[0] += hash[i] == 0 ? i : (hash[i] == 1) ? -i : 0;
            hash[i]++;
        }
        return hash[0];
    }

    public int sumOfUniqueN(int[] nums) {
        int[] hash = new int[101];
        for (int i : nums) hash[i]++;
        for (int i = 1; i < 101; i++) hash[0] += hash[i] == 1 ? i : 0;
        return hash[0];
    }
}
