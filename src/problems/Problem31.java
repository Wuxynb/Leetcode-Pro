package problems;

import java.util.Arrays;

/**
 * 31. 下一个排列
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列（即，组合出下一个更大的整数）。
 *
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 必须 原地 修改，只允许使用额外常数空间。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 * 示例 2：
 *
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 * 示例 3：
 *
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 * 示例 4：
 *
 * 输入：nums = [1]
 * 输出：[1]
 *
 * 链接：https://leetcode-cn.com/problems/next-permutation/
 */
public class Problem31 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 6, 5};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    /*
        1 2 3 4 6 5
        从后向前查找第一个相邻升序的元素对 (i,j)，满足 A[i] < A[j]。此时 [j,end) 必然是降序
        在 [j,end) 从后向前查找第一个满足 A[i] < A[k] 的 k。A[i]、A[k] 分别就是上文所说的「小数」、「大数」
        将 A[i] 与 A[k] 交换
        可以断定这时 [j,end) 必然是降序，逆置 [j,end)，使其升序
        如果在步骤 1 找不到符合的相邻元素对，说明当前 [begin,end) 为一个降序顺序，则直接跳到步骤 4
     */
    public static void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 1 || nums.length == 0) return;
        int i = 0, j = 0, k = 0, left = 0, right = nums.length - 1;
        // find i, j
        for (int a = nums.length - 1; a > 0 ; --a) {
            if (nums[a] > nums[a - 1]) {
                i = a - 1;
                j = a;
                break;
            }
        }
        boolean desc = i == j;
        if (!desc) {
            // find k
            for (int b = nums.length - 1; b >= j; --b) {
                if (nums[b] > nums[i]) {
                    k = b;
                    break;
                }
            }
            int temp = nums[k];
            nums[k] = nums[i];
            nums[i] = temp;
            left = j;
        }

        while (left < right) {
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
            left++;right--;
        }
    }
}
