package problems;

/**
 * 258. 各位相加
 * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。返回这个结果。
 *
 *
 *
 * 示例 1:
 *
 * 输入: num = 38
 * 输出: 2
 * 解释: 各位相加的过程为：
 * 38 --> 3 + 8 --> 11
 * 11 --> 1 + 1 --> 2
 * 由于 2 是一位数，所以返回 2。
 * 示例 1:
 *
 * 输入: num = 0
 * 输出: 0
 *
 *
 * 提示：
 *
 * 0 <= num <= 231 - 1
 *
 *
 * 进阶：你可以不使用循环或者递归，在 O(1) 时间复杂度内解决这个问题吗？
 *
 * link: https://leetcode-cn.com/problems/add-digits/
 */
public class Problem258 {
    public static void main(String[] args) {

    }

    public int addDigits(int num) {
        int x;
        while (true) {
            x = 0;
            while (num > 0){
                x += (num % 10);
                num /= 10;
            }
            if (x < 10) return x;
            num = x;
        }
    }

    public int addDigitsOpt(int num) {
        // 能够被9整除的整数，各位上的数字加起来也必然能被9整除，所以，连续累加起来，最终必然就是9。
        // 不能被9整除的整数，各位上的数字加起来，结果对9取模，和初始数对9取摸，是一样的，所以，连续累加起来，最终必然就是初始数对9取摸。
         if (num % 9 == 0) return 9;
         else return num % 9;

        // return (num - 1) % 9 + 1;
    }
}
