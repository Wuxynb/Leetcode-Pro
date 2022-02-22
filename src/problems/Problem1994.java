package problems;

/**
 * 1994. 好子集的数目
 * 给你一个整数数组 nums 。如果 nums 的一个子集中，所有元素的乘积可以表示为一个或多个 互不相同的质数 的乘积，那么我们称它为 好子集 。
 * <p>
 * 比方说，如果 nums = [1, 2, 3, 4] ：
 * [2, 3] ，[1, 2, 3] 和 [1, 3] 是 好 子集，乘积分别为 6 = 2*3 ，6 = 2*3 和 3 = 3 。
 * [1, 4] 和 [4] 不是 好 子集，因为乘积分别为 4 = 2*2 和 4 = 2*2 。
 * 请你返回 nums 中不同的 好 子集的数目对 109 + 7 取余 的结果。
 * <p>
 * nums 中的 子集 是通过删除 nums 中一些（可能一个都不删除，也可能全部都删除）元素后剩余元素组成的数组。如果两个子集删除的下标不同，那么它们被视为不同的子集。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3,4]
 * 输出：6
 * 解释：好子集为：
 * - [1,2]：乘积为 2 ，可以表示为质数 2 的乘积。
 * - [1,2,3]：乘积为 6 ，可以表示为互不相同的质数 2 和 3 的乘积。
 * - [1,3]：乘积为 3 ，可以表示为质数 3 的乘积。
 * - [2]：乘积为 2 ，可以表示为质数 2 的乘积。
 * - [2,3]：乘积为 6 ，可以表示为互不相同的质数 2 和 3 的乘积。
 * - [3]：乘积为 3 ，可以表示为质数 3 的乘积。
 * 示例 2：
 * <p>
 * 输入：nums = [4,2,3,15]
 * 输出：5
 * 解释：好子集为：
 * - [2]：乘积为 2 ，可以表示为质数 2 的乘积。
 * - [2,3]：乘积为 6 ，可以表示为互不相同质数 2 和 3 的乘积。
 * - [2,15]：乘积为 30 ，可以表示为互不相同质数 2，3 和 5 的乘积。
 * - [3]：乘积为 3 ，可以表示为质数 3 的乘积。
 * - [15]：乘积为 15 ，可以表示为互不相同质数 3 和 5 的乘积。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 30
 * <p>
 * link: https://leetcode-cn.com/problems/the-number-of-good-subsets/
 */
public class Problem1994 {
    public static void main(String[] args) {
        System.out.println(numberOfGoodSubsets(new int[]{1, 2, 3, 4, 15, 30}));
    }

    /***
     * 好子集中每个质数最多出现一次
     *
     * num: 原整数集中的任意一个数
     * maskForNum: num中质数的存在情况
     * state: 每一种质数的选择状态
     * count[]: 统计num的出现次数
     * dp[maskForNum | state]: 选择num的前提下再选择state中标记的质数之后的好子集的个数，也即maskForNum改变state之后的好子集的个数
     *
     * 1、进行变量定义以及 **dp[]**的初始化
     * 2、将nums[]中的每个数的出现情况统计到count[]
     * 3、从小到大遍历**nums[]**中的每个数
     *      将有平方数质因数的num排除
     *      将num的质数出现情况统计进 maskForNum
     *      遍历每一种可能的state
     *          如果当前state中某一个质数在maskForNum已经存在了，就跳过
     *          套用 dp[maskForNum|state] += count[num]*dp[state] 进行动态规划
     * 4、统计**dp[]**中所有好子集的总和，注意 dp[0] 不需要统计
     *
     * 5、然后就是针对特殊数 1，有多少个1，最后的结果就乘以2的多少次方。
     */
    public static int numberOfGoodSubsets(int[] nums) {
        int mod = (int) (1e9 + 7);
        long res = 0;
        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29}, count = new int[31];
        long[] dp = new long[1024]; // 2^10
        dp[0] = 1; // 初始化dp0
        for (int num : nums) count[num]++;
        for (int num = 2; num <= 30; num++) { // 遍历nums中的每一个数，除了1
            // 当前数不存在或为平方数 跳过
            if (count[num] == 0 || num % 4 == 0 || num % 9 == 0 || num % 25 == 0) continue;

            // 对10个质数处理，如果当前数能被质数整除，则记录进maskForNum
            int maskFomNum = 0;
            for (int i = 0; i < 10; i++) {
                if (num % primes[i] == 0) maskFomNum |= (1 << i);
            }

            // 遍历每一种状态
            for (int state = 0; state < 1024; state++) { // 2^10=1024
                // maskForNum中已经存在了其中一个质数，跳过
                if ((maskFomNum & state) > 0) continue;
                // 更新当前状态的好子集个数
                dp[maskFomNum | state] = (dp[maskFomNum | state] + count[num] * dp[state]) % mod;
            }
        }
        // 跳过dp[0]
        for (int i = 1; i < 1024; i++) res = (res + dp[i]) % mod;
        // 最后结果为res乘以2的多少个1的次方，即1既可以选，也可以不选
        for (int i = 0; i < count[1]; ++i) res = (res * 2) % mod;

        return (int) res;
    }
}
