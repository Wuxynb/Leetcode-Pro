package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * 500. 键盘行
 * 给你一个字符串数组 words ，只返回可以使用在 美式键盘 同一行的字母打印出来的单词。键盘如下图所示。
 *
 * 美式键盘 中：
 *
 * 第一行由字符 "qwertyuiop" 组成。
 * 第二行由字符 "asdfghjkl" 组成。
 * 第三行由字符 "zxcvbnm" 组成。
 * American keyboard
 *
 *
 *
 * 示例 1：
 *
 * 输入：words = ["Hello","Alaska","Dad","Peace"]
 * 输出：["Alaska","Dad"]
 * 示例 2：
 *
 * 输入：words = ["omk"]
 * 输出：[]
 * 示例 3：
 *
 * 输入：words = ["adsdf","sfd"]
 * 输出：["adsdf","sfd"]
 *
 * 链接：https://leetcode-cn.com/problems/keyboard-row/
 */
public class Problem500 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(findWords(new String[]{"Hello", "Alaska", "Dad", "Peace"})));
    }

    public static String[] findWords(String[] words) {
        List<String> ans = new ArrayList<>();
        for (String word : words) if (find(word, "qwertyuiop") || find(word, "asdfghjkl") || find(word, "zxcvbnm")) ans.add(word);
        return ans.toArray(new String[0]);
    }

    public static boolean find(String word, String pattern) {
        for (char c : word.toLowerCase().toCharArray()) if (pattern.indexOf(c) == -1) return false;
        return true;
    }

    public static String[] findWordsRegex(String[] words) {
        List<String> list = new ArrayList<>();
        for (String word : words) {
            if ((word.toLowerCase(Locale.ROOT)).matches("^([qwertyuiop]+|[asdfghjkl]+|[zxcvbnm]+)$")) list.add(word);
        }
        return list.toArray(new String[0]);
    }
}
