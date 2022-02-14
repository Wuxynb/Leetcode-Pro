package problems;

/**
 * 154. 寻找旋转排序数组中的最小值 II
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,4,4,5,6,7] 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,4]
 * 若旋转 7 次，则可以得到 [0,1,4,4,5,6,7]
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 *
 * 给你一个可能存在 重复 元素值的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,5]
 * 输出：1
 * 示例 2：
 *
 * 输入：nums = [2,2,2,0,1]
 * 输出：0
 *
 * 链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/
 */
public class Problem154 {
    public static void main(String[] args) {
        System.out.println(findMin(new int[]{3, 3, 3, 1, 2, 3}));
        System.out.println(findMin(new int[]{3, 1}));
        System.out.println(findMin(new int[]{2, 2, 2, 0, 1}));
        System.out.println(findMin(new int[]{3, 3, 1, 3}));
        System.out.println(findMin(new int[]{10, 1, 10, 10, 10}));
    }

    public static int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) { // right is ordered
                right = mid;
            } else if (nums[mid] > nums[right]) { // right is ordered
                left = mid + 1;
            } else { // 中间元素与最后一个元素相等
                right--;
            }
        }
        return nums[left];
    }

    // 4 5 1 2 3
    public static int findMin_(int[] nums) {
        int left = 0, right = nums.length - 1, ans = nums[0];
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[left] == nums[mid] && nums[right] == nums[mid]) { // 去除重复元素
                ans = Math.min(ans, nums[mid]);
                left++;
                right--;
            } else if (nums[left] <= nums[mid]) { // left is order
                ans = Math.min(nums[left], ans);
                left = mid + 1;
            } else {
                ans = Math.min(ans, nums[mid]);
                right = mid - 1;
            }
        }
        return ans;
    }
}
