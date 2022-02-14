package offers;

import java.util.Arrays;

/**
 * 剑指 Offer 61. 扑克牌中的顺子
 * 从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3,4,5]
 * 输出: True
 * <p>
 * <p>
 * 示例 2:
 * <p>
 * 输入: [0,0,1,2,5]
 * 输出: True
 * <p>
 * 链接：https://leetcode-cn.com/problems/bu-ke-pai-zhong-de-shun-zi-lcof/
 */
public class Offer61 {
    public static void main(String[] args) {
        System.out.println(isStraight(new int[]{0, 0, 1, 2, 5}));
    }

    public static boolean isStraight(int[] nums) {
        Arrays.sort(nums);
        int zero = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == 0) zero++;
            else {
                if (nums[i] == nums[i + 1]) return false;
                else if ((zero -= nums[i + 1] - nums[i] - 1) < 0) return false;
            }
        }
        return zero >= 0;
    }
}
