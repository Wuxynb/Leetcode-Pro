package problems;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. 最小覆盖子串
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * <p>
 * <p>
 * <p>
 * 注意：
 * <p>
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 示例 2：
 * <p>
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 示例 3:
 * <p>
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 * <p>
 * 链接：https://leetcode-cn.com/problems/minimum-window-substring/
 */
public class Problem76 {
    public static void main(String[] args) {
//        System.out.println(minWindow("ADOBECODEBAANCBD", "ABC"));
        System.out.println(minWindow("ADOBECODEBAANCD", "ABC"));
//        System.out.println(minWindow("abcabdebac", "cda"));
    }

    public static String minWindowPlus(String s, String t) {
        char[] chars = s.toCharArray(), chart = t.toCharArray();
        int n = chars.length, m = chart.length;

        int[] hash = new int[128];
        for (char ch : chart) hash[ch]--;

        String res = "";
        for (int i = 0, j = 0, cnt = 0; i < n; i++) {
            hash[chars[i]]++;
            if (hash[chars[i]] <= 0) cnt++;
            while (cnt == m && hash[chars[j]] > 0) hash[chars[j++]]--; // hash[chars[j]] > 0 表示无关字符或多余的有效字符
            if (cnt == m)
                if (res.equals("") || res.length() > i - j + 1)
                    res = s.substring(j, i + 1);
        }
        return res;
    }

    public static String minWindow(String s, String t) {
        HashMap<Character, Integer> hs = new HashMap<>();
        HashMap<Character, Integer> ht = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            ht.put(t.charAt(i), ht.getOrDefault(t.charAt(i), 0) + 1);
        }
        String ans = "";
        int len = 0x3f3f3f3f, cnt = 0;  //有多少个元素符合
        for (int i = 0, j = 0; i < s.length(); i++) {
            hs.put(s.charAt(i), hs.getOrDefault(s.charAt(i), 0) + 1);
            if (ht.containsKey(s.charAt(i)) && hs.get(s.charAt(i)) <= ht.get(s.charAt(i))) cnt++;
            while (j < i && (!ht.containsKey(s.charAt(j)) || hs.get(s.charAt(j)) > ht.get(s.charAt(j)))) {
                int count = hs.get(s.charAt(j)) - 1;
                hs.put(s.charAt(j), count);
                j++;
            }
            if (cnt == t.length() && i - j + 1 < len) {
                len = i - j + 1;
                ans = s.substring(j, i + 1);
            }
        }
        return ans;
    }

    public static String minWindow_(String s, String t) {
        Map<Character, Integer> ori = new HashMap<>();
        Map<Character, Integer> cnt = new HashMap<>();

        int tLen = t.length();
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            ori.put(c, ori.getOrDefault(c, 0) + 1);
        }
        int l = 0, r = -1;
        int len = Integer.MAX_VALUE, ansL = -1, ansR = -1;
        int sLen = s.length();
        while (r < sLen) {
            ++r;
            if (r < sLen && ori.containsKey(s.charAt(r))) {
                cnt.put(s.charAt(r), cnt.getOrDefault(s.charAt(r), 0) + 1);
            }
            while (check(ori, cnt) && l <= r) {
                if (r - l + 1 < len) {
                    len = r - l + 1;
                    ansL = l;
                    ansR = l + len;
                }
                if (ori.containsKey(s.charAt(l))) {
                    cnt.put(s.charAt(l), cnt.getOrDefault(s.charAt(l), 0) - 1);
                }
                ++l;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }

    /*
        abcabd ebac
        cda
     */
    public static String minWindow__(String s, String t) {
        String ans = "";
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : tc) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i <= sc.length - tc.length; i++) {
            Map<Character, Integer> tempMap = new HashMap<>();
            int start = i, i2 = i;
            boolean f = false, f2 = false;  // f多余有效字符是否出现，f2第二个有效字符是否出现
            for (int j = i; j < sc.length; j++) {
                if (map.containsKey(sc[j])) {
                    int c = tempMap.getOrDefault(sc[j], 0);
                    tempMap.put(sc[j], c + 1);
                    if (!f && c == map.get(sc[j])) { // 记录第一个重复的有效字符所在位置（没必要）
                        i = j - 1;
                        f = true;
                    }
                    if (!f2 && j != i) { // 记录第二个有效字符位置
                        i2 = j - 1;
                        f2 = true;
                    }
                } else continue;
                if (check(map, tempMap)) {
                    tempMap.clear();
                    String str = s.substring(start, j + 1);
                    ans = ans.equals("") ? str : (ans.length() < str.length() ? ans : str);
                    if (!f) i = i2;
                    break;
                }
                i = Math.min(i, i2);
            }
        }

        return ans;
    }

    public static boolean check(Map<Character, Integer> map1, Map<Character, Integer> map2) {
        for (Character c : map1.keySet()) {
            if (map1.get(c) > map2.getOrDefault(c, 0)) return false;
        }
        return true;
    }
}
