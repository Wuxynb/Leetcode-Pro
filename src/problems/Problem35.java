package problems;

/**
 * 35. 搜索插入位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 请必须使用时间复杂度为 O(log n) 的算法。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 * 示例 2:
 *
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 * 示例 3:
 *
 * 输入: nums = [1,3,5,6], target = 7
 * 输出: 4
 *
 * 链接：https://leetcode-cn.com/problems/search-insert-position/
 */
public class Problem35 {

    public static void main(String[] args) {
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 0));
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 2));
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 3));
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 4));
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 7));
    }

    /*
     *  在while循环中，若找到了target直接返回
     *  当原数组不包含target时，考虑while循环最后一次执行的总是 left = right = mid,
     *  此时nums[mid] 左边的数全部小于target，nums[mid]右边的数全部大于target,
     *  则此时我们要返回的插入位置分为两种情况：
     *  ①就是这个位置，即nums[mid]>target时，此时执行了right=mid-1，返回left正确
     *  ②是该位置的右边一个，即nums[mid]<target时，此时执行了left=mid+1,返回left也正确
     */
    public static int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length - 1, mid;
        while (l <= r) {
            mid = l + (r - l) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) {
//                if (nums[mid] <= target && nums[r] >= target) l = mid + 1;
//                else return r + 1;
                l = mid + 1;
            } else {
//                if (nums[l] <= target && nums[mid] >= target) r = mid - 1;
//                else return l;
                r = mid - 1;
            }
        }
        return l;
    }
}
