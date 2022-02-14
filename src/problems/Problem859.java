package problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 859. 亲密字符串
 * 给你两个字符串 s 和 goal ，只要我们可以通过交换 s 中的两个字母得到与 goal 相等的结果，就返回 true ；否则返回 false 。
 *
 * 交换字母的定义是：取两个下标 i 和 j （下标从 0 开始）且满足 i != j ，接着交换 s[i] 和 s[j] 处的字符。
 *
 * 例如，在 "abcd" 中交换下标 0 和下标 2 的元素可以生成 "cbad" 。
 *
 *
 * 示例 1：
 *
 * 输入：s = "ab", goal = "ba"
 * 输出：true
 * 解释：你可以交换 s[0] = 'a' 和 s[1] = 'b' 生成 "ba"，此时 s 和 goal 相等。
 * 示例 2：
 *
 * 输入：s = "ab", goal = "ab"
 * 输出：false
 * 解释：你只能交换 s[0] = 'a' 和 s[1] = 'b' 生成 "ba"，此时 s 和 goal 不相等。
 * 示例 3：
 *
 * 输入：s = "aa", goal = "aa"
 * 输出：true
 * 解释：你可以交换 s[0] = 'a' 和 s[1] = 'a' 生成 "aa"，此时 s 和 goal 相等。
 * 示例 4：
 *
 * 输入：s = "aaaaaaabc", goal = "aaaaaaacb"
 * 输出：true
 *
 * 链接：https://leetcode-cn.com/problems/buddy-strings/
 */
public class Problem859 {
    public static void main(String[] args) {
        System.out.println(buddyStrings("aa", "aa"));
        System.out.println(buddyStringsOpti("aa", "aa"));
    }

    public static boolean buddyStringsOpti(String s, String goal) {
        int m = s.length(), n = goal.length(), diff = 0;
        if (m != n) return false;
        int[] hash1 = new int[26], hash2 = new int[26];
        char[] ss = s.toCharArray(), goals = goal.toCharArray();
        for (int i = 0; i < n; i++) {
            hash1[ss[i] - 'a']++;
            hash2[goals[i] - 'a']++;
            if (ss[i] != goals[i]) diff++;
        }
        boolean ans = false;
        for (int i = 0; i < 26; i++) {
            if (hash1[i] != hash2[i]) return false;
            if (hash1[i] > 1) ans = true;
        }
        return diff == 2 || (diff == 0 && ans);
    }

    public static boolean buddyStrings(String s, String goal) {
        if (s.length() != goal.length()) return false;
        Map<Character, List<Integer>> map = new HashMap<>();
        char[] ss = s.toCharArray(), goals = goal.toCharArray();
        for (int i = 0; i < ss.length; i++) {
            map.putIfAbsent(ss[i], new ArrayList<>());
            map.get(ss[i]).add(i);
        }
        if (s.equals(goal) && s.length() == map.size()) return false;
        for (int i = 0; i < goals.length; i++) {
            if (ss[i] != goals[i]) {
                List<Integer> list = map.get(goals[i]);
                if (list == null) return false;
                for (int idx : list) {
                    if (idx > i && goals[idx] == ss[i]) {
                        char tmp = goals[i];
                        goals[i] = goals[idx];
                        goals[idx] = tmp;
                        if (String.valueOf(ss).equals(String.valueOf(goals))) return true;
                        tmp = goals[i];
                        goals[i] = goals[idx];
                        goals[idx] = tmp;
                    }
                }
                return false;
            }
        }
        return true;
    }
}
