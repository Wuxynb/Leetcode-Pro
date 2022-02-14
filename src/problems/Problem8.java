package problems;

/**
 * 8. 字符串转换整数 (atoi)
 * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
 *
 * 函数 myAtoi(string s) 的算法如下：
 *
 * 读入字符串并丢弃无用的前导空格
 * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
 * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
 * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
 * 如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
 * 返回整数作为最终结果。
 * 注意：
 *
 * 本题中的空白字符只包括空格字符 ' ' 。
 * 除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
 *
 *
 * 示例 1：
 *
 * 输入：s = "42"
 * 输出：42
 * 解释：加粗的字符串为已经读入的字符，插入符号是当前读取的字符。
 * 第 1 步："42"（当前没有读入字符，因为没有前导空格）
 *          ^
 * 第 2 步："42"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
 *          ^
 * 第 3 步："42"（读入 "42"）
 *            ^
 * 解析得到整数 42 。
 * 由于 "42" 在范围 [-231, 231 - 1] 内，最终结果为 42 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/string-to-integer-atoi/
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Problem8 {

    public static void main(String[] args) {
        System.out.println(myAtoi("  -1"));
    }

    public static int myAtoi(String s) {
        int k = 0;
        long ans = 0;
        boolean f = false;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                if (k == 0 && (chars[i] == '-' || chars[i] == '+')) {
                    if (chars[i] == '-') {
                        f = true;
                    }
                    k++;
                } else {
                    if (chars[i] >= '0' && chars[i] <= '9') {
                        ans = ans * 10 + (int) (chars[i] - 48);
                        k++;
                        // ans * 10 + chars[i] - 48 > Integer.MAX_VALUE ---->  ans > (Integer.MAX_VALUE - chars[i] - 48) / 10
                        if (ans > Integer.MAX_VALUE) {
                            if (f) {
                                if (-ans < Integer.MIN_VALUE) return Integer.MIN_VALUE;
                            } else {
                                return Integer.MAX_VALUE;
                            }
                        }

                    } else {
                        break;
                    }
                }
            } else {
                if (k > 0) break;
            }
        }

        return f ? (int) -ans : (int) ans;
    }
}
