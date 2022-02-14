package problems;

/**
 * 540. 有序数组中的单一元素
 * 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
 *
 * 请你找出并返回只出现一次的那个数。
 *
 * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,2,3,3,4,4,8,8]
 * 输出: 2
 * 示例 2:
 *
 * 输入: nums =  [3,3,7,7,10,11,11]
 * 输出: 10
 *
 *
 * 提示:
 *
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 105
 *
 * link: https://leetcode-cn.com/problems/single-element-in-a-sorted-array/
 *
 * @author Wuxinyue
 * @version 1.0
 * @date 2022/2/14 12:13
 */
public class Problem540 {
    public static void main(String[] args) {
        System.out.println(singleNonDuplicate(new int[]{1, 1, 2, 3, 3, 4, 4, 8, 8}));
        System.out.println(singleNonDuplicate(new int[]{1, 1, 2, 2, 3, 3, 4, 8, 8}));
        System.out.println(singleNonDuplicate(new int[]{3, 3, 7, 7, 10, 11, 11}));

        System.out.println(singleNonDuplicateAns(new int[]{1, 1, 2, 3, 3, 4, 4, 8, 8}));
        System.out.println(singleNonDuplicateAns(new int[]{1, 1, 2, 2, 3, 3, 4, 8, 8}));
        System.out.println(singleNonDuplicateAns(new int[]{3, 3, 7, 7, 10, 11, 11}));
    }

    public static int singleNonDuplicateAns(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = ((right - left) >> 1) + left;
            if ((mid & 1) == 1) mid--;
            if (nums[mid] == nums[mid + 1]) left = mid + 2;
            else right = mid;
        }
        return nums[left];
    }

    // 1 1 2 3 3 4 4 8 8
    // 1 1 2 2 3 3 4 8 8
    public static int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid - 1] == nums[mid]) {
                if (((mid - 1) & 1) == 1) right = mid - 2;
                else left = mid + 1;
            } else if (nums[mid] == nums[mid + 1]) {
                if ((mid & 1) == 1) right = mid - 1;
                else left = mid + 2;
            } else return nums[mid];
        }
        return nums[left];
    }
}
