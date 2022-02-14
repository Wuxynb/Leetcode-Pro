package problems;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 *
 * 进阶：
 *
 * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 *
 *
 * 示例 1：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例 2：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 *
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
public class Problem34 {

    public static void main(String[] args) {

    }

    public static int[] searchRange(int[] nums, int target) {
        int[] ans = new int[]{-1, -1};
        int n = nums.length, l = 0, r = n - 1, mid;
        while (l <= r) {
            mid = (l + r) / 2;
            if (nums[mid] == target) {
                int r1 = mid, r2 = mid;
                while (r1 > 0 && nums[r1 - 1] == target) r1--;
                while (r2 < n - 1 && nums[r2 + 1] == target) r2++;
                ans[0] = r1;
                ans[1] = r2;
                return ans;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    public static int[] searchRangePlus(int[] nums, int target) {
        int[] ans = new int[]{-1, -1};
        ans[0] = binarySearch(nums, target, true);
        ans[1] = binarySearch(nums, target, false);

        return ans;
    }

    public static int binarySearch(int[] nums, int target, boolean leftRight) {
        int ans = -1;
        int left = 0, right = nums.length - 1, mid;
        while (left <= right) {
            mid = left + (right - left) / 2; // mid = (left + right) / 2;
            if (nums[mid] == target) {
                ans = mid;
                if (leftRight) right = mid - 1;
                else left = mid + 1;
            } else if (nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }

        return ans;
    }
}
