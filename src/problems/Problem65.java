package problems;

import java.util.regex.Pattern;

/**
 * 65. 有效数字
 * 有效数字（按顺序）可以分成以下几个部分：
 *
 * 一个 小数 或者 整数
 * （可选）一个 'e' 或 'E' ，后面跟着一个 整数
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
 * 部分有效数字列举如下：
 *
 * ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"]
 * 部分无效数字列举如下：
 *
 * ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"]
 * 给你一个字符串 s ，如果 s 是一个 有效数字 ，请返回 true 。
 *
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
 * 输入：s = ".1"
 * 输出：true
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 20
 * s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，或者点 '.' 。
 *
 * 链接：https://leetcode-cn.com/problems/valid-number/
 */
public class Problem65 {
    public static void main(String[] args) {
        System.out.println(isNumber("-123.456e789"));
        Pattern pattern = Pattern.compile("[-+]?(\\d+(\\.\\d*)?|\\.\\d+)([eE][+-]?\\d+)?$");
    }

    /**
     * ?	 指定字符重复0次或1次
     * (  )  将子表达式分组
     *
     * @param s 要判断的字符串
     * @return 为数字返回true，否则返回false
     */
    public static boolean isNumber(String s) {
        return Pattern.compile("[+-]?(\\d+(\\.\\d*)?|\\.\\d+)([eE][+-]?\\d+)?$").matcher(s).matches();
    }

    public static boolean isNumber_(String s) {
        char[] ss = s.toCharArray();
        boolean numFlag = false, dotFlag = false, eFlag = false;
        for (int i = 0; i < ss.length; i++) {
            if (ss[i] >= '0' && ss[i] <= '9') {// ss[i] = [0-9]
                numFlag = true;
            } else if (ss[i] == '.' && !dotFlag && !eFlag) { // s[i]为'.'，需要点未出现过且e未出现过
                dotFlag = true;
            } else if ((ss[i] == 'e' || ss[i] == 'E') && !eFlag && numFlag) { // s[i]为e，需要e未出现过且之前有数字
                eFlag = true;
                numFlag = false; // 出现e之后重新判断整数
            } else if ((ss[i] == '+' || ss[i] == '-') && (i == 0 || ss[i - 1] == 'e' || ss[i - 1] == 'E')) { // s[i]为+-，只能出现在首位或者e之后
                continue;
            } else {
                return false;
            }
        }
        // 避免'.'这种测试样例，保证出现数字
        if (!numFlag) return false;

        return true;
    }
}
