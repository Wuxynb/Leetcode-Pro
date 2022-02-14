package offers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer 38. 字符串的排列
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * <p>
 * <p>
 * <p>
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 * <p>
 *
 * 链接：https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/
 */
public class Offer38 {
    public static void main(String[] args) {

    }

    public String[] permutation(String s) {
        List<String> ans = new ArrayList<>();
        char[] cs = s.toCharArray();
        Arrays.sort(cs);
        backtrack(cs, ans, new StringBuilder(), 0, new boolean[s.length()]);
        return ans.toArray(new String[0]);
    }

    public void backtrack(char[] cs, List<String> ans, StringBuilder sb, int t, boolean[] visited) {
        if (sb.length() == cs.length) {
            ans.add(sb.toString());
            return;
        }
        for (int i = 0; i < cs.length; i++) {
            if (visited[i] || (i > 0 && cs[i] == cs[i - 1] && !visited[i - 1])) { // visited[i-1]已经回溯过了，会重新变为false。
                continue;
            }
            sb.append(cs[i]);
            visited[i] = true;
            backtrack(cs, ans, sb, t + 1, visited);
            sb.deleteCharAt(sb.length() - 1);
            visited[i] = false;
        }
    }
}
