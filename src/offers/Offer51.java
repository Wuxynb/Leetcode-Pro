package offers;

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
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
 */
public class Offer51 {
    public static void main(String[] args) {
        int[] nums = new int[]{7, 5, 6, 4};
//        System.out.println(reversePairs(nums));
        System.out.println(reversePairsPlus(nums));
    }

    static int ans = 0;
    public static int reversePairsPlus(int[] nums) {
        mergeSort(nums, 0, nums.length - 1, new int[nums.length]);
        return ans;
    }

    public static void mergeSort(int[] nums, int left, int right, int[] temp) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(nums, left, mid, temp);
            mergeSort(nums, mid + 1, right, temp);
            merge(nums, left, mid, right, temp);
        }
    }

    public static void merge(int[] nums, int left, int mid, int right, int[] temp) {
        int i = left, j = mid + 1, t = 0;
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) temp[t++] = nums[i++];
            else {
                ans += (mid - i + 1);
                temp[t++] = nums[j++];
            }
        }
        while (i <= mid) temp[t++] = nums[i++];
        while (j <= right) temp[t++] = nums[j++];
        t = 0;
        while (left <= right) nums[left++] = temp[t++];
    }

    public static int reversePairs(int[] nums) {
        return mergeSort(nums, new int[nums.length], 0, nums.length - 1);
    }

    public static int mergeSort(int[] nums, int[] tmps, int left, int right) {
        if (left >= right) return 0;
        int mid = (right + left) / 2;
        int res = mergeSort(nums, tmps, left, mid) + mergeSort(nums, tmps, mid + 1, right);
        int i = left, j = mid + 1;
        for (int k = left; k <= right; k++) tmps[k] = nums[k];
        for (int k = left; k <= right; k++) {
            if (i == mid + 1) nums[k] = tmps[j++];
            else if (j == right + 1 || tmps[i] <= tmps[j]) nums[k] = tmps[i++];
            else {
                nums[k] = tmps[j++];
                res += mid - i + 1;
            }
        }
        return res;
    }

    public int reversePairsBf(int[] nums) {
        int c = 0;
        for (int i = 0; i < nums.length - 1; i++)
            for (int j = i + 1; j < nums.length; j++)
                if (nums[i] > nums[j])
                    c++;
        return c;
    }
}
