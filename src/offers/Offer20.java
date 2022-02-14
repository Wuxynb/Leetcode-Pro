package offers;

import java.util.regex.Pattern;

/**
 * 剑指 Offer 20. 表示数值的字符串
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 *
 * 数值（按顺序）可以分成以下几个部分：
 *
 * 若干空格
 * 一个 小数 或者 整数
 * （可选）一个 'e' 或 'E' ，后面跟着一个 整数
 * 若干空格
 * 小数（按顺序）可以分成以下几个部分：
 *
 * （可选）一个符号字符（'+' 或 '-'）
 * 下述格式之一：
 * 至少一位数字，后面跟着一个点 '.'
 * 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
 * 一个点 '.' ，后面跟着至少一位数字
 * 整数（按顺序）可以分成以下几个部分：
 *
 * （可选）一个符号字符（'+' 或 '-'）
 * 至少一位数字
 * 部分数值列举如下：
 *
 * ["+100", "5e2", "-123", "3.1416", "-1E-16", "0123"]
 * 部分非数值列举如下：
 *
 * ["12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"]
 *
 *
 * 示例 1：
 *
 * 输入：s = "0"
 * 输出：true
 * 示例 2：
 *
 * 输入：s = "e"
 * 输出：false
 * 示例 3：
 *
 * 输入：s = "."
 * 输出：false
 * 示例 4：
 *
 * 输入：s = "    .1  "
 * 输出：true
 *
 * 链接：https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/
 */
public class Offer20 {
    public static void main(String[] args) {
        System.out.println(3.2e-1);
    }

    public static boolean isNumber(String s) {
        char[] chars = s.trim().toCharArray();
        boolean isNum = false, isDot = false, isE = false;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                isNum = true;
            } else if (chars[i] == '.') {
                if (isDot || isE) return false; // .前面不能有.或者出现Ee
                isDot = true;
            } else if (chars[i] == 'e' || chars[i] == 'E') {
                if (!isNum || isE) return false; // Ee前面必须有整数，且后面不能再有Ee
                isE = true;
                isNum = false; // Ee后面必须有整数，故重置isNum
            } else if (chars[i] == '-' || chars[i] == '+') {
                if (i != 0 && chars[i - 1] != 'e' && chars[i - 1] != 'E') return false; // -+只能在开头或者Ee的后面
            } else return false; //其他字符直接返回false
        }
        return isNum; // 防止出现123e或123e+-等情况
    }

    // leetcode the 65
    public static boolean isNumberRegex(String s) { // regular expression
        // ?最多只可出现一次。
        return Pattern.matches("[+-]?(\\d+(\\.\\d*)?|\\.\\d+)([eE][-+]?\\d+)?$", s.trim());
    }
}
