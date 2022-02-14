package problems;

import java.util.Arrays;

/**
 * 164. 最大间距
 * 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
 *
 * 如果数组元素个数小于 2，则返回 0。
 *
 * 示例 1:
 *
 * 输入: [3,6,9,1]
 * 输出: 3
 * 解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
 * 示例 2:
 *
 * 输入: [10]
 * 输出: 0
 * 解释: 数组元素个数小于 2，因此返回 0。
 *
 * 链接：https://leetcode-cn.com/problems/maximum-gap/
 */
public class Problem164 {
    public static void main(String[] args) {
        System.out.println(maximumGap(new int[]{3, 6, 9, 1}));
        System.out.println(maximumGapCountingSort(new int[]{3, 6, 9, 1}));
        System.out.println(maximumGapRadixSort(new int[]{3, 6, 9, 1}));
    }

    public static int maximumGapRadixSort(int[] nums) {
        if (nums.length <= 1) return 0;
        int maxV = Arrays.stream(nums).max().getAsInt(), radix = 1, n = nums.length, ans = 0;
        int[] temps = new int[n], buckets = new int[10];
        while (maxV >= radix) {
            Arrays.fill(buckets, 0);
            for (int num : nums) buckets[(num / radix) % 10]++;
            for (int i = 1; i < 10; i++) buckets[i] += buckets[i - 1];
            for (int i = n - 1; i >= 0; i--) temps[--buckets[(nums[i] / radix) % 10]] = nums[i];
            System.arraycopy(temps, 0, nums, 0, n);
            radix *= 10;
        }
        for (int i = 0; i < nums.length - 1; i++) ans = Math.max(ans, nums[i + 1] - nums[i]);
        return ans;
    }

    public static int maximumGapCountingSort(int[] nums) {
        if (nums.length <= 1) return 0;
        int maxV = Arrays.stream(nums).max().getAsInt(), ans = 0, idx = 0;
        int[] buckets = new int[maxV + 1];
        for (int num : nums) buckets[num]++;
        for (int i = 0; i < buckets.length; i++) {
            while (buckets[i] > 0) {
                nums[idx++] = i;
                buckets[i]--;
            }
        }
        for (int i = 0; i < nums.length - 1; i++) ans = Math.max(ans, nums[i + 1] - nums[i]);
        return ans;
    }

    public static int maximumGap(int[] nums) {
        int ans = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            ans = Math.max(ans, nums[i + 1] - nums[i]);
        }
        return ans;
    }
}
