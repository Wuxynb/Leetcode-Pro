package problems;

import java.util.*;

/**
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *
 *
 * 示例 1：
 *
 * 输入：s = "()"
 * 输出：true
 * 示例 2：
 *
 * 输入：s = "()[]{}"
 * 输出：true
 * 示例 3：
 *
 * 输入：s = "(]"
 * 输出：false
 *
 * 链接：https://leetcode-cn.com/problems/valid-parentheses/
 */
public class Problem20 {

    public static void main(String[] args) {
        System.out.println(isValid("){"));
        System.out.println(isValidPlus("){"));
    }

    public static boolean isValid(String s) {
        if (s.length() % 2 == 1) return false;
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        Deque<Character> stack = new LinkedList<>();
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            Character c = map.get(aChar);
            if (c != null) {
                stack.push(aChar);
            } else {
                if (stack.size() == 0 || map.get(stack.pop()) != aChar) {
                    return false;
                }
            }
        }

        return stack.size() == 0;
    }

    public static boolean isValidPlus(String s) {
        char[] stack = new char[s.length() + 1];
        int top = 1;
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '(' || c == '[' || c == '{') stack[top++] = c;
            else if (c == ')' && stack[--top] != '(') return false;
            else if (c == ']' && stack[--top] != '[') return false;
            else if (c == '}' && stack[--top] != '{') return false;
        }

        return top == 1;
    }
}
