package problems;

import java.util.HashMap;
import java.util.Map;

/**
 * 166. 分数到小数
 * 给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以 字符串形式返回小数 。
 *
 * 如果小数部分为循环小数，则将循环的部分括在括号内。
 *
 * 如果存在多个答案，只需返回 任意一个 。
 *
 * 对于所有给定的输入，保证 答案字符串的长度小于 104 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：numerator = 1, denominator = 2
 * 输出："0.5"
 * 示例 2：
 *
 * 输入：numerator = 2, denominator = 1
 * 输出："2"
 * 示例 3：
 *
 * 输入：numerator = 2, denominator = 3
 * 输出："0.(6)"
 * 示例 4：
 *
 * 输入：numerator = 4, denominator = 333
 * 输出："0.(012)"
 * 示例 5：
 *
 * 输入：numerator = 1, denominator = 5
 * 输出："0.2"
 *
 * 链接：https://leetcode-cn.com/problems/fraction-to-recurring-decimal/
 */
public class Problem166 {
    public static void main(String[] args) {
        System.out.println(fractionToDecimal(10, 3));
        System.out.println(fractionToDecimal(1, 2));
    }

    /*
        哈希表记录所有被除数的下标，如果出现了重复的被除数，则证明出现了循环，
        把左括号塞到记录的下标位置，右括号放在最后
     */
    public static String fractionToDecimal(int numerator, int denominator) {
        StringBuilder sb = new StringBuilder();
        long a = numerator, b = denominator;
        if (a * b < 0) sb.append('-');
        a = Math.abs(a);
        b = Math.abs(b);
        sb.append(a / b);
        if (a % b == 0) return sb.toString();
        sb.append('.');
        Map<Long, Integer> map = new HashMap<>();
        while ((a = (a % b) * 10) > 0 && !map.containsKey(a)) {
            map.put(a, sb.length());
            sb.append(a / b);
        }
        if (a == 0) return sb.toString();
        return sb.insert(map.get(a).intValue(), '(').append(')').toString();
    }
}
