package problems;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：["()"]
 * <p>
 * 链接：https://leetcode-cn.com/problems/generate-parentheses/
 */
public class Problem22 {

    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }

    public static List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();

        backtrack(list, "", n, 0, 0);

        return list;
    }

    public static void backtrack(List<String> list, String str, int n, int left, int right) {
        if (left == right && left == n) list.add(str);
        if (left < right) return;
        if (left < n) backtrack(list, str + "(", n, left + 1, right);
        if (right < n) backtrack(list, str + ")", n, left, right + 1);
    }

    public static boolean isValidPlus(String s) {
        char[] stack = new char[s.length() + 1];
        int top = 1;
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '(') stack[top++] = c;
            else if (c == ')' && stack[--top] != '(') return false;
        }

        return top == 1;
    }

    // List all combinations without restrictions
    /*public static void backtrack(List<String> list, String str, int n, int left, int right) {
        if (left == right && left == n) list.add(str);
        if (left < right) return; // 剪枝

        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                if (left < n) {
                    str += "(";
                    backtrack(list, str, n, left + 1, right);
                    str = str.substring(0, str.length() - 1);
                }
            } else {
                if (right < n) {
                    str += ")";
                    backtrack(list, str, n, left, right + 1);
                    str = str.substring(0, str.length() - 1);
                }
            }
        }
    }*/
}
