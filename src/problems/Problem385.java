package problems;

import util.NestedInteger;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 385. 迷你语法分析器
 * 给定一个字符串 s 表示一个整数嵌套列表，实现一个解析它的语法分析器并返回解析的结果 NestedInteger 。
 * <p>
 * 列表中的每个元素只可能是整数或整数嵌套列表
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "324",
 * 输出：324
 * 解释：你应该返回一个 NestedInteger 对象，其中只包含整数值 324。
 * 示例 2：
 * <p>
 * 输入：s = "[123,[456,[789]]]",
 * 输出：[123,[456,[789]]]
 * 解释：返回一个 NestedInteger 对象包含一个有两个元素的嵌套列表：
 * 1. 一个 integer 包含值 123
 * 2. 一个包含两个元素的嵌套列表：
 * i.  一个 integer 包含值 456
 * ii. 一个包含一个元素的嵌套列表
 * a. 一个 integer 包含值 789
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 5 * 104
 * s 由数字、方括号 "[]"、负号 '-' 、逗号 ','组成
 * 用例保证 s 是可解析的 NestedInteger
 * 输入中的所有值的范围是 [-106, 106]
 * <p>
 * link: https://leetcode-cn.com/problems/mini-parser/
 */
public class Problem385 {
    public static void main(String[] args) {
        System.out.println(deserialize("[123,[456,[789]]]"));
    }

    public static NestedInteger deserialize(String s) {
        if (s.charAt(0) != '[') return new NestedInteger(Integer.parseInt(s));
        char[] cs = s.toCharArray();
        Deque<NestedInteger> stack = new ArrayDeque<>();
        int num = 0;
        boolean neg = false;
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == '-') neg = true;
            else if (Character.isDigit(cs[i])) num = num * 10 + cs[i] - '0';
            else if (cs[i] == '[') stack.push(new NestedInteger());
            else if (cs[i] == ',' || cs[i] == ']') {
                if (Character.isDigit(cs[i - 1])) {
                    if (neg) num *= -1;
                    stack.peek().add(new NestedInteger(num));
                }
                num = 0;
                neg = false;
                if (cs[i] == ']' && stack.size() > 1) {
                    NestedInteger ni = stack.pop();
                    stack.peek().add(ni);
                }
            }
        }
        return stack.pop();
    }
}
