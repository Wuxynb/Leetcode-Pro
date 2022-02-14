package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18. 四数之和
 * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] ：
 * <p>
 * 0 <= a, b, c, d < n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 你可以按 任意顺序 返回答案 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * 示例 2：
 * <p>
 * 输入：nums = [2,2,2,2,2], target = 8
 * 输出：[[2,2,2,2]]
 * <p>
 * 链接：https://leetcode-cn.com/problems/4sum/
 */
public class Problem18 {

    public static void main(String[] args) {
        System.out.println(fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        if (nums.length < 4) return lists;
        Arrays.sort(nums); // -2, -1, 0, 0, 1, 2

        for (int i = 0; i < nums.length - 3; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                for (int j = i + 1; j < nums.length - 2; j++) {
                    if (j == i + 1 || nums[j] != nums[j - 1]) {
                        int l = nums.length - 1;
                        for (int k = j + 1; k < nums.length; k++) {
                            if (k == j + 1 || nums[k] != nums[k - 1]) {
                                while (k < l) {
                                    if (nums[i] + nums[j] + nums[k] + nums[l] < target) {
                                        break;
                                    } else if (nums[i] + nums[j] + nums[k] + nums[l] > target) {
                                        l--;
                                    } else {
//                                        System.out.println("i = " + i + ", j = " + j + ", k = " + k + ", l = " + l);
                                        lists.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return lists;
    }
}
