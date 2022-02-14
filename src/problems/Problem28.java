package problems;

import java.util.HashMap;
import java.util.Map;

/**
 * 28. 实现 strStr()
 * 实现 strStr() 函数。
 * <p>
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回  -1 。
 * <p>
 * 说明：
 * <p>
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * <p>
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：haystack = "hello", needle = "ll"
 * 输出：2
 * 示例 2：
 * <p>
 * 输入：haystack = "aaaaa", needle = "bba"
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：haystack = "", needle = ""
 * 输出：0
 * <p>
 * 链接：https://leetcode-cn.com/problems/implement-strstr/
 */
public class Problem28 {

    public static void main(String[] args) {
        System.out.println(strStr("ababbbbaaabbbaaa", "bbbb"));
        System.out.println(strStrSunday("aaaaa", "bba"));
    }

    /*
        Horspool Algorithm
     */
    public static int strStr(String haystack, String needle) {
        if (needle.equals("")) return 0;
        if (needle.length() > haystack.length()) return -1;
        Map<Character, Integer> shiftTable = new HashMap<>();
        char[] haystacks = haystack.toCharArray();
        char[] needles = needle.toCharArray();
        int n = haystacks.length, m = needles.length;

        for (int i = 0; i < needles.length - 1; i++) {
            shiftTable.put(needles[i], needles.length - 1 - i);
        }

        int i = m - 1;
        while (i < n) {
            int k = 0;
            while (k < m && needles[m - k - 1] == haystacks[i - k]) {
                k++;
            }
            if (k == m) return i - m + 1;
            else i += shiftTable.get(haystacks[i]) == null ? m : shiftTable.get(haystacks[i]);
        }

        return -1;
    }

    /*
        Sunday Algorithm
     */
    public static int strStrSunday(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        if (m == 0) return 0;
        if (m > n) return -1;
        char[] haystacks = haystack.toCharArray();
        char[] needles = needle.toCharArray();
        Map<Character, Integer> shiftTable = new HashMap<>();
        for (int i = 0; i < m; i++) {
            shiftTable.put(needles[i], m - i);
        }

        int i = 0;
        while (i + m <= n) {
            int k = 0;
            while (k < m && needles[k] == haystacks[i + k]) {
                k++;
            }
            if (k == m) return i;
            else {
                if (i + m < n) i += shiftTable.get(haystacks[i + m]) == null ? m + 1 : shiftTable.get(haystacks[i + m]);
                else return -1;
            }
        }

        return -1;
    }

    /*
        KMP algorithm
     */
    public int strStrKMP(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        if (m == 0) {
            return 0;
        }
        char[] s = haystack.toCharArray();
        char[] p = needle.toCharArray();

        int[] next = new int[m];

        //构造next数组
        for (int i = 1, j = 0; i < m; i++) {
            while (j > 0 && p[i] != p[j]) {
                j = next[j - 1];
            }
            if (p[i] == p[j]) {
                j++;
            }
            next[i] = j;
        }
        //匹配过程
        for (int i = 0, j = 0; i < n; i++) {
            while (j > 0 && s[i] != p[j]) {
                j = next[j - 1];
            }
            if (s[i] == p[j]) {
                j++;
            }
            if (j == m) {
                return i - m + 1;
            }
        }
        return -1;
    }
}
