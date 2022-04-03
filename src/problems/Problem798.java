package problems;

import java.util.Arrays;

/**
 * 798. 得分最高的最小轮调
 * 给你一个数组 nums，我们可以将它按一个非负整数 k 进行轮调，这样可以使数组变为 [nums[k], nums[k + 1], ... nums[nums.length - 1], nums[0], nums[1], ..., nums[k-1]] 的形式。此后，任何值小于或等于其索引的项都可以记作一分。
 * <p>
 * 例如，数组为 nums = [2,4,1,3,0]，我们按 k = 2 进行轮调后，它将变成 [1,3,0,2,4]。这将记为 3 分，因为 1 > 0 [不计分]、3 > 1 [不计分]、0 <= 2 [计 1 分]、2 <= 3 [计 1 分]，4 <= 4 [计 1 分]。
 * 在所有可能的轮调中，返回我们所能得到的最高分数对应的轮调下标 k 。如果有多个答案，返回满足条件的最小的下标 k 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,3,1,4,0]
 * 输出：3
 * 解释：
 * 下面列出了每个 k 的得分：
 * k = 0,  nums = [2,3,1,4,0],    score 2
 * k = 1,  nums = [3,1,4,0,2],    score 3
 * k = 2,  nums = [1,4,0,2,3],    score 3
 * k = 3,  nums = [4,0,2,3,1],    score 4
 * k = 4,  nums = [0,2,3,1,4],    score 3
 * 所以我们应当选择 k = 3，得分最高。
 * 示例 2：
 * <p>
 * 输入：nums = [1,3,0,2,4]
 * 输出：0
 * 解释：
 * nums 无论怎么变化总是有 3 分。
 * 所以我们将选择最小的 k，即 0。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * 0 <= nums[i] < nums.length
 * <p>
 * link: https://leetcode-cn.com/problems/smallest-rotation-with-highest-score/
 */
public class Problem798 {
    public static void main(String[] args) {
        System.out.println(bestRotation(new int[]{2, 3, 1, 4, 0}));
    }

    public static int bestRotation(int[] nums) {
        // 每个数左撤后，统计nums[i]<=i的数量
        int[] rotate = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) continue;
            // 0在任何位置都有分数
            int minus = nums[i] - 1; // 左移过程中减1的位置
            if (i > minus) rotate[i - minus]--;
             else rotate[nums.length + i - minus]--;
            rotate[i + 1]++; // 转到数组尾部
        }
        int ans = 0;
        for (int i = 1; i < nums.length; i++) {
            rotate[i] += rotate[i - 1];
            if (rotate[i] > rotate[ans]) ans = i;
        }
        return ans;
    }

    public static int bestRotationBF(int[] nums) {
        int k = 0, n = nums.length, score = 0;
        for (int i = 0; i < n; i++) {
            int[] array = reverse(nums, i);
            int tmp = 0;
            for (int j = 0; j < n; j++) if (array[j] <= j) tmp++;
            if (tmp > score) {
                k = i;
                score = tmp;
            }
        }
        return k;
    }

    public static int[] reverse(int[] nums, int k) {
        int n = nums.length, j = 0;
        int[] ans = new int[n];
        for (int i = k; i < n; i++) ans[j++] = nums[i];
        for (int i = 0; i < k; i++) ans[j++] = nums[i];
        return ans;
    }
}
