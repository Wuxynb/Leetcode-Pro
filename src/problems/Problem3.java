package problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Problem3 {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("dvdec"));
        Map<Character, Integer> map = new HashMap<>();
    }

    public static int lengthOfLongestSubstring(String s) {
        List<String> list = new ArrayList<>();

        getMaxLen(s, list);

        int length = 0;
        for (String str : list) {
            length = Math.max(length, str.length());
        }

        return length;
    }

    public static void getMaxLen(String s, List<String> list) {
        String tmp = "";
        for (int i = 0; i < s.length(); i++) {
            if (!tmp.contains(s.charAt(i) + "")) {
                tmp += s.charAt(i) + "";

                if (s.length() - 1 == i) {
                    list.add(tmp);
                }
            } else {
                list.add(tmp);
                getMaxLen(s.substring(s.length() > 1 ? 1 : 0), list);
                break;
            }
        }
    }

    public static int lengthOfLongestSubstring2(String s) {
        Map<Character, Integer> map = new HashMap<>();

        int start = 0, length = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) != null) {
                start = Math.max(start, map.get(s.charAt(i)) + 1);
            }
            length = Math.max(length, i - start + 1);
            map.put(s.charAt(i), i);
        }

        return length;
    }
}


