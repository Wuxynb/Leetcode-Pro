package offers;

import java.util.Arrays;

/**
 * 剑指 Offer 50. 第一个只出现一次的字符
 * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
 * <p>
 * 示例 1:
 * <p>
 * 输入：s = "abaccdeff"
 * 输出：'b'
 * 示例 2:
 * <p>
 * 输入：s = ""
 * 输出：' '
 * <p>
 * 链接：https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/
 */
public class Offer50 {
    public static void main(String[] args) {
//        System.out.println(firstUniqChar("leetcode"));
        System.out.println(BWTEncode("banana"));
    }

    public static char firstUniqChar(String s) {
        int[] hash = new int[26];
        for (char c : s.toCharArray()) hash[c - 'a']++;
        for (char c : s.toCharArray()) if (hash[c - 'a'] == 1) return c;
        return ' ';
    }

    // BWT encode
    public static String BWTEncode(String line) {
        String str = line + "&";
        int len = str.length();
        // 1.轮转
        char[] charArray = str.toCharArray();
        char[][] chs = new char[len][len];
        for (int i = 0; i < len; i++) {
            char[] tempArr = charArray.clone();
            for (int j = 0; j < len; j++) {
                chs[i][j] = tempArr[j];
                if (j <= len - 2) charArray[j + 1] = tempArr[j];
            }
            charArray[0] = tempArr[len - 1];
        }

        // 2.排序，按照字典顺序
        String[] strings = new String[len];
        for (int i = 0; i < len; i++) strings[i] = new String(chs[i]);
        Arrays.sort(strings);

        // 3.取最后一行
        StringBuilder sb = new StringBuilder();
        for (String s : strings) sb.append(s.charAt(len - 1));

        return sb.toString();
    }
}
