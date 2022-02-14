package problems;

import java.util.*;

/**
 * 151. 翻转字符串里的单词
 * 给你一个字符串 s ，逐个翻转字符串中的所有 单词 。
 *
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 *
 * 请你返回一个翻转 s 中单词顺序并用单个空格相连的字符串。
 *
 * 说明：
 *
 * 输入字符串 s 可以在前面、后面或者单词间包含多余的空格。
 * 翻转后单词间应当仅用一个空格分隔。
 * 翻转后的字符串中不应包含额外的空格。
 *
 *
 * 示例 1：
 *
 * 输入：s = "the sky is blue"
 * 输出："blue is sky the"
 * 示例 2：
 *
 * 输入：s = "  hello world  "
 * 输出："world hello"
 * 解释：输入字符串可以在前面或者后面包含多余的空格，但是翻转后的字符不能包括。
 * 示例 3：
 *
 * 输入：s = "a good   example"
 * 输出："example good a"
 * 解释：如果两个单词间有多余的空格，将翻转后单词间的空格减少到只含一个。
 * 示例 4：
 *
 * 输入：s = "  Bob    Loves  Alice   "
 * 输出："Alice Loves Bob"
 * 示例 5：
 *
 * 输入：s = "Alice does not even like bob"
 * 输出："bob like even not does Alice"
 *
 * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string/
 */
public class Problem151 {
    public static void main(String[] args) {
        System.out.println(reverseWords("the  sky is blue"));
        System.out.println(reverseWords("  hello world  "));
        System.out.println(reverseWords("a good   example"));

        System.out.println(reverseWordsDoublePointer("the  sky is blue"));
        System.out.println(reverseWordsDoublePointer("  hello world  "));
        System.out.println(reverseWordsDoublePointer("a good   example"));
    }

    public static String reverseWordsDoublePointer(String s) {
        int left = 0, right = 0;
        Deque<String> deque = new LinkedList<>();
        char[] chars = s.toCharArray();
        while (right < s.length()) {
            while (left < s.length() && chars[left] == ' ') left++;
            right = left;
            while (right < s.length() && chars[right] != ' ') right++;
            if (left != right && right <= s.length()) {
                deque.addFirst(new String(chars, left, right - left));
            }
            left = right;
        }
        return String.join(" ", deque);
    }

    public static String reverseWords(String s) {
        int j = s.length() - 1, len = 0;
        List<String> list = new ArrayList<>();
        char[] chars = s.toCharArray();
        while (j >= 0) {
            if (chars[j] != ' ') {
                len++;
                if (j == 0) list.add(s.substring(j, j + len));
            } else {
                if (len > 0) {
                    list.add(s.substring(j + 1, j + 1 + len));
                    len = 0;
                }
            }
            j--;
        }
        return String.join(" ", list);
    }
}
