package problems;

/**
 * 307. 区域和检索 - 数组可修改
 * 给你一个数组 nums ，请你完成两类查询。
 *
 * 其中一类查询要求 更新 数组 nums 下标对应的值
 * 另一类查询要求返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 ，其中 left <= right
 * 实现 NumArray 类：
 *
 * NumArray(int[] nums) 用整数数组 nums 初始化对象
 * void update(int index, int val) 将 nums[index] 的值 更新 为 val
 * int sumRange(int left, int right) 返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 （即，nums[left] + nums[left + 1], ..., nums[right]）
 *
 *
 * 示例 1：
 *
 * 输入：
 * ["NumArray", "sumRange", "update", "sumRange"]
 * [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
 * 输出：
 * [null, 9, null, 8]
 *
 * 解释：
 * NumArray numArray = new NumArray([1, 3, 5]);
 * numArray.sumRange(0, 2); // 返回 1 + 3 + 5 = 9
 * numArray.update(1, 2);   // nums = [1,2,5]
 * numArray.sumRange(0, 2); // 返回 1 + 2 + 5 = 8
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 * 104
 * -100 <= nums[i] <= 100
 * 0 <= index < nums.length
 * -100 <= val <= 100
 * 0 <= left <= right < nums.length
 * 调用 update 和 sumRange 方法次数不大于 3 * 104
 *
 * link: https://leetcode-cn.com/problems/range-sum-query-mutable/
 */
public class Problem307 {
    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int[]{0, 9, 5, 7, 3});
        System.out.println(numArray.sumRange(4, 4)); // 3
        System.out.println(numArray.sumRange(2, 4)); // 15
        System.out.println(numArray.sumRange(2, 2));
        numArray.update(1, 5);
        System.out.println(numArray.sumRange(2, 4));
    }

    /**
     * Your NumArray object will be instantiated and called as such:
     * NumArray obj = new NumArray(nums);
     * obj.update(index,val);
     * int param_2 = obj.sumRange(left,right);
     */
    static class NumArray {
        private final int[] nums, sum;

        public NumArray(int[] nums) {
            this.nums = nums;
            sum = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                add(i + 1, nums[i]);
            }
        }

        private int lowBit(int x) {
            return x & -x;
        }

        private void add(int p, int x) {
            for (int i = p; i <= nums.length; i += lowBit(i)) {
                sum[i] += x;
            }
        }

        private int getSum(int p) {
            int ans = 0;
            for (int i = p; i > 0; i -= lowBit(i)) {
                ans += sum[i];
            }
            return ans;
        }

        public void update(int index, int val) {
            add(index + 1, val - nums[index]);
            nums[index] = val;
        }

        public int sumRange(int left, int right) {

            return getSum(right + 1) - getSum(left);
        }
    }
}
