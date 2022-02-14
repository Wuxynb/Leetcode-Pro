package problems;

/**
 * 58. 最后一个单词的长度
 * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中最后一个单词的长度。
 *
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "Hello World"
 * 输出：5
 * 示例 2：
 *
 * 输入：s = "   fly me   to   the moon  "
 * 输出：4
 * 示例 3：
 *
 * 输入：s = "luffy is still joyboy"
 * 输出：6
 *
 * 链接：https://leetcode-cn.com/problems/length-of-last-word/
 */
public class Problem58 {
    public static void main(String[] args) {
        String s = "   fly me   to   the moon  ";
        String[] strs = s.split(" ");
        System.out.println(strs[strs.length - 1].replaceAll(" ", "").length());
    }

    public static int lengthOfLastWord(String s) {
        int ans = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                ans++;
            } else {
                if (ans != 0) break;
            }
        }
        return ans;
    }

    public static int lengthOfLastWord_(String s) {
        String[] strs = s.split(" ");
        char[] chars = strs[strs.length - 1].toCharArray();
        int ans = 0;
        for (char c : chars) {
            if (c != ' ') ans++;
        }
        return ans;
    }
}
