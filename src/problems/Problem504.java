package problems;

/**
 * 504. 七进制数
 * 给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。
 *
 *
 *
 * 示例 1:
 *
 * 输入: num = 100
 * 输出: "202"
 * 示例 2:
 *
 * 输入: num = -7
 * 输出: "-10"
 *
 *
 * 提示：
 *
 * -107 <= num <= 107
 *
 * link: https://leetcode-cn.com/problems/base-7/
 */
public class Problem504 {
    public static void main(String[] args) {
        System.out.println(convertToBase7(100));
        System.out.println(convertToBase7(1000));
        System.out.println(convertToBase7(-7));
        System.out.println(convertToBase7(-104));
    }

    public static String convertToBase7(int num) {
        if (num == 0) return "0";
        boolean isNegative = num < 0;
        num = Math.abs(num);
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.append(num % 7);
            num /= 7;
        }
        if (isNegative) sb.append('-');
        return sb.reverse().toString();
    }
}
