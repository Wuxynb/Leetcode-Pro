package problems;

/**
 * 796. 旋转字符串
 * 给定两个字符串, s 和 goal。如果在若干次旋转操作之后，s 能变成 goal ，那么返回 true 。
 *
 * s 的 旋转操作 就是将 s 最左边的字符移动到最右边。
 *
 * 例如, 若 s = 'abcde'，在旋转一次之后结果就是'bcdea' 。
 *
 *
 * 示例 1:
 *
 * 输入: s = "abcde", goal = "cdeab"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "abcde", goal = "abced"
 * 输出: false
 *
 *
 * 提示:
 *
 * 1 <= s.length, goal.length <= 100
 * s 和 goal 由小写英文字母组成
 *
 * link: https://leetcode-cn.com/problems/rotate-string/
 */
public class Problem769 {
    public static void main(String[] args) {
        System.out.println(rotateString("abcde", "cdeab"));
        System.out.println(rotateStringBrute("abcde", "cdeab"));
    }

    /**
     * 输入: s = "abcde", goal = "cdeab"
     * 输出: true
     */
    public static boolean rotateString(String s, String goal) {
        return s.length() == goal.length() && s.concat(s).contains(goal);
    }

    public static boolean rotateStringBrute(String s, String goal) {
        for (int i = 0; i < s.length(); i++) {
            String t = s.substring(i) + s.substring(0, i);
            if (t.equals(goal)) return true;
        }
        return false;
    }
}
