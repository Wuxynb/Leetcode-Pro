package problems;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 720. 词典中最长的单词
 * 给出一个字符串数组 words 组成的一本英语词典。返回 words 中最长的一个单词，该单词是由 words 词典中其他单词逐步添加一个字母组成。
 *
 * 若其中有多个可行的答案，则返回答案中字典序最小的单词。若无答案，则返回空字符串。
 *
 *
 *
 * 示例 1：
 *
 * 输入：words = ["w","wo","wor","worl", "world"]
 * 输出："world"
 * 解释： 单词"world"可由"w", "wo", "wor", 和 "worl"逐步添加一个字母组成。
 * 示例 2：
 *
 * 输入：words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
 * 输出："apple"
 * 解释："apply" 和 "apple" 都能由词典中的单词组成。但是 "apple" 的字典序小于 "apply"
 *
 *
 * 提示：
 *
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 30
 * 所有输入的字符串 words[i] 都只包含小写字母。
 *
 * link: https://leetcode-cn.com/problems/longest-word-in-dictionary/
 */
public class Problem720 {
    public static void main(String[] args) {
        System.out.println(longestWord(new String[]{"a", "banana", "app", "appl", "ap", "apply", "apple"}));
        System.out.println(longestWord(new String[]{"w","wo","wor","worl", "world"}));
    }

    /**
     * 输入：words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
     * 输出："apple"
     * 解释："apply" 和 "apple" 都能由词典中的单词组成。但是 "apple" 的字典序小于 "apply"
     */
    public static String longestWord(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() != b.length() ? (a.length() - b.length()) : b.compareTo(a));
        Set<String> set = new HashSet<>();
        String longest = "";
        set.add(longest);
        for (String word : words) {
            if (set.contains(word.substring(0, word.length() - 1))) {
                set.add(word);
                if (word.length() > longest.length()) longest = word;
            }
        }
        return longest;
    }
}
