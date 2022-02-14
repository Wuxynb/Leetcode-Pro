package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 66. 加一
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 *
 *
 * 示例 1：
 *
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 * 示例 2：
 *
 * 输入：digits = [4,3,2,1]
 * 输出：[4,3,2,2]
 * 解释：输入数组表示数字 4321。
 * 示例 3：
 *
 * 输入：digits = [0]
 * 输出：[1]
 *
 * 链接：https://leetcode-cn.com/problems/plus-one/
 */
public class Problem66 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(plusOne(new int[]{9,9})));
        System.out.println(Arrays.toString(plusOnePlus(new int[]{9,9})));
    }

    /*
        Overflow
     */
    public static int[] plusOneN(int[] digits) {
        long num = 0;
        for (int i = 0; i < digits.length; i++) {
            num = num * 10 + digits[i];
        }
        num++;

        List<Integer> nums = new ArrayList<>();
        while (num != 0) {
            nums.add((int) (num % 10));
            num /= 10;
        }
        int[] newDigits = new int[nums.size()];
        for (int i = newDigits.length - 1; i >= 0; i--) {
            newDigits[i] = nums.get(newDigits.length - i - 1);
        }

        return newDigits;
    }

    public static int[] plusOne(int[] digits) {
        List<Integer> nums = new ArrayList<>();
        int n = digits.length, carry = 0;
        digits[n - 1] += 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i] += carry;
            nums.add((digits[i]) % 10);
            carry = digits[i] / 10;
        }
        if (carry != 0) nums.add(carry);
        int[] newDigits = new int[nums.size()];
        for (int i = newDigits.length - 1; i >= 0; i--) {
            newDigits[i] = nums.get(newDigits.length - i - 1);
        }

        return newDigits;
    }

    /*
     1. 除 99 之外的数字加一；
     2. 数字 99。
     */
    public static int[] plusOnePlus(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] %= 10;
            if (digits[i] != 0) return digits;
        }
        digits = new int[n + 1];
        digits[0] = 1;
        return digits;
    }
}
