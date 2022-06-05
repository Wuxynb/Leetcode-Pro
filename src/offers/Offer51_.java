package offers;

import java.util.Arrays;

/**
 * 剑指 Offer 51. 数组中的逆序对
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [7,5,6,4]
 * 输出: 5
 *
 *
 * 限制：
 *
 * 0 <= 数组长度 <= 50000
 *
 * link: https://leetcode.cn/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
 */
public class Offer51_ {
    public static void main(String[] args) {
        System.out.println(reversePairs(new int[]{7, 5, 6, 4}));

        System.out.println(reversePairsBit(new int[]{7, 5, 6, 4}));
    }

    /*
     * ============================================================
     * |                   树状数组统计逆序对                         |
     * ============================================================
     */
    public static int reversePairsBit(int[] nums) {
        int n = nums.length, ans = 0;
        int[] temp = nums.clone();
        // 离散化
        Arrays.sort(temp);
        for (int i = 0; i < n; i++)
            nums[i] = Arrays.binarySearch(temp, nums[i]) + 1; // 记录nums[i]在数组中的位置
        // 树状数组统计逆序对
        BIT bit = new BIT(n);
        for (int i = n - 1; i >= 0; --i) {
            ans += bit.getSum(nums[i] - 1);
            bit.add(nums[i], 1);
        }
        return ans;
    }

    static class BIT {
        private final int[] tree;
        private final int n;

        public BIT(int n) {
            this.n = n;
            tree = new int[n + 1];
        }

        private int lowbit(int x) { return x & -x; }

        public void add(int x, int d) {
            for (int i = x; i <= n; i += lowbit(i)) {
                tree[i] += d;
            }
        }

        public int getSum(int x) {
            int ans = 0;
            for (int i = x; i > 0; i -= lowbit(i)) {
                ans += tree[i];
            }
            return ans;
        }
    }

    /*
     * ============================================================
     * |                   归并排序统计逆序对                         |
     * ============================================================
     */
    static int ans = 0;

    public static int reversePairs(int[] nums) {
        ans = 0;
        mergeSort(nums, 0, nums.length - 1);
        return ans;
    }

    public static void mergeSort(int[] nums, int l, int r) {
        if (l < r) {
            int m = l + (r - l >> 1);
            mergeSort(nums, l, m);
            mergeSort(nums, m + 1, r);
            merge(nums, l, m, r);
        }
    }

    public static void merge(int[] nums, int l, int m, int r) {
        int i = l, j = m + 1, t = 0;
        int[] temp = new int[r - l + 1];
        while (i <= m && j <= r) {
            if (nums[i] <= nums[j]) {
                temp[t++] = nums[i++];
            } else {
                ans += (m - i + 1);
                temp[t++] = nums[j++];
            }
        }
        while (i <= m) temp[t++] = nums[i++];
        while (j <= r) temp[t++] = nums[j++];
        for (int k = 0; k < temp.length; k++) nums[l + k] = temp[k];
    }
}
