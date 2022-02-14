package problems;

import java.util.Arrays;

/**
 * 75. 颜色分类
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 *
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 * 示例 2：
 *
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：[0]
 * 示例 4：
 *
 * 输入：nums = [1]
 * 输出：[1]
 *
 * 链接：https://leetcode-cn.com/problems/sort-colors/
 */
public class Problem75 {
    public static void main(String[] args) {
        int[] nums = new int[]{5, 4, 5, 2, 3, 9, 7, 1};
        mergeSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    /*
        0 挪到最前面，2 挪到最后面就完事儿了
        注意 2 挪完如果换出来的不是 1，那么指针要回退，因为 0 和 2 都是需要再次移动的
     */
    public static void sortColors(int[] nums) {
        int p = 0, q = nums.length - 1;
        for (int i = 0; i <= q; i++) {
            if (nums[i] == 0) {
                nums[i] = nums[p];
                nums[p++] = 0;
            } else if (nums[i] == 2) {
                nums[i] = nums[q];
                nums[q--] = 2;
                if (nums[i] != 1) i--;
            }
        }
    }

    public static void bubbleSortColors(int[] nums) {
        bubbleSort(nums);
    }

    public static void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }

            }
        }
    }

    public static void quickSortColors(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    public static void quickSort(int[] nums, int low, int high)  {
        if (low < high) {
            int s = partition(nums, low, high);
            quickSort(nums, low, s - 1);
            quickSort(nums, s + 1, high);
        }
    }

    /*
        5 4 5 2 3 9 7 1
        5 4 5 2 3 1 7 9

     */
    public static int partition(int[] nums, int low, int high) {
        int p = nums[low], i = low, j = high;
        while (i < j) {
            while (nums[j] > p && i < j) j--;
            while (nums[i] <= p && i < j) i++;
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        nums[low] = nums[j];
        nums[j] = p;

        return j;
    }

    public static void mergeSortColors(int[] nums) {
        mergeSort(nums);
    }

    public static void mergeSort(int[] nums) {
        if (nums.length > 1) {
            int[] nums1 = Arrays.copyOfRange(nums, 0, nums.length / 2);
            int[] nums2 = Arrays.copyOfRange(nums, nums.length / 2, nums.length);
            mergeSort(nums1);
            mergeSort(nums2);
            merge(nums, nums1, nums2);
        }
    }

    public static void merge(int[] nums, int[] nums1, int[] nums2) {
        int i = 0, j = 0, c = 0;
        while (i < nums1.length || j < nums2.length) {
            if (i < nums1.length && j < nums2.length) {
                if (nums1[i] < nums2[j]) nums[c++] = nums1[i++];
                else if (nums1[i] == nums2[j]) {
                    nums[c++] = nums1[i++];
                    nums[c++] = nums2[j++];
                } else nums[c++] = nums2[j++];
            } else if (i < nums1.length) nums[c++] = nums1[i++];
            else nums[c++] = nums2[j++];
        }
    }
}
